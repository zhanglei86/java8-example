package win.leizhang.java8example.test;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by zealous on 2018/11/22.
 */
@Deprecated
public class TestString {

    @Test
    public void testJson() {
        String str = JSON.toJSONString(null);
        System.out.println(str);
    }

    @Test
    public void test1() {
        String codeInt = RandomUtils.nextInt(100, 200) + "";
        double codeDouble = RandomUtils.nextDouble();
        long codeLong = RandomUtils.nextLong();

        Collections cls;
        Collection<List> cl;
        Stream<List> stream;
        System.out.println("lambda");

        List<String> list = Arrays.asList("dddd", "bb", "ccc", "a");
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        list.sort((str1, str2) -> (str2.length() - str1.length()));

        System.out.println("ok");
    }

    @Test
    public void test2() {
        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run==>t1");
            }
        });
        td.start();

        Thread td2 = new Thread(() -> System.out.println("run==>t2"));
        td2.start();
    }


    @Test
    public void testHelloWeird() {
        // 另类表达
        System.out.println(randomString(-229985452) + " " + randomString(-147909649));
    }

    private static String randomString(int i) {
        Random ran = new Random(i);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int k = ran.nextInt(27);
            if (k == 0)
                break;

            sb.append((char) ('`' + k));
        }

        return sb.toString();
    }
}
