package com.yuyuoped.gmall.search.feign;


import com.yuyuoped.gmall.wms.api.GmallWmsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("wms")
public interface GmallWmsClient extends GmallWmsApi {
}
