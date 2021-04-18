package vn.plusplus.spring.threading;

public class ThreadExtend extends Thread{

    Main main;

    public ThreadExtend(Main main) {
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
