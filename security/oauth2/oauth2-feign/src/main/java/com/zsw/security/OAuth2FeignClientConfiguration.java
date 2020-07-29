package com.zsw.security;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * OAuth2 FeignClient Configuration
 *
 * @author ZhangShaowei on 2018/6/1 16:14
 **/
@Configuration
public class OAuth2FeignClientConfiguration {

    /**
     * {@link org.springframework.cloud.netflix.feign.FeignClient} 拦截器 增加 oauth 头消息
     *
     * @param oAuth2RestTemplate
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2RestTemplate oAuth2RestTemplate) {
        return template -> {
            OAuth2AccessToken accessToken = oAuth2RestTemplate.getAccessToken();
            template.header(HttpHeaders.AUTHORIZATION, accessToken.getTokenType() + " " + accessToken.getValue());
        };
    }


}