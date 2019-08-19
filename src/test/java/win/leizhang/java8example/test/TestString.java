package win.leizhang.java8example.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
        // 空
        String str = JSON.toJSONString(null);

        // 数组
        String str2 = "{\"userId\":100,\"userName\":\"xx\"}";
        List<Object> list = JSON.parseArray("[" + str2 + "]", Object.class);

        // 版本差异
        Map<String, Object> as = new HashMap<>();
        Map<String, Object> as1 = new HashMap<>();
        Map<String, Object> as2 = new HashMap<>();
        Map<String, Object> as3 = new HashMap<>();
        as.put("a", "1111");
        as.put("a2", "1111");
        as.put("a3", "1111");
        as.put("a4", as2);
        as2.put("a2", "1111");
        as2.put("a3", "1111");
        as2.put("a4", as3);
        as3.put("a2", "1111");
        as3.put("a3", "1111");
        as3.put("a4", "1111");
        as1.put("b", as);
        JSONObject b2 = new JSONObject(as);
        System.out.println(">>>>" + b2.getJSONObject("a4").getString("a4"));
        //不同的版本输入信息如下：
        //1.2.29： >>>>{"a2":"1111","a3":"1111","a4":"1111"}
        //1.2.58：>>>>{a2=1111, a3=1111, a4=1111}

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
