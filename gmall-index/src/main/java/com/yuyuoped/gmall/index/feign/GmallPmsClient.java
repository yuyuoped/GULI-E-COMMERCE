package com.yuyuoped.gmall.index.feign;

import com.yuyuoped.gmall.pms.api.GmallPmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("pms")
public interface GmallPmsClient extends GmallPmsApi {
}
