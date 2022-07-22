package com.yuyuoped.gmall.ums.mapper;

import com.yuyuoped.gmall.ums.entity.UserLoginLogEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登陆记录表
 * 
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:31:20
 */
@Mapper
public interface UserLoginLogMapper extends BaseMapper<UserLoginLogEntity> {
	
}
