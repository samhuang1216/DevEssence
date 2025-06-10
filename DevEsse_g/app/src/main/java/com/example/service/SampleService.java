package com.example.service;

import com.example.dao.SampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class SampleService {

    private final SampleDao sampleDao;

    @Autowired
    public SampleService(SampleDao sampleDao) {
        this.sampleDao = sampleDao;
    }

    public CompletableFuture<List<String>> getNames() {
        return sampleDao.findAllNames();
    }
}
