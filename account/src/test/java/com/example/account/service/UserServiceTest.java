package com.example.account.service;

import com.example.account.dto.response.UserResponse;
import com.example.account.model.User;
import com.example.account.repository.UserRepository;
import com.example.account.service.rabbitmq.producer.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public User createUser(){
        User user = new User();
        user.setUsername("deneme");
        user.setPassword("testtest");
        return user;
    }

    public List<User> createUserList(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            userList.add(createUser());
        }
        return userList;
    }

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService service;


    static int counter = 1;

    @BeforeAll
    public static void beforeAll(){
        System.out.println("Tests start...");
    }

    @AfterAll
    public static void afterAll(){
        System.out.println("Tests end...");
    }

    @BeforeEach
    public void before(){
        assertNotNull(service);
        System.out.println("Test" + counter + "start...");
    }

    @AfterEach
    public void after(){
        System.out.println("Test" + counter + " end...");
        counter = counter +1;
    }

    @DisplayName("add Test")
    @Test
    void add() {
        System.out.println("UserServiceTest add() test start...");
        User user = createUser();
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(user);
        UserResponse userResponse;
        userResponse = service.add(user);
        assertNotNull(userResponse);
        assertNotNull(userResponse.storeName);
        assertNotNull(userResponse.username);
        System.out.println("UserServiceTest add() test end...");
    }

    @DisplayName("get Test")
    @Test
    void get() {
        System.out.println("UserServiceTest get() test start...");
        assertNotNull(service.get());
        System.out.println("UserServiceTest get() test end...");
    }

    @DisplayName("userModelToUserResponseDto Test")
    @Test
    void userModelToUserResponseDto() {
        System.out.println("UserServiceTest userModelToUserResponseDto() test start...");
        UserResponse response = service.userModelToUserResponseDto(createUser());
        assertNotNull(response);
        assertNotNull(response.username);
        assertNotNull(response.storeName);
        System.out.println("UserServiceTest userModelToUserResponseDto() test end...");
    }

    @DisplayName("userModelListToUserResponseList Test")
    @Test
    void userModelListToUserResponseList() {
        System.out.println("UserServiceTest userModelListToUserResponseList() test start...");
        List<UserResponse> response = service.userModelListToUserResponseList(createUserList());
        assertNotNull(response);
        System.out.println("UserServiceTest userModelListToUserResponseList() test end...");
    }

    @Test
    @Disabled("Not implemented yet")
    void getById() {
    }
}