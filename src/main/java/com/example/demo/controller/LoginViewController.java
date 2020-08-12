package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.common.CommonUtils;
import com.example.demo.common.HttpHeaderInfo;
import com.example.demo.util.AgentUserKitUtils;
import com.example.demo.util.HttpClientResult;
import com.example.demo.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author y001
 * 登录
 */
@Controller
@RequestMapping("/login")
public class LoginViewController {
    @Autowired
    private HttpClientUtil httpClientUtil;
    @GetMapping("/logView")
    public ModelAndView loginView(@RequestParam Map<String,Object> param){
        ModelAndView mv = new ModelAndView();
        //验证码
        /*try {

            HttpClientResult httpClientResult = httpClientUtil.doPost("http://localhost:8079/test/client", param);
            //String jsonStr = String.valueOf(httpClientResult.getContent()) ;
            //HttpClientResult<Map<String,Object>> result = JSONObject.parseObject(jsonStr, new TypeReference<HttpClientResult<Map<String,Object>>>() {});
            Object parse = JSONObject.parse(httpClientResult.getContent().toString());
            Map<String,Object> map = (Map<String, Object>) parse;
            System.out.println(map);
            List<String> list = (ArrayList)map.get("list");
            list.add("dd");
            System.out.println(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
*/
        //mv.addObject("result",param);
        mv.setViewName("login/login");
        return mv;
    }

    @RequestMapping("/success")
    public ModelAndView success(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/main/success");
        return mv;
    }
    @ResponseBody
    @RequestMapping("/test")
    public Map<String,Object> test(HttpServletRequest request){
        Map<String,Object> resMap = new HashMap<>();
        String headersInfo = HttpHeaderInfo.getUserAgent(request);
        String userAgent = AgentUserKitUtils.getDeviceInfo(request);
        resMap.put("userAgent",userAgent);
        resMap.put("headInfo",headersInfo);
        return resMap;
    }
}
