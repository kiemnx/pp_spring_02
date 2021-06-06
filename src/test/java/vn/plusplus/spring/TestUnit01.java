package vn.plusplus.spring;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import vn.plusplus.spring.services.CheckEmail;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestUnit01 {


    @Test
    public void testBryptEncode(){
        String rawPassword = "123456";
        String encodePass = "$2a$10$xLATF0TZegKO/EbsGtwIKOxnAmEPbtGi7j4vwGOT8Rlb2FAM5.H8a";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean result = encoder.matches(rawPassword, encodePass);

        Assert.assertEquals(true,  result);
    }
    @Test
    public void testCheckEmail(){
        String email = "plusplus.kiemnx@gmail.com";
        Boolean result = CheckEmail.checkEmail(email);
        Assert.assertEquals(false, result);
    }

}
