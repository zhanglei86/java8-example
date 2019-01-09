package win.leizhang.java8example.test;

import com.alibaba.fastjson.JSON;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zealous on 2018/12/15.
 */
//@RunWith(SpringRunner.class)
public class BaseTestCase {

    public final Logger log = LoggerFactory.getLogger(this.getClass());

    private long beginTime;
    private long endTime;

    static {
        System.setProperty("currentAppName", "java8-example-testCase");
        // server端口
        System.setProperty("server.port", "9092");
    }

    @Before
    public void begin() {
        beginTime = System.currentTimeMillis();
    }

    @After
    public void end() {

        endTime = System.currentTimeMillis();

        System.err.println("");
        System.err.println("#######################################################");
        System.err.println("elapsed time : " + (endTime - beginTime) + "ms");
        System.err.println("#######################################################");
        System.err.println("");
    }

    public void printData(Object data) {
        String str;
        if (data instanceof String) {
            str = String.valueOf(data);
        } else {
            str = JSON.toJSONString(data);
        }
        System.err.println("data ==> " + str);
    }

    @Test
    public void testCase() {
        System.out.println("base testCase finish!");
    }

}
