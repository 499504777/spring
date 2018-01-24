package com;

import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2017/9/15 13:42
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
public class ApiApplication {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}