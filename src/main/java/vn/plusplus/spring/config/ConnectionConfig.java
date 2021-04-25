package vn.plusplus.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import vn.plusplus.spring.controller.HomeController;
import vn.plusplus.spring.services.UserInterface;
import vn.plusplus.spring.services.UserService;
import vn.plusplus.spring.services.UserService2;

import java.sql.Connection;
import java.sql.DriverManager;

@Configuration
public class ConnectionConfig {

    @Bean
    Connection getDBConnection() {
        Connection conn =null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            if (conn == null) {
                conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "1234");
            } else return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    @Bean
    UserService2 userService2(){
        return new UserService2(getDBConnection());
    }

    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
