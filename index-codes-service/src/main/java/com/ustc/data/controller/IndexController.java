package com.ustc.data.controller;

import com.ustc.data.pojo.Index;
import com.ustc.data.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/codes")
    @CrossOrigin
    public List<Index> codes() throws Exception {
        return indexService.get();
    }
}
