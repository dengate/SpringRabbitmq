package com.example.account.dto.response;

public class UserResponse {
    public String username;
    public String storeName;

    public UserResponse(String username, String storeName) {
        this.username = username;
        this.storeName = storeName;
    }

    public UserResponse() {
    }
}
