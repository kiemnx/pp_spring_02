package vn.plusplus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.spring.controller.request.LoginRequest;
import vn.plusplus.spring.controller.request.RegisterRequest;
import vn.plusplus.spring.services.UserService;


@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register")
    public String register(@RequestBody RegisterRequest request){
        userService.register(request.getPhone(), request.getPass());
        return "OK";
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest request){
        String result = userService.login(request);
        return result;
    }

}

