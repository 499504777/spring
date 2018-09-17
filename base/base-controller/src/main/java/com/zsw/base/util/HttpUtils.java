package com.zsw.base.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : zhangshaowei
 * @filename: HttpUtils.java CopyRight (c) 2016 Company, Inc. All rights reserved.
 * @date : 2016年12月26日 上午11:21:10
 * <p>
 * 描 述 : http or https 请求utils 并不支持 https 再重定向
 */
public final class HttpUtils {
    /** */
    private static final String IP_SPILT = ",";

    /**  */
    private HttpUtils() {
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？ 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130, 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        // Enumeration<String> headerNames = request.getHeaderNames();
        // while (headerNames.hasMoreElements()){
        // System.out.println(headerNames.nextElement());
        // }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 设置代理/防火墙之后会有多个ip值,取第1个为用户真实ip
        if (ip.contains(IP_SPILT)) {
            ip = ip.split(IP_SPILT)[0];
        }
        return ip;
    }
}
