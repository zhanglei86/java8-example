package win.leizhang.java8example.test.core;

import org.junit.Test;

/**
 * Created by zealous on 2018/12/15.
 */
public class CoreTest {

    @Test
    public void test1() {
        int availProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("avail processors count:" + availProcessors);
    }

}
