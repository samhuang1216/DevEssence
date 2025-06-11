package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DuckDBQueryExecutorTests {

    @Autowired
    DuckDBQueryExecutor executor;

    @Test
    void executesSimpleQuery() throws Exception {
        executor.submit("CREATE TABLE t(v INTEGER)").get();
        executor.submit("INSERT INTO t VALUES (1), (2)").get();
        List<Map<String, Object>> rows = executor.submit("SELECT COUNT(*) AS c FROM t").get();
        assertEquals(1, rows.size());
        Object count = rows.get(0).get("c");
        assertNotNull(count);
    }
}
