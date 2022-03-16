package com.example.account.service.rabbitmq;

import com.example.account.RabbitMQConfig;
import com.example.account.dto.response.UserResponse;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQService implements IRabbitMQService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMQConfig config;

    @Override
    public void send(List<UserResponse> users) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), users);
    }

}