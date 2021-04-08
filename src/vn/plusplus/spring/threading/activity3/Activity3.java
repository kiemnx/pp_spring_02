package vn.plusplus.spring.threading.activity3;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Activity3 {
    public static int sleep = 2000; //sleep in 2 seconds
    public static BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

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
            Random r = new Random();
            Integer year = r.nextInt(999) + 1000;
            System.out.println(Thread.currentThread().getName() +
                    ": Gen ra so " + year);
            try {
                Activity3.queue.put(year);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(Activity3.sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CheckNumber extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                Integer year = Activity3.queue.poll(100, TimeUnit.MILLISECONDS);
                if(year != null){
                    if (year % 4 == 0 && year % 100 != 0) {
                        System.out.println(Thread.currentThread().getName() +
                                ": So " + year + " la nam nhuan.");
                    } else {
                        System.out.println(Thread.currentThread().getName() +
                                ": So " + year + " KO la nam nhuan.");
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Sleep
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
