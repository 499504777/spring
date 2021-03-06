package com.zsw.admin.config;

/**
 * AdminServerConfiguration
 *
 * @author ZhangShaowei on 2018/5/28 9:59
 **/
//@Configuration
//@ConditionalOnProperty(name = "spring.boot.admin.oauth2.enabled", havingValue = "true")
public class AdminOauth2Configuration {

//    /**
//     * logger
//     */
//    private static final Logger logger = LoggerFactory.getLogger(AdminOauth2Configuration.class);
//
//    @Value("${anze.orm.application.servername.oauth}")
//    private String oauthServerName;
//
//    @Value("${anze.orm.oauthclient.authorization}")
//    private String oauthclientAuthorization;
//
//    /**
//     *
//     */
//    @Value("${anze.orm.monitor.admin.clientId:admin-server}")
//    private String clientId;
//
//    /**
//     *
//     */
//    @Value("${anze.orm.monitor.admin.secret:admin-server}")
//    private String secret;
//
//    @Bean
//    public UserInfoRestTemplateFactory userInfoRestTemplateFactory(
//            ObjectProvider<List<UserInfoRestTemplateCustomizer>> customizers,
//            ObjectProvider<OAuth2ProtectedResourceDetails> details,
//            ObjectProvider<OAuth2ClientContext> oauth2ClientContext) {
//        logger.info("初始化 admin oauth2 相关: init UserInfoRestTemplateFactory for OAuth2RestTemplate");
//        return new DefaultUserInfoRestTemplateFactory(customizers, details, oauth2ClientContext);
//    }
//
//    @Bean
//    public OAuth2RestTemplate oAuth2RestOperations(UserInfoRestTemplateFactory userInfoRestTemplateFactory) {
//        return userInfoRestTemplateFactory.getUserInfoRestTemplate();
//    }
//
//
//    /**
//     * {@link org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerTokenServicesConfiguration}
//     * <p>
//     * {@link UserInfoRestTemplateFactory}
//     * 注入 UserInfoRestTemplateCustomizer , OAuth2ProtectedResourceDetails , OAuth2ClientContext
//     * <p>
//     * 这里配置的 accessToken server 地址 使用了微服务中的 服务名称 所以需要 配置 LoadBalancerInterceptor 解析
//     * <p>
//     * 若使用了 spring-retry 这里 是无法注入 LoadBalancerInterceptor 的
//     *
//     * @param loadBalancerInterceptor LoadBalancerInterceptor
//     * @return UserInfoRestTemplateCustomizer
//     */
//    @Bean
//    UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(LoadBalancerInterceptor loadBalancerInterceptor) {
//        return template -> {
//            List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
//            interceptors.add(loadBalancerInterceptor);
//            AccessTokenProviderChain accessTokenProviderChain = Stream
//                    .of(new AuthorizationCodeAccessTokenProvider(), new ImplicitAccessTokenProvider(),
//                            new ResourceOwnerPasswordAccessTokenProvider(), new ClientCredentialsAccessTokenProvider())
//                    .peek(tp -> tp.setInterceptors(interceptors))
//                    .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
//            template.setAccessTokenProvider(accessTokenProviderChain);
//        };
//    }
//
//
//    /**
//     * {@link this#userInfoRestTemplateCustomizer(LoadBalancerInterceptor)}
//     *
//     * @return OAuth2ProtectedResourceDetails
//     */
//    @Bean
//    public OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails() {
////        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
////
////        resource.setAccessTokenUri("http://" + this.oauthServerName + "/oauth/token");
////        // 开发测试下的账号，拥有所有 resources
////        resource.setClientId(this.clientId);
////        resource.setClientSecret(this.secret);
////        resource.setGrantType("client_credentials");
////        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
////        return resource;
//
//        String basic = new String(Base64.getDecoder().decode(this.oauthclientAuthorization));
//        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails() {
//        };
//        String[] split = basic.split(":");
//
//        resource.setAccessTokenUri("http://" + this.oauthServerName + "/oauth/token");
//        resource.setClientId(split[0]);
//        resource.setClientSecret(split[1]);
//        resource.setGrantType("client_credentials");
//        resource.setClientAuthenticationScheme(AuthenticationScheme.header);
//        return resource;
//    }
//
//    @Bean
//    public OAuth2ClientContext oAuth2ClientContext() {
//        return new DefaultOAuth2ClientContext();
//    }
//
//    /**
//     * cloud 服务之间使用了 oauth2 协议
//     * 在admin 发送到所有服务请求 headers 中添加 权限 Authorization 类型 默认 Bearer
//     *
//     * @param oAuth2RestTemplate
//     * @return
//     */
//    @Bean
//    public HttpHeadersProvider httpHeadersProvider(OAuth2RestTemplate oAuth2RestTemplate) {
//        return new OAuth2AuthorizationHttpHeadersProvider(oAuth2RestTemplate::getAccessToken);
//    }


}
