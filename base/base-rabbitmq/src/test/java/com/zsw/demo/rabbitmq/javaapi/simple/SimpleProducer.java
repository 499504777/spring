package com.zsw.lesson.rabbitmq.javaapi.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.zsw.lesson.rabbitmq.utils.RabbitMQUtils;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author ZhangShaowei on 2019/8/26 10:31
 **/
public class SimpleProducer {

    @SneakyThrows
    public static void main(String[] args) {
        ConnectionFactory factory = RabbitMQUtils.create();

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String message;
        while (!Objects.equals(message = reader.readLine(), "exit")) {
            channel.basicPublish(SimpleConsumer.EXCHANGE_NAME, "zsw.test", null, message.getBytes(UTF_8));
        }

        channel.close();
        connection.close();

    }

}
