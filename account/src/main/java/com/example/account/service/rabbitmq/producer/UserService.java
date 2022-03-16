package com.example.account.service.rabbitmq.producer;


import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;
import com.example.account.repository.UserRepository;
import com.example.account.service.rabbitmq.IRabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository repository;
    @Autowired
    private IRabbitMQService rabbit;

    @PostConstruct
    public void init(){
        get();
    }

    @Override
    public UserResponse add(User user){
            return userModelToUserResponseDto(repository.save(user));
    }

    @Override
    public List<UserResponse> get(){
        List<UserResponse> userResponseList = userModelListToUserResponseList(repository.findAll());
        for (int i = 0; i < 100; i++) {
            rabbit.send(userResponseList);
        }
        return userResponseList;
    }

    @Override
    public UserResponse userModelToUserResponseDto(User user){
        UserResponse response = new UserResponse();
        response.username = user.getUsername();
        response.storeName = response.username + " Store";
        return response;
    }

    @Override
    public List<UserResponse> userModelListToUserResponseList(List<User> users){
        List<UserResponse> userResponseList = new ArrayList<>();
        for (User user:users){
            userResponseList.add(userModelToUserResponseDto(user));
        }

        return userResponseList;
    }


}
