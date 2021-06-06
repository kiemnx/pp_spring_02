package vn.plusplus.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.plusplus.spring.controller.request.LoginRequest;
import vn.plusplus.spring.controller.request.RegisterRequest;
import vn.plusplus.spring.entity.UserEntity;
import vn.plusplus.spring.repository.UserRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

//    @Autowired
    Connection connection;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByUserName(s);
        if(userEntity == null){
            throw new RuntimeException("User " + s + " not found");
        }

        List<GrantedAuthority> grantList = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(userEntity.getRole());
        grantList.add(authority);

        UserDetails userDetails = new User(userEntity.getUserName(), userEntity.getPassword(), grantList);

        return userDetails;
    }
    public void register(String username, String password){
//        bCryptPasswordEncoder.matches("abc@1234", "$2a$10$S3Yi.G5M.2iQuaWP5xOUSOALq1CNTR0BxhtOwMMIT/zxLqGTpoQGq");
        if(CheckEmail.checkEmail(username)) {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserName(username);

            userEntity.setPassword(bCryptPasswordEncoder.encode(password));
            userEntity.setRole("ROLE_USER");
            userRepository.save(userEntity);
        }
    }
}
