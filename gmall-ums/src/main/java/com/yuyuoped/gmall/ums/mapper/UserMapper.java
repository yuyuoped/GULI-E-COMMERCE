package com.yuyuoped.gmall.ums.mapper;

import com.yuyuoped.gmall.ums.entity.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户表
 * 
 * @author yuyuoped
 * @email yuyuoped999@gmail.com
 * @date 2022-07-22 23:31:22
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {
	
}
