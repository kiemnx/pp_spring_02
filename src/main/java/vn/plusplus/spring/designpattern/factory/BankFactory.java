package vn.plusplus.spring.designpattern.factory;

public class BankFactory {
    public static Bank getBank(String bankType) {
        if(bankType.equals("TPB")){
            return new TPBank();
        }
        if(bankType.equals("VCB")){
            return new VietcomBank();
        }
        return null;
    }
}
