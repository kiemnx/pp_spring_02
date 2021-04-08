package vn.plusplus.spring.designpattern.factory;

public class VietcomBank implements Bank {
    @Override
    public String getBankName() {
        return "Vietcombank";
    }
}
