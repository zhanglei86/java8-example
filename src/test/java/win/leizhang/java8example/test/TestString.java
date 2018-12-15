package win.leizhang.java8example.test;

import org.junit.Test;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by zealous on 2018/11/22.
 */
@Deprecated
public class TestString {

    @Test
    public void test1() {

        Collections cls;
        Collection<List> cl;

        Stream<List> stream;

        System.out.println("lambda");


        Thread td = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run==>t1");
            }
        });
        td.start();

        Thread td2 = new Thread(() -> System.out.println("run==>t2"));
        td2.start();

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
}
