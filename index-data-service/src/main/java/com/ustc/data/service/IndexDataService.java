package com.ustc.data.service;

import cn.hutool.core.collection.CollUtil;
import com.ustc.data.pojo.IndexData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="index_datas")
public class IndexDataService {

    @Cacheable(key="'indexData-code-'+ #code")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }
}
