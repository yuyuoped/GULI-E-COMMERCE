package com.yuyuoped.gmall.pms.config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author yuyuoped
 * @since 2022/7/25
 */
@Configuration
@Data
@PropertySource("classpath:application-local.yml")
public class OssConfig {

}
