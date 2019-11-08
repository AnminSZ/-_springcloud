package com.ustc.data.service;

import cn.hutool.core.collection.CollUtil;
import com.ustc.data.pojo.Index;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="indexes")
public class IndexService {
    private List<Index> indexes;

//   如果没有数据，则会返回 “无效指数代码”
    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        Index index = new Index();
        index.setName("无效指数代码");
        index.setCode("000000");
        return CollUtil.toList(index);
    }

}
