package com.anze.mq.spring.api;

import java.io.Serializable;

/**
 * @author ZhangShaowei on 2019/9/11 16:23
 **/
public interface SendResult extends Serializable {

    String getTopic();

    String getMessageId();

}
