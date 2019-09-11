/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anze.mq.spring.autoconfigure;

import com.anze.mq.spring.annotation.ConsumeMode;
import com.anze.mq.spring.annotation.MessageModel;
import com.anze.mq.spring.annotation.RocketMQMessageListener;
import com.anze.mq.spring.core.RocketMQListener;
import com.anze.mq.spring.support.DefaultRocketMQListenerContainer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.BeanDefinitionValidationException;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.StandardEnvironment;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
@EnableConfigurationProperties(RocketMQProperties.class)
public class ListenerContainerConfiguration implements ApplicationContextAware, SmartInitializingSingleton {
    private final static Logger log = LoggerFactory.getLogger(ListenerContainerConfiguration.class);

    private ConfigurableApplicationContext applicationContext;

    private AtomicLong counter = new AtomicLong(0);

    private StandardEnvironment environment;

    private RocketMQProperties rocketMQProperties;

    private ObjectMapper objectMapper;

    private Class<?> containerBeanClass = DefaultRocketMQListenerContainer.class;


    public ListenerContainerConfiguration(ObjectMapper rocketMQMessageObjectMapper,
                                          StandardEnvironment environment,
                                          RocketMQProperties rocketMQProperties) {
        this.objectMapper = rocketMQMessageObjectMapper;
        this.environment = environment;
        this.rocketMQProperties = rocketMQProperties;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    @Override
    public void afterSingletonsInstantiated() {
        Map<String, Object> beans = this.applicationContext.getBeansWithAnnotation(RocketMQMessageListener.class);

        if (Objects.nonNull(beans)) {
            beans.forEach(this::registerContainer);
        }
    }

    private void registerContainer(String beanName, Object bean) {
        Class<?> clazz = AopProxyUtils.ultimateTargetClass(bean);

        if (!RocketMQListener.class.isAssignableFrom(bean.getClass())) {
            throw new IllegalStateException(clazz + " is not instance of " + RocketMQListener.class.getName());
        }

        RocketMQMessageListener annotation = clazz.getAnnotation(RocketMQMessageListener.class);

        String consumerGroup = this.environment.resolvePlaceholders(annotation.consumerGroup());
        String topic = this.environment.resolvePlaceholders(annotation.topic());

        boolean listenerEnabled =
                (boolean) rocketMQProperties.getConsumer().getListeners().getOrDefault(consumerGroup, Collections.EMPTY_MAP)
                        .getOrDefault(topic, true);

        if (!listenerEnabled) {
            log.debug(
                    "Consumer Listener (group:{},topic:{}) is not enabled by configuration, will ignore initialization.",
                    consumerGroup, topic);
            return;
        }
        validate(annotation);

        String containerBeanName = String.format("%s_%s",
                DefaultRocketMQListenerContainer.class.getName(), counter.incrementAndGet());

        RootBeanDefinition beanDefinition = new RootBeanDefinition(containerBeanClass);
        beanDefinition.getPropertyValues().add("name", beanName);
        beanDefinition.getPropertyValues().add("rocketMQListener", bean);
        beanDefinition.getPropertyValues().add("annotation", annotation);
        beanDefinition.getPropertyValues().add("objectMapper", objectMapper);
        beanDefinition.getPropertyValues().add("properties", rocketMQProperties);

        GenericApplicationContext genericApplicationContext = (GenericApplicationContext) applicationContext;
        genericApplicationContext.registerBeanDefinition(containerBeanName, beanDefinition);

//        genericApplicationContext.registerBean(containerBeanName, DefaultRocketMQListenerContainer.class,
//                () -> createRocketMQListenerContainer(containerBeanName, bean, annotation));
        DefaultRocketMQListenerContainer container = genericApplicationContext.getBean(containerBeanName,
                DefaultRocketMQListenerContainer.class);
        if (!container.isRunning()) {
            try {
                container.start();
            } catch (Exception e) {
                log.error("Started container failed. {}", container, e);
                throw new RuntimeException(e);
            }
        }

        log.info("Register the listener to container, listenerBeanName:{}, containerBeanName:{}", beanName, containerBeanName);
    }

//    private DefaultRocketMQListenerContainer createRocketMQListenerContainer(String name, Object bean, RocketMQMessageListener annotation) {
//        DefaultRocketMQListenerContainer container = new DefaultRocketMQListenerContainer();
//
//        String nameServer = environment.resolvePlaceholders(annotation.nameServer());
//        nameServer = StringUtils.isEmpty(nameServer) ? rocketMQProperties.getNameServer() : nameServer;
//        String accessChannel = environment.resolvePlaceholders(annotation.accessChannel());
//        container.setNameServer(nameServer);
//        if (!StringUtils.isEmpty(accessChannel)) {
//            container.setOnsChannel(ONSChannel.valueOf(accessChannel));
//        }
//        container.setTopic(environment.resolvePlaceholders(annotation.topic()));
//        container.setConsumerGroup(environment.resolvePlaceholders(annotation.consumerGroup()));
//        container.setRocketMQMessageListener(annotation);
//        container.setRocketMQListener((RocketMQListener) bean);
//        container.setObjectMapper(objectMapper);
//        container.setName(name);  // REVIEW ME, use the same clientId or multiple?
//
//        return container;
//    }

    private void validate(RocketMQMessageListener annotation) {
        if (annotation.consumeMode() == ConsumeMode.ORDERLY &&
                annotation.messageModel() == MessageModel.BROADCASTING) {
            throw new BeanDefinitionValidationException(
                    "Bad annotation definition in @RocketMQMessageListener, messageModel BROADCASTING does not support ORDERLY message!");
        }
    }

}
