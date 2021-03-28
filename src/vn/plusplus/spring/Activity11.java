package vn.plusplus.spring;

public class Activity11 {
    public static int maxNt = 1;
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis() + ": START: Bat dau tim");
        CheckNumber number = new CheckNumber();

        Thread thread1 = new Thread(new ThreadCheckNumber(number, 1, 50000));
        Thread thread2 = new Thread(new ThreadCheckNumber(number, 50001, 100000));
        Thread thread3 = new Thread(new ThreadCheckNumber(number, 100001, 150000));
        Thread thread4 = new Thread(new ThreadCheckNumber(number, 150001, 200000));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}

class CheckNumber {
    boolean checkNto(int n){
        for(int i = 2; i<n; i++){
            if(n%i==0){
                return false;
            }
        }
        return true;
    }

    synchronized void updateMaxNto(int n){
        if(n > Activity11.maxNt){
            Activity11.maxNt = n;
            if(Activity11.maxNt == 199999){
                System.out.println(System.currentTimeMillis() +": END: So nguyen to la: " + Activity11.maxNt);
            }
        }
    }
}

class ThreadCheckNumber implements Runnable{
    CheckNumber checkNumber;
    int start, end;
    public ThreadCheckNumber(CheckNumber checkNumber, int start, int end) {
        this.checkNumber = checkNumber;
        this.start = start;
        this.end = end;
    }
    @Override
    public void run() {
        for(int i=start; i<=end; i++){
            if(checkNumber.checkNto(i)){
                checkNumber.updateMaxNto(i);
            }
        }
    }
}


class ThreadActivity implements Runnable{

    int STT;

    public ThreadActivity(int STT) {
        this.STT = STT;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ ": STT=" + STT);
    }
}