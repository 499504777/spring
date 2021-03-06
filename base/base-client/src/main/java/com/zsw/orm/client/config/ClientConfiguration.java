package com.zsw.orm.client.config;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2017/11/6 16:07
 * <p>
 * {@link org.springframework.cloud.openfeign.ribbon.FeignRibbonClientAutoConfiguration}
 */
@Setter
//@ConfigurationProperties(prefix = "feign.httpclient")
@Configuration
@EnableHystrix
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = "com")
public class ClientConfiguration {

    /**
     *
     */
    private Integer maxConnections = 20;

    /**
     *
     */
    private Integer maxConnectionsPerRoute = 2;

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ClientConfiguration.class);


//    @Bean
//    public OkHttpClient okHttpClient() {
//        return new okhttp3.OkHttpClient.Builder()
//                .readTimeout(60, TimeUnit.SECONDS)
//                .connectTimeout(60, TimeUnit.SECONDS)
//                .writeTimeout(120, TimeUnit.SECONDS)
//                .retryOnConnectionFailure(true)
//                .connectionPool(new ConnectionPool(maxConnections, 5, TimeUnit.MINUTES))
//                // .addInterceptor();
//                .build();
//    }

//    @Bean
//    public HttpClient httpClient() {
//        return HttpClientBuilder.create()
//                .setConnectionTimeToLive(900L, TimeUnit.SECONDS)
//                .setMaxConnTotal(this.maxConnections)
//                .setMaxConnPerRoute(this.maxConnectionsPerRoute)
//                .build();
//    }

    /**
     * FeignClient 默认使用 HttpURLConnection 每次请求都建立一个新的连接
     * 效率非常低。为了提高效率，可以通过连接池提高效率
     * 可以使用 appache httpclient  & OkHttpClient
     *
     * @return
     */
//    @Bean
//    public HttpClient httpClient() {
//        return HttpClientBuilder.create()
//                .setMaxConnTotal(this.maxConnections)
//                .setMaxConnPerRoute(this.maxConnectionsPerRoute)
//                .build();


//        logger.info("init feign httpclient configuration " );
//        // 生成默认请求配置
//        RequestConfig.Builder requestConfigBuilder = RequestConfig.custom();
//        // 超时时间
//        requestConfigBuilder.setSocketTimeout(5 * 1000);
//        // 连接时间
//        requestConfigBuilder.setConnectTimeout(5 * 1000);
//        RequestConfig defaultRequestConfig = requestConfigBuilder.build();
//        // 连接池配置
//        // 长连接保持30秒
//        final PoolingHttpClientConnectionManager pollingConnectionManager
//                = new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
//        // 总连接数
//        pollingConnectionManager.setMaxTotal(5000);
//        // 同路由的并发数
//        pollingConnectionManager.setDefaultMaxPerRoute(100);
//
//        // httpclient 配置
//        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
//        // 保持长连接配置，需要在头添加Keep-Alive
//        httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
//        httpClientBuilder.setConnectionManager(pollingConnectionManager);
//        httpClientBuilder.setDefaultRequestConfig(defaultRequestConfig);
//        HttpClient client = httpClientBuilder.build();
//
//
//        // 启动定时器，定时回收过期的连接
//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                //        System.out.println("=====closeIdleConnections===");
//                pollingConnectionManager.closeExpiredConnections();
//                pollingConnectionManager.closeIdleConnections(5, TimeUnit.SECONDS);
//            }
//        }, 10 * 1000, 5 * 1000);
//        logger.info("===== Apache httpclient 初始化连接池===");
//
//        return client;
//    }

}
