package vn.plusplus.spring.threading;

public class ThreadImplement implements Runnable {
    Main main;

    public ThreadImplement(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        while (true){
            main.incrementCounter();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
