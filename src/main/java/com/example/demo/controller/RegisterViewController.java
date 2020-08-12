package com.example.demo.controller;

import com.example.demo.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author y001
 * 注册页面
 */
@Controller
@RequestMapping("/register")
public class RegisterViewController {
    @Autowired
    private RegisterService registerService;
    /**
     * 注册页面跳转
     * @return
     */
    @GetMapping("/reg")
    public String register(){
        return "register/register";
    }

    @ResponseBody
    @PostMapping("/doRegister")
    public Map<String,Object> doRegister(@RequestParam Map<String,Object> param) {
        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> stringObjectMap = registerService.doRegister(param);
        resMap.putAll(stringObjectMap);
        return resMap;
    }
}
