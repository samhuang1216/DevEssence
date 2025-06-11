package com.example;

import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class DuckDBQueryExecutor {

    private final BlockingQueue<QueryTask> queue = new LinkedBlockingQueue<>();
    private final Connection connection;

    public DuckDBQueryExecutor() throws SQLException {
        connection = DriverManager.getConnection("jdbc:duckdb:");
        startQueryThread();
    }

    public CompletableFuture<List<Map<String, Object>>> submit(String sql) {
        CompletableFuture<List<Map<String, Object>>> future = new CompletableFuture<>();
        queue.offer(new QueryTask(sql, future));
        return future;
    }

    private void startQueryThread() {
        Thread worker = new Thread(() -> {
            while (true) {
                QueryTask task = null;
                try {
                    task = queue.take();
                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(task.sql());
                    List<Map<String, Object>> rows = convert(rs);
                    task.future().complete(rows);
                } catch (Exception e) {
                    if (task != null) {
                        task.future().completeExceptionally(e);
                    }
                }
            }
        });
        worker.setDaemon(true);
        worker.start();
    }

    private List<Map<String, Object>> convert(ResultSet rs) throws SQLException {
        List<Map<String, Object>> result = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        while (rs.next()) {
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                row.put(meta.getColumnName(i), rs.getObject(i));
            }
            result.add(row);
        }
        return result;
    }

    public record QueryTask(String sql, CompletableFuture<List<Map<String, Object>>> future) {}
}
