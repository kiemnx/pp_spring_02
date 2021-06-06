package vn.plusplus.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.spring.controller.request.LoginRequest;
import vn.plusplus.spring.entity.UserEntity;
import vn.plusplus.spring.repository.UserRepository;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
@RequestMapping(value = "/security")
public class TokeSecurity {

    private static final Logger logger = LoggerFactory.getLogger(TokeSecurity.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Integer expiredTime = 3 *60;

    @PostMapping(value = "/login")
    public String login(@RequestBody LoginRequest loginRequest){
        logger.info("========== Start received request username {}, password {}", loginRequest.getUserName(), loginRequest.getPassword());
        UserEntity userEntity = userRepository.findUserByUserName(loginRequest.getUserName());
        if(userEntity == null){
            logger.error("Username {} is not found", loginRequest.getUserName());
            throw new RuntimeException("Not found");
        }
        boolean check = bCryptPasswordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword());
        if(!check){
            logger.error("Password {} not match with username {}", loginRequest.getPassword(), loginRequest.getUserName());
            throw new RuntimeException("Login error");
        }

        String token = UUID.randomUUID().toString();
        userEntity.setToken(token);
        Timestamp expired = new Timestamp(System.currentTimeMillis() + expiredTime * 1000);
        userEntity.setExpiredTime(expired);
        userRepository.save(userEntity);
        logger.info("======== End request login with username {}", loginRequest.getUserName());
        return token;
    }

    @GetMapping(value = "/userInfo")
    public UserEntity getUserInfor(@RequestParam(name = "token") String token){
        UserEntity tokenUser = userRepository.findUserByToken(token);
        if(tokenUser == null){
            throw new RuntimeException("Token ko hop le");
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if(tokenUser.getExpiredTime().before(now)){
            throw new RuntimeException("Token het han");
        }
        return tokenUser;
    }
}
