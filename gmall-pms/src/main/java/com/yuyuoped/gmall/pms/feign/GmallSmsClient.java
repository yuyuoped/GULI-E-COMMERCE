package com.yuyuoped.gmall.pms.feign;

import com.yuyuoped.gmall.sms.api.SmsApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author yuyuoped
 * @since 2022/7/28
 */
@FeignClient("sms-service")
public interface GmallSmsClient extends SmsApi {
}
