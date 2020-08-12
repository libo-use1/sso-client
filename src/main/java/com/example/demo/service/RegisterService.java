package com.example.demo.service;

/**
 * @author y001
 */

import com.alibaba.fastjson.JSONObject;
import com.example.demo.util.HttpClientResult;
import com.example.demo.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册
 * @author y001
 */
@Service
public class RegisterService {
    @Autowired
    private HttpClientUtil httpClientUtil;
    /**
     * 服务端注册验证
     * @param param
     * @return
     */
    public Map<String,Object> doRegister(Map<String,Object> param){
        Map<String,Object> resMap = new HashMap<>();
        try {
            HttpClientResult httpClientResult = httpClientUtil.doPost("http://localhost:8079/register/doRegister", param);
            //String jsonStr = String.valueOf(httpClientResult.getContent()) ;
            //HttpClientResult<Map<String,Object>> result = JSONObject.parseObject(jsonStr, new TypeReference<HttpClientResult<Map<String,Object>>>() {});
            Object parse = JSONObject.parse(httpClientResult.getContent().toString());
            Map<String,Object> map = (Map<String, Object>) parse;
            resMap.putAll(map);
        } catch (Exception e) {
            resMap.put("code","failed");
            resMap.put("message","注册失败");
        }
        return resMap;
    }
}

