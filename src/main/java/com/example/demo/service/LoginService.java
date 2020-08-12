package com.example.demo.service;

import com.example.demo.common.CommonUtils;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author y001
 */
@Service
public class LoginService {
    @Autowired
    private UserMapper userMapper;

    public Map<String,Object> login(Map<String,Object> param){
        Map<String,Object> resMap = new HashMap<>();
        //如果为空直接返回
        if (CommonUtils.isEmpty(param.get("username"))){
            resMap.put("code",200);
            resMap.put("message","用户名为空");
        }
        if (CommonUtils.isMobileNO(String.valueOf(param.get("username")))){
            param.put("telephone",param.get("username"));
        }else {
            param.put("loginName",param.get("username"));
        }

        return resMap;
    }
}
