package com.example.account.service.rabbitmq;

import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;

import java.util.List;

public interface IRabbitMQService {
    void send(List<UserResponse> users);
}
