package vn.plusplus.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.plugin.util.UIUtil;

import java.util.UUID;

@RestController
@RequestMapping(value = "/redis")
public class RedisController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping(value = "/add-key/{id}")
    public String addKey(@PathVariable(name = "id") String id){
        String token = UUID.randomUUID().toString();
        String value = "userId=" + id +",userName=Kiemnx";
        redisTemplate.opsForValue().set(token, value);
        return token;
    }
    //Login => tim user info => sinh token => insert DB + push vao redis key=token, value la user info

    //Khi co request kem token => get token trong redis,
    // neu co => lay dc user info
    // neu khong => truy van mysql
    @GetMapping(value = "/get-key/{token}")
    public String getKey(@PathVariable(name = "token") String token){
        //Query trong bang token => userId => query bang user => user info
        String value = (String) redisTemplate.opsForValue().get(token);

        return value;
    }
}
