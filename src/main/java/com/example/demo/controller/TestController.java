package com.example.demo.controller;

import com.example.demo.util.RedisUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author y001
 * 测试
 */
@Api("测试")
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/view")
    public ModelAndView test(){
        ModelAndView mv = new ModelAndView();
        redisUtil.set("test","测试");
        redisUtil.set("userName","aaa");
        redisUtil.set("password","sss");
        mv.setViewName("test");
        return mv;
    }

    @RequestMapping("/testRedis")
    public ModelAndView testRedis(){
        ModelAndView mv = new ModelAndView();
        Map<String,Object> resMap = new HashMap<>();
        resMap.put("redis",redisUtil);
        resMap.put("userName",redisUtil.get("test"));
        resMap.put("password",redisUtil.get("password"));
        if (!("".equals(redisUtil.get("userName"))||null==redisUtil.get("userName"))){
            redisUtil.del("userName");
        }
        resMap.put("test",redisUtil.get("userName"));
        redisUtil.set("resMap",resMap);
        mv.addObject("result",resMap);
        mv.setViewName("testRedis");
        return mv;
    }
}
