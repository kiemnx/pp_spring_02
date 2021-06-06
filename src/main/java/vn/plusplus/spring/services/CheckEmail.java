package vn.plusplus.spring.services;

public class CheckEmail {
    public static boolean checkEmail(String email){
        Boolean result = email.endsWith("@gmail.com");
        //

        if(result){
            boolean test2 = email.startsWith("plusplus.");
            if(test2){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
