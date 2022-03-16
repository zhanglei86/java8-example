package win.leizhang.java8example.juc.wbl;

import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * 批量异步任务，电商询价程序
 * Created by zealous on 2020/9/24.
 */
public class CompletionServiceTest {

    // 简单
    @Test
    public void test1() {
        // S1
        BigDecimal r1 = getPriceByS1();
        save("1", r1);
        // S2
        BigDecimal r2 = getPriceByS2();
        save("2", r2);
        // S3
        BigDecimal r3 = getPriceByS3();
        save("3", r3);
    }

    // 并发处理
    @Test
    public void test2() {
        ExecutorService cutor = Executors.newFixedThreadPool(4);
        Future<BigDecimal> f1 = cutor.submit(() -> getPriceByS1());
        Future<BigDecimal> f2 = cutor.submit(() -> getPriceByS2());
        Future<BigDecimal> f3 = cutor.submit(() -> getPriceByS3());

        cutor.execute(() -> {
            try {
                save("f1", f1.get());
                save("f2", f2.get());
                save("f3", f3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    // 并发，加阻塞队列，执行完的即可提交
    @Test
    public void test3() throws Exception {
        ExecutorService cutor = Executors.newFixedThreadPool(3);
        Future<BigDecimal> f1 = cutor.submit(() -> getPriceByS1());
        Future<BigDecimal> f2 = cutor.submit(() -> getPriceByS2());
        Future<BigDecimal> f3 = cutor.submit(() -> getPriceByS3());

        // 创建阻塞队列
        BlockingQueue<BigDecimal> bq = new LinkedBlockingDeque<>();
        cutor.execute(() -> {
            try {
                bq.put(f1.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        cutor.execute(() -> {
            try {
                bq.put(f2.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        cutor.execute(() -> {
            try {
                bq.put(f3.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 保存所有
        for (int i = 0; i < 3; i++) {
            BigDecimal r = bq.take();
            cutor.execute(() -> save("xxx", r));
        }
    }

    // 测试3的优化
    @Test
    public void test4() throws Exception {
        ExecutorService cutor = Executors.newFixedThreadPool(3);
        // 创建
        CompletionService<BigDecimal> cs = new ExecutorCompletionService<>(cutor);
        cs.submit(() -> getPriceByS1());
        cs.submit(() -> getPriceByS2());
        cs.submit(() -> getPriceByS3());

        System.out.println(LocalDateTime.now() + ", start");
        // 保存所有
        for (int i = 0; i < 3; i++) {
            BigDecimal r = cs.take().get();
            cutor.execute(() -> save("xxx", r));
        }
        System.out.println(LocalDateTime.now() + ", end");
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

    private BigDecimal getPriceByS1() {
        sleep(9, null);
        return new BigDecimal("100");
    }

    private BigDecimal getPriceByS2() {
        sleep(3, null);
        return new BigDecimal("50");
    }

    private BigDecimal getPriceByS3() {
        sleep(6, null);
        return new BigDecimal("500");
    }

    private void save(String str, BigDecimal bg) {
        System.out.println(LocalDateTime.now());
        System.out.printf("线程T%s, 值%s, 保存成功\n", str, bg);
    }
}
