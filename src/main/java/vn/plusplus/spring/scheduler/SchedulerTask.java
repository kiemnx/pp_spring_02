package vn.plusplus.spring.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import vn.plusplus.spring.services.BookService;
import vn.plusplus.spring.services.UserService;

@Component
public class SchedulerTask {


    @Autowired
    BookService bookService;

//    @Scheduled(cron = "0/10 * * * * ?")
    public void runCronJob(){
        System.out.println(Thread.currentThread().getName() + " Cron: " + System.currentTimeMillis()/1000);
        bookService.testAsync();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " Cron: " + System.currentTimeMillis()/1000);
    }

//    @Scheduled(fixedDelay = 1000)
    public void runFixDelay(){
        System.out.println("Fix delay: " + System.currentTimeMillis()/1000);
    }

//    @Scheduled(fixedRate = 2000)
    public void runFixRate(){
        System.out.println("Fix rate: " + System.currentTimeMillis()/1000);

    }
}
