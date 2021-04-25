package vn.plusplus.spring.designpattern.solid;

import org.springframework.beans.factory.annotation.Autowired;
import vn.plusplus.spring.designpattern.factory.VietcomBank;

public class BankService {
    @Autowired
    VietcomBank vietcomBank;

    public static void main(String[] args) {
        VietcomBank vcb = new VietcomBank();
    }
}
