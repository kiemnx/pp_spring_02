package vn.plusplus.spring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/home")
public class HomeController {

    @GetMapping(value = "/news/{newId}")
    public String getNewsById(@PathVariable(value = "newId") Integer newId){
        //truy van database lay chi tiet bai bao co id = newId
        return "Content news: " + newId;
    }

    @GetMapping(value = "/news")
    public String getNewByParams(@RequestParam(value = "id") Integer newId,
                                 @RequestParam(value = "language") String lang){
        //truy van database lay chi tiet bai bao co id = newId
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
    public LoginRequest login(@RequestBody LoginRequest request){
        String hardUser = "kiemnx";
        String hardPass = "123456";

        if(request.getPassword().equals(hardPass) && request.getUserName().equals(hardUser)){
            request.setPassword("xxxxxx");
            return request;
        } else {
            return request;
        }
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

@Getter @Setter
class LoginRequest{
    private String userName;
    private String password;
}
