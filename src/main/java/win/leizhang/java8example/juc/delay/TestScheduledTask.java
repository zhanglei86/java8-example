package win.leizhang.java8example.juc.delay;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zealous on 2020/7/29.
 */
public class TestScheduledTask {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(100);

/*
        for (int i = 10; i > 0; i--) {
            executor.schedule(new Runnable() {

                public void run() {
                    // TODO Auto-generated method stub
                    System.out.println(
                            "Work start, thread id:" + Thread.currentThread().getId() + " " + sdf.format(new Date()));
                }

            }, 4, TimeUnit.SECONDS);
        }
*/

        for (int i = 10; i > 0; i--) {
            int finalI = i;
            System.out.println(finalI);
            executor.schedule(() -> System.out.printf("Work start id=%s, thread id==>%s, dt==>%s\n", finalI, Thread.currentThread().getId(), sdf.format(new Date())), i, TimeUnit.SECONDS);
        }

    }
}
