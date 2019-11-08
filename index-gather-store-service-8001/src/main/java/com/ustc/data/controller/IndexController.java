package com.ustc.data.controller;

import com.ustc.data.pojo.Index;
import com.ustc.data.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexService.getIndexFromThirdPart("codes");
    }

    @GetMapping("/freshCodes")
    public List<Index> fresh() throws Exception {
        return indexService.fresh("codes");
    }
}
