package com.yuyuoped.gmall.index.config;

import com.yuyuoped.gmall.index.feign.GmallPmsClient;
import com.yuyuoped.gmall.pms.entity.CategoryEntity;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Configuration
public class BloomFilterConfig {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private GmallPmsClient pmsClient;

    private static final String keyPrefix = "index:cates";

    @Bean
    public RBloomFilter<String> bloomFilter() {
        RBloomFilter<String> rBloomFilter = redissonClient.getBloomFilter("index:bf");
        rBloomFilter.tryInit(2000, 0.03);

        List<CategoryEntity> data = pmsClient.queryCategoriesByPid(0L).getData();
        if (!CollectionUtils.isEmpty(data)) {
            for (CategoryEntity categoryEntity : data) {
                bloomFilter().add(keyPrefix + categoryEntity.getId());
            }
        }
        return rBloomFilter;
    }
}
