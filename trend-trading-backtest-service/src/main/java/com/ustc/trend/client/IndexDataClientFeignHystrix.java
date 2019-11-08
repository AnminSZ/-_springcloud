package com.ustc.trend.client;

import cn.hutool.core.collection.CollectionUtil;
import com.ustc.trend.pojo.IndexData;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IndexDataClientFeignHystrix implements IndexDataClient {

    @Override
    public List<IndexData> getIndexData(String code) {
        System.out.println("IndexDataClientFeignHystrix happened");
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        return CollectionUtil.toList(indexData);
    }
}
