package win.leizhang.java8example.test.core.concurrent;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者和消费者，学习内存模型《由浅入深Java内存模型》。
 * <url>https://mp.weixin.qq.com/s/Z1-e3ahQDhwlTleUimDeuA</url>
 * Created by zealous on 2018/8/17.
 */
public class TestProductConsumer {

    @Test
    public void testRun() {
        BlockingQueue<Food> queue = new LinkedBlockingDeque<>(10);
        Producer[] p = new Producer[3];
        Consumer[] c = new Consumer[15];

        for (int i = 0; i < 3; i++) {
            p[i] = new Producer(queue);
        }
        for (int j = 0; j < 15; j++) {
            c[j] = new Consumer(queue);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 3; i++) {
            executorService.execute(p[i]);
        }
        for (int j = 0; j < 15; j++) {
            executorService.execute(c[j]);
        }

        try {
            Thread.sleep(1000 * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Food {
    private int id;

    public Food(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Producer implements Runnable {
    private boolean working = true;
    private BlockingQueue<Food> queue;
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue<Food> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (working) {
            int id = count.incrementAndGet();
            Food food = new Food(id);
            if (queue.offer(food)) {
                System.out.println(Thread.currentThread().getId() + "号员工将" + food.getId() + "号食物加入餐台");
            } else {
                System.out.println("餐台已满，" + food.getId() + "号食物无法加入");
            }

            try {
                Thread.sleep(1000 * 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        working = false;
    }
}

class Consumer implements Runnable {
    private boolean working = true;
    private BlockingQueue<Food> queue;

    public Consumer(BlockingQueue<Food> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Food food = queue.take();//take()方式，若队列中没有元素则线程被阻塞
                System.out.println(food.getId() + "号食物已被" + Thread.currentThread().getId() + "号顾客端走");
                Thread.sleep(1000 * 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}