package com.ustc.data.service;

import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ustc.data.pojo.IndexData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@CacheConfig(cacheNames="index_datas")
public class IndexDataService {
    @Autowired
    private IndexServiceInterface indexServiceFeignClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Cacheable(key = "'indexData-code-'+#id")
    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> getIndexFromThirdPart(String id) {
        List<IndexData> indexes = new ArrayList<>();
        List<Map> maps = indexServiceFeignClient.getIndexFromThirdParty(id);
        for (Map map : maps) {
            IndexData indexData = new IndexData();
            indexData.setDate(map.get("date").toString());
            indexData.setClosePoint(Float.parseFloat(map.get("closePoint").toString()));
            indexes.add(indexData);
        }
        return indexes;
    }

    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<IndexData> fresh(String id) {
        IndexDataService indexDataService = applicationContext.getBean(IndexDataService.class);
        indexDataService.remove(id);
        return indexDataService.getIndexFromThirdPart(id);
    }

    //是否清楚所有的缓存
    @CacheEvict(key = "'indexData-code-'+#id")
    public void remove(String id) {

    }

    private List<IndexData> third_part_not_connected(String id) {
        System.out.println("发生异常了");
        IndexData index= new IndexData();
        index.setClosePoint(0);
        index.setDate("n/a");
        return CollectionUtil.toList(index);
    }
}
