package com.yuyuoped.gmall.pms.vo;

import com.yuyuoped.gmall.pms.entity.SpuAttrValueEntity;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author yuyuoped
 * @since 2022/7/28
 */
public class SpuAttrValueVo extends SpuAttrValueEntity {

    public void setValueSelected(List<String> valueSelected) {
        if (CollectionUtils.isEmpty(valueSelected)) {
            this.setAttrValue(StringUtils.join(valueSelected, ","));
        }
    }
}
