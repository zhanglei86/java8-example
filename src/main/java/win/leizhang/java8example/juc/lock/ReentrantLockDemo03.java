package win.leizhang.java8example.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock(xx)的使用
 * description: tryLock()方法立刻返回当前获取情况。tryLock(long time, TimeUnit unit)等待一定的时间，返回获取情况。
 * Created by zealous on 2019-02-25.
 */
public class ReentrantLockDemo03 implements Runnable {
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                System.out.println(Thread.currentThread().getName() + " 获取当前lock锁");
                TimeUnit.SECONDS.sleep(4);
            } else {
                System.out.println(Thread.currentThread().getName() + " 获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockDemo03 reentrantLockDemo = new ReentrantLockDemo03();
        Thread thread01 = new Thread(reentrantLockDemo, "thread01");
        Thread thread02 = new Thread(reentrantLockDemo, "thread02");
        thread01.start();
        thread02.start();
    }
}
