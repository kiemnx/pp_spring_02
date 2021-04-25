package vn.plusplus.spring.services;

import vn.plusplus.spring.controller.request.LoginRequest;

import java.sql.Connection;

public class UserService2 implements UserInterface {

    private final Connection connection;

    public UserService2(Connection connection) {
        this.connection = connection;
    }

    public String login(LoginRequest request){
        return "Success 2";
    }
}
