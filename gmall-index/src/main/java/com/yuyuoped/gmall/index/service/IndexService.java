package com.yuyuoped.gmall.index.service;

import com.alibaba.csp.sentinel.cluster.request.ClusterRequest;
import com.yuyuoped.gmall.index.annotation.GmallCache;
import com.yuyuoped.gmall.index.feign.GmallPmsClient;
import com.yuyuoped.gmall.pms.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexService {

    private static final String keyPrefix = "index:cates:";

    @Autowired
    private GmallPmsClient gmallPmsClient;


    public List<CategoryEntity> queryCategoriesByPid(long pid) {
        return gmallPmsClient.queryCategoriesByPid(pid).getData();
    }

    @GmallCache(prefix = keyPrefix, timeout = 10*24*60*60, randomAdditionTime = 3*60*60)
    public List<CategoryEntity> queryLevel23ByParentId(Long pid) {
        return gmallPmsClient.queryLevel23ByParentId(pid).getData();
    }
}
