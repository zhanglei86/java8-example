package win.leizhang.java8example.oom.heap;

import java.util.concurrent.TimeUnit;

/**
 * cpu资源被大量占用
 * 参考: -[如何快速定位JVM中消耗CPU最多的线程？](https://mp.weixin.qq.com/s?__biz=MzI4MTY5NTk4Ng==&mid=2247489061&amp;idx=1&amp;sn=b26db4789cf459397d85311430aa9ee5&source=41#wechat_redirect) 你假笨  极客时间  2018-05-17
 * Created by zealous on 2019/1/28.
 */
public class CpuAreHeavilyOccupiedTest1 {

    public static void main(String[] args) {
        toRun();
    }

    private static void toRun() {
        // 线程，阻塞着的
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    try {
                        TimeUnit.MINUTES.sleep(40);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }

        // 线程，生效的
        Thread t = new Thread() {
            @Override
            public void run() {
                int i = 0;
                while (true) {
                    i = i / 10000;
                    i++;
                }
            }
        };
        t.setName("Busiest thread");
        t.start();
    }
}
