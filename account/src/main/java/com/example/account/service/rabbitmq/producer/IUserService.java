package com.example.account.service.rabbitmq.producer;

import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;

import java.util.List;

public interface IUserService {
    UserResponse add(User user);

    List<UserResponse> get();

    UserResponse userModelToUserResponseDto(User user);

    List<UserResponse> userModelListToUserResponseList(List<User> users);

}
