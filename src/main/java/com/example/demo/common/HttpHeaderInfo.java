package com.example.demo.common;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author y001
 * 获取http请求头中信息
 */
public class HttpHeaderInfo {
    //private static HttpServletRequest request;

    /**
     * 获取请求头
     * @return
     */
    public static Map<String, Object> getHeadersInfo(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 获取user-agent
     * @return
     */
    public static String getUserAgent(HttpServletRequest request) {
        return request.getHeader("user-agent");
    }
}
