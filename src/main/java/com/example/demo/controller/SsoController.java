//package com.example.demo.controller;
//
//
//import com.example.demo.util.RedisUtil;
//import com.google.code.kaptcha.impl.DefaultKaptcha;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.StringUtils;
//import org.springframework.web.bind.annotation.*;
//
//import javax.imageio.ImageIO;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//
//@Controller
//@RequestMapping("/sso")
//public class SsoController {
//    @Autowired
//    private DefaultKaptcha defaultKaptcha;
//    @Autowired
//    private IUserService userService;
//    @Autowired
//    private RedisUtil redisUtil;
//
//
//    public static final Logger LOGGER = LogManager.getLogger();
//
//    /**
//     * 生成验证码
//     */
//    @RequestMapping("/getCode")
//    public void defaultKaptcha(HttpServletRequest request,HttpServletResponse httpServletResponse) throws Exception {
//        byte[] captchaChallengeAsJpeg = null;
//        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
//        try {
//            //获取用户设备信息
//            String deviceInfo = AgentUserKitUtils.getDeviceInfo(request);
//            String vrifyCode = MD5Utils.getMD5Code(deviceInfo);
//            //生产验证码字符串并保存到session中
//            String createText = defaultKaptcha.createText();
//            redisUtil.set(vrifyCode, createText);
//            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
//            BufferedImage challenge = defaultKaptcha.createImage(createText);
//            ImageIO.write(challenge, "jpg", jpegOutputStream);
//        } catch (IllegalArgumentException e) {
//            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return;
//        }
//        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
//        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
//        httpServletResponse.setHeader("Cache-Control", "no-store");
//        httpServletResponse.setHeader("Pragma", "no-cache");
//        httpServletResponse.setDateHeader("Expires", 0);
//        httpServletResponse.setContentType("image/jpeg");
//        ServletOutputStream responseOutputStream =
//                httpServletResponse.getOutputStream();
//        responseOutputStream.write(captchaChallengeAsJpeg);
//        responseOutputStream.flush();
//        responseOutputStream.close();
//    }
//
//    /**
//     * 用户名登录
//     *
//     * @param
//     * @param
//     * @return
//     */
//    @PostMapping(value = "/doLogin", produces = {"application/json;charset=UTF-8"})
//    public String doLogin(Model model, String username, String fromUrl, String password, String code, HttpServletRequest request, HttpServletResponse response) {
//        String result = userService.login(username, password, code, request, response);
//        if (result == null) {
//            //重定向到
//            return "redirect:" + fromUrl;
//        } else {
//            model.addAttribute("result", result);
//            model.addAttribute("fromUrl", fromUrl);
//            return "login";
//        }
//    }
//
//    //验证
//    @ResponseBody
//    @GetMapping("/validate")
//    public HttpClientResult<User> getUserInfo(@RequestParam(value = "token", required = false) String token) {
//        if (!StringUtils.isEmpty(token)) {
//            User user = (User) redisUtil.get(token);
//            if (user != null) {
//                return new HttpClientResult(200, user);
//            } else if ((user=userService.getUserInfo(token)) !=null) {
//                return new HttpClientResult(200, user);
//            } else {
//                return new HttpClientResult(400, null);
//            }
//        } else {
//            return new HttpClientResult(400, null);
//        }
//    }
//
//    /**
//     * 发送手机验证码
//     */
//    @GetMapping("/getPhoneCode")
//    public void sengPhoneCode(String phone) {
//        String code = QcloudSmsUtils.getCode();//获取随机验证码
//        redisUtil.set(phone, code, 60);
//        try {
//            //调用接口方法，发送短信到手机 --phone接收短信的手机号码
//            QcloudSmsUtils.sendCode(phone, code);
//        } catch (Exception e) {
//            LOGGER.error("发送手机验证码错误", e);
//        }
//    }
//
//    /**
//     * 注册-- 验证手机验证码
//     *
//     * @param phone
//     * @param code
//     * @return
//     */
//    @PostMapping("/register/checkPCode")
//    public String checkPCode(Model model, String phone, String code) {
//        String result = userService.checkPCode(phone, code);
//        if (result == null) {
//            model.addAttribute("phone", phone);
//            //跳转完善信息页面
//            return "register";
//        }
//        model.addAttribute("result", result);
//        //返回手机注册页
//        return "pregister";
//    }
//
//    /**
//     * 注册---完善信息
//     *
//     * @param user
//     * @return
//     */
//    @PostMapping("/register")
//    public String register(Model model, User user) {
//        String result = userService.register(user);
//        if (result == null) {
//            return "login";
//        }
//        model.addAttribute("result", result);
//        return "register";
//    }
//
//    /**
//     * 退出登录
//     */
//    @GetMapping("/outLogin")
//    public String outLogin(@CookieValue(required = false, value = "token") Cookie cookie, HttpServletResponse response) {
//        userService.outLogin(cookie, response);
//
//        return "login";
//    }
//
//
//    /**
//     * 忘记密码
//     */
//    @PostMapping("/forgetPassword")
//    public String forgetPassword(Model model, String phone, String code, String newPassword,@CookieValue(required = false, value = "token") Cookie cookie) {
//        String result = userService.forgetPassword(phone, code, newPassword,cookie);
//        if (result == null) {
//            return "login";
//        }
//        model.addAttribute("result", result);
//        return "forget";
//    }
//
//
//}
