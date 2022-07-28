package com.yuyuoped.gmall.sms.api;

import com.yuyuoped.gmall.common.bean.ResponseVo;
import com.yuyuoped.gmall.sms.vo.SkuSalesVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yuyuoped
 * @since 2022/7/28
 */

public interface SmsApi {
    @PostMapping("sms/skubounds/sales/save")
    ResponseVo<Object> saveSales(@RequestBody SkuSalesVo saleVo);
}
