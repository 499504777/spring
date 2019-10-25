package com.zsw.demo;

import lombok.*;

import java.util.List;

/**
 * @author ZhangShaowei on 2019/8/15 14:29
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;

    private String name;

    @Singular
    private List<Product> products;

}