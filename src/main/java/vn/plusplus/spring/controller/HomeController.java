package vn.plusplus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.spring.controller.request.LoginRequest;
import vn.plusplus.spring.services.UserInterface;
import vn.plusplus.spring.services.UserService;
import vn.plusplus.spring.services.UserService2;

import java.util.Date;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/news/{newId}")
    public String getNewsById(@PathVariable(value = "newId") Integer newId){
        //truy van database lay chi tiet bai bao co id = newId
        return "Content news: " + newId;
    }

    @GetMapping(value = "/news")
    public String getNewByParams(@RequestParam(value = "id") Integer newId,
                                 @RequestParam(value = "language") String lang){
        //truy van database lay chi tiet bai bao co id = newId
        System.out.println();
        return "Content news get by params: " + newId + " language: " + lang;
    }


    @GetMapping(value = "/news24h/{newName}")
    public String getContent(@PathVariable(value = "newName") String name){
        String id = name.substring(name.lastIndexOf("-"));
        return "Content: " + id;
    }

    @RequestMapping(value = "/current-time", method = RequestMethod.GET)
    public String getServerTime(){
        return String.valueOf(new Date());
    }

    @GetMapping(value = "/current-time-2")
    public String getServerTime2(){
        return "API 2: " + String.valueOf(new Date());
    }

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest request){
        String result = userService.login(request);
        return result;
    }
    @PutMapping(value = "/login")
    public LoginRequest loginPut(@RequestBody LoginRequest request){
        String hardUser = "kiemnx";
        String hardPass = "abc";

        if(request.getPassword().equals(hardPass) && request.getUserName().equals(hardUser)){
            request.setPassword("yyy");
            return request;
        } else {
            return request;
        }
    }

    @DeleteMapping(value = "/user")
    public String deleteUser(){
        return "Delete success";
    }
}

