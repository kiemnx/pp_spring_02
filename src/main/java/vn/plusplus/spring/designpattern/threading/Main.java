package vn.plusplus.spring.designpattern.threading;

public class Main {

    public static Integer counter = 0;

    public static void main(String[] args) {

        Main main = new Main();

        ThreadExtend thread1 = new ThreadExtend(main);
        thread1.setName("java");
        thread1.start();
        System.out.println("Run thread 1 done");

//        thread1.stop();

        Thread thread2 = new Thread(new ThreadImplement(main));
        thread2.setName("python");
        thread2.start();
        System.out.println("Run thread 2 done");
    }

    public void incrementCounter(){
        //Chuyen tien, tru tien

        //Dong bo du lieu
        synchronized (this){
            Main.counter += 1;
            System.out.println(Thread.currentThread().getName() +": counter=" + Main.counter);
        }
    }

    synchronized void incrementCounter2(){
        //Chuyen tien, tru tien

        //Dong bo du lieu
        Main.counter += 1;
        System.out.println(Thread.currentThread().getName() +": counter=" + Main.counter);
    }
}