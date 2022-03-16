package win.leizhang.java8example.juc.wbl;

import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * 烧水泡茶2
 * Created by zealous on 2020/9/24.
 */
public class CompletableFutureTest {

    @Test
    public void test1() {
        // 任务1 洗茶壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1:洗水壶...");
            sleep(1, null);
            System.out.println("T1:烧开水...");
            sleep(15, null);
        });

        // 任务2 洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2:洗茶壶...");
            sleep(1, null);
            System.out.println("T2:洗茶杯...");
            sleep(2, null);
            System.out.println("T2:拿茶叶...");
            sleep(1, null);
            return "龙井";
        });

        // 任务3 等待1和2都完成后，泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T3:拿到了茶叶:" + tf);

            System.out.println("T3:泡茶...");
            return "上茶:" + tf;
        });

        // 等待任务3执行结果
        System.out.println(f3.join());
    }

    private void sleep(int t, TimeUnit u) {
        if (Objects.isNull(u)) {
            u = TimeUnit.SECONDS;
        }
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        CompletableFuture<Integer> f0 = CompletableFuture
                .supplyAsync(() -> 7 / 0)
                .thenApply(r -> r * 10)
                .exceptionally(e -> 0);
        System.out.println(f0.join());
    }
}
