package com.ustc.data.controller;

import com.ustc.data.pojo.IndexData;
import com.ustc.data.service.IndexDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexDataController {
    @Autowired
    private IndexDataService indexDataService;

    @GetMapping("/getIndexData/{code}")
    public List<IndexData> get(@PathVariable("code") String code) {
        return indexDataService.getIndexFromThirdPart(code);
    }

    @GetMapping("/freshIndexData/{code}")
    public List<IndexData> fresh(@PathVariable("code") String code) {
        return indexDataService.fresh(code);
    }
}
