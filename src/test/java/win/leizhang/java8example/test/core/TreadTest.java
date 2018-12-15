package win.leizhang.java8example.test.core;

import org.junit.Test;

/**
 * Created by zealous on 2018/12/15.
 */
public class TreadTest {
    public final int value = 4;

    @Test
    public void test1() {
        Runnable r = () -> System.out.println("Hello!");
        r.run();
    }

    @Test
    public void test2() {
        doIt();
    }

    private static void doIt() {
        int value = 6;
        Runnable r = new Runnable() {
            public final int value = 5;

            public void run() {
                int value = 10;
                System.out.println(this.value);
            }
        };
        r.run();
    }
}
