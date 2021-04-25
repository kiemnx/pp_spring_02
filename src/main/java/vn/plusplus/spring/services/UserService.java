package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.spring.controller.request.LoginRequest;

import java.sql.Connection;

@Service
public class UserService implements UserInterface{

    @Autowired
    Connection connection;

    public String login(LoginRequest request){
        return "Success";
    }
}
