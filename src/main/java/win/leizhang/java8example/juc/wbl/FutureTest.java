package win.leizhang.java8example.juc.wbl;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * 烧水泡茶1
 * Created by zealous on 2020/9/23.
 */
public class FutureTest {

    FutureTask<String> ft1 = new FutureTask<>(new T1Task());
    FutureTask<String> ft2 = new FutureTask<>(new T2Task());

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        Thread t1 = new Thread(ft1);
        t1.start();
        Thread t2 = new Thread(ft2);
        t2.start();

        System.out.printf("ft1==>%s", ft1.get());
    }

    // T1Task需要执行的任务
    class T1Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T1:洗水壶...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T1:烧开水...");
            TimeUnit.SECONDS.sleep(15);
            // 获取T2线程的茶叶
            String tf = ft2.get();
            System.out.println("T1:拿到了茶叶:" + tf);

            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        }
    }

    // T2Task需要执行的任务
    static class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("T2:洗茶壶...");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("T2:洗茶杯...");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("T2:拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return "龙井";
        }
    }
}
