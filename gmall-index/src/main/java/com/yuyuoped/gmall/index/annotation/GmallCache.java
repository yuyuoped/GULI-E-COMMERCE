package com.yuyuoped.gmall.index.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GmallCache {

    //该缓存在redis中的前缀
    String prefix() default "gmall:cache:";

    //该缓存的过期时间(单位s)
    int timeout() default 24*60*60;

    //该缓存为避免雪崩而额外增加的随机时间最大值(单位s)
    int randomAdditionTime() default 60*60;

}
