package com.demo;

import org.mapstruct.factory.Mappers;

/**
 * @author ZhangShaowei on 2019/7/16 17:13
 **/
public interface BaseMapper<T, S> {

    BaseMapper INSTANCE = Mappers.getMapper(BaseMapper.class);

    T toDto(S product);


}
