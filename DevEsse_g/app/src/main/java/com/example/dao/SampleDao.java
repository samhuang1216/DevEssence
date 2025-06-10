package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public class SampleDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SampleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Async("taskExecutor")
    public CompletableFuture<List<String>> findAllNames() {
        List<String> names = jdbcTemplate.queryForList("SELECT name FROM sample_table", String.class);
        return CompletableFuture.completedFuture(names);
    }
}
