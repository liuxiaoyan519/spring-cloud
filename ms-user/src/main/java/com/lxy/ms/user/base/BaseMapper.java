package com.lxy.ms.user.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

//mapper基类
public interface BaseMapper<T> extends Mapper<T>,MySqlMapper<T> {
}
