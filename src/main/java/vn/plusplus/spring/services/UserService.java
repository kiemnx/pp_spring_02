package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.plusplus.spring.controller.request.LoginRequest;
import vn.plusplus.spring.controller.request.RegisterRequest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Service
public class UserService{

    @Autowired
    Connection connection;

    public String login(LoginRequest request){
        return "Success";
    }

    public String register(RegisterRequest register){
        //Check phone
        if(register.getPhone() == null || register.getPhone() == "" || register.getPhone().length() != 10){
            return "So dien thoai khong chinh xac";
        }
        //Check email
        if(StringUtils.isEmpty(register.getEmail()) || !register.getEmail().contains("@gmail.com")){
            return "Email khong chinh xac";
        }
        //Check pass
        if(StringUtils.isEmpty(register.getPass()) || register.getPass().length() < 8){
            return "Mat khau chua dap dung";
        }

        //Kiem tra SDT hoac email da ton tai hay chua
        String sql = "SELECT * FROM user WHERE user_name='" + register.getPhone()+ "'" +
                " OR email='" + register.getEmail() +"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()){
                return "So dien thoai hoac email ton tai";
            }

            //Insert new user
            sql = "insert into user(`user_name`, `password`, `email`) " +
                    "VALUES ('"+ register.getPhone() +"', '"+register.getPass()+"', '"+register.getEmail()+"');";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return "Register fail: internal error";
        }
        return "Success";
    }
}
