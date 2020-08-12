package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author y001
 */
@Mapper
public interface UserMapper {
    /**
     * 通过用户名或手机号获取用户信息
     * @param param
     * @return
     */
    Map<String,Object> getUserLogin(Map<String, Object> param);

    /**
     * 用户注册
     * @param param
     * @return
     */
    int register(Map<String, Object> param);

    /**
     * 修改个人信息
     * @param param
     * @return
     */
    int updateUser(Map<String, Object> param);
}
