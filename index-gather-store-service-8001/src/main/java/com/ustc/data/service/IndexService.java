package com.ustc.data.service;

import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.ustc.data.pojo.Index;
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
@CacheConfig(cacheNames = "indexes")
public class IndexService {
    @Autowired
    private IndexServiceInterface indexServiceFeignClient;

    @Autowired
    private ApplicationContext applicationContext;

    @Cacheable(key = "'all_codes'")
    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<Index> getIndexFromThirdPart(String id) {
        List<Index> indexes = new ArrayList<>();
        List<Map> maps = indexServiceFeignClient.getIndexFromThirdParty(id);
        for (Map map : maps) {
            Index index = new Index();
            index.setCode(map.get("code").toString());
            index.setName(map.get("name").toString());
            indexes.add(index);
        }
        return indexes;
    }

    /*
    * 这里用了 SpringContextUtil.getBean 去重新获取了一次 IndexService，
    * 为什么不直接在 fresh 方法里调用 remove, store 方法， 而要重新获取一次呢？
    * 这个是因为 springboot 的机制大概有这么个 bug吧. 从已经存在的方法里调用 redis 相关方法，
    * 并不能触发 redis 相关操作，所以只好用这种方式重新获取一次了。
    * */
    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<Index> fresh(String id) {
        IndexService indexService = applicationContext.getBean(IndexService.class);
        indexService.remove();
        return indexService.getIndexFromThirdPart(id);
    }

    //是否清楚所有的缓存
    @CacheEvict(allEntries = true)
    public void remove() {

    }

    private List<Index> third_part_not_connected(String id) {
        System.out.println("third_part_not_connected()");
        Index index = new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }
}
