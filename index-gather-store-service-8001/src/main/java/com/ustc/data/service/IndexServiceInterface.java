package com.ustc.data.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(value = "third-part-index-data-project")
public interface IndexServiceInterface {

    @GetMapping(value = "/indexes/{id}.json")
    List<Map> getIndexFromThirdParty(@PathVariable("id") String id);
}
