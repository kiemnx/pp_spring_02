package vn.plusplus.spring.activity3;

import java.util.Random;

public class Activity3 {
    public static int sleep = 2000; //sleep in 2 seconds
    public static int year;
    public static boolean flag = false;

    public static void main(String[] args) {
        CheckNumber m1 = new CheckNumber();
        GenNumber m2 = new GenNumber();
        m1.start();
        m2.start();
    }

}

class GenNumber extends Thread {
    @Override
    public void run() {
        while (true) {
//            if(Activity3.flag == false){
                Random r = new Random();
                Activity3.year = r.nextInt(999) + 1000;
                System.out.println(Thread.currentThread().getName() +
                        ": Gen ra so " + Activity3.year);
                Activity3.flag = true;
                try {
                    Thread.sleep(Activity3.sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
        }
    }
}

class CheckNumber extends Thread {
    @Override
    public void run() {
        while (true) {
            if (Activity3.flag == true) {
                if (Activity3.year % 4 == 0 && Activity3.year % 100 != 0) {
                    System.out.println(Thread.currentThread().getName() +
                            ": So " + Activity3.year + " la nam nhuan.");
                } else {
                    System.out.println(Thread.currentThread().getName() +
                            ": So " + Activity3.year + " KO la nam nhuan.");
                };
                Activity3.flag = false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
