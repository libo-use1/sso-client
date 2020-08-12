package com.example.demo.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class VrifyCodeUtil {
    @Autowired
    RedisUtil redisUtil;
    /**
     * 验证码验证
     */

    public boolean imgvrifyControllerDefaultKaptcha(HttpServletRequest request, String parameter){
        //获取用户设备信息
        String deviceInfo = AgentUserKitUtils.getDeviceInfo(request);
        String vrifyCode = MD5Utils.getMD5Code(deviceInfo);
        String captchaId = (String) redisUtil.get(vrifyCode);
        if (!captchaId.equals(parameter)) {
            return false;
        } else {
            return true;
        }
    }
}
