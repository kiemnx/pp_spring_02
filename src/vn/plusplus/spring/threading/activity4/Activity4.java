package vn.plusplus.spring.threading.activity4;

import java.util.*;

public class Activity4 {
    public static Map<String, String> maps = new HashMap<>();
    public static String key = "";
    public static boolean flag = false;
    public static void main(String[] args) {
        maps.put("Monday", "Thu 2");
        maps.put("Tuesday", "Thu 3");
        maps.put("Wednesday", "Thu 4");
        maps.put("Thursday", "Thu 5");
        maps.put("Friday", "Thu 6");
        maps.put("Saturday", "Thu 7");
        maps.put("Sunday", "Chu nhat");

        Thread getKey = new Thread(new GetKey());
        Thread getValue = new Thread(new GetValue());
        getKey.start();
        getValue.start();
    }
}

class GetKey implements Runnable{
    @Override
    public void run() {
        Random rd = new Random();
        Object[] keyList = Activity4.maps.keySet().toArray();
        while (true){
            Activity4.key = keyList[rd.nextInt(keyList.length)].toString();
            System.out.println(Thread.currentThread().getName() + ": Key: " + Activity4.key);
            Activity4.flag = true;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class GetValue implements Runnable{
    @Override
    public void run() {
        while (true){
            if(Activity4.flag == true){
                String value = Activity4.maps.get(Activity4.key);
                System.out.println(Thread.currentThread().getName() + ": Value: " + value);
                Activity4.flag = false;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
