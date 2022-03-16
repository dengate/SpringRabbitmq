package com.example.account.service.rabbitmq.listener;

import com.example.account.dto.response.UserResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryListener {

    @RabbitListener(queues = "ahmet.queue")
    public void handleUsers(List<UserResponse> users){
        System.out.println(users);
    }
}
