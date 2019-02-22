package win.leizhang.java8example.test.opl;

import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

import java.util.Optional;

/**
 * Created by zealous on 2018/12/5.
 */
public class OplBaseTest {

    @Test
    public void test1() {
        Integer value1 = null;
        Integer value2 = 10;

        // 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);
        // 如果传递的参数是 null，抛出NPE
        Optional<Integer> b = Optional.of(value2);

        System.out.println(sum(a, b));
    }

    private Integer sum(Optional<Integer> a, Optional<Integer> b) {
        // 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(0);
        // 值需要存在
        Integer value2 = b.get();

        return value1 + value2;
    }

    @Test
    public void test2() {
        // 原报文
        String msg1 = "{\"type\":\"E001\",\"status\":\"1\"}";
        String msg2 = "{\"evtType\":\"E002\",\"status\":\"1\"}";
        String msg3 = "{\"evtTypeCode\":\"E003\",\"status\":\"1\"}";

        String code = validType(msg3);
        System.out.println(code);
        System.out.println("ok");
    }

    private String validType(String msg) {
        // 解析出事件编码
        Optional<Object> obj1 = Optional.ofNullable(JSONArray.parseObject(msg).get("type"));
        Optional<Object> obj2 = Optional.ofNullable(JSONArray.parseObject(msg).get("evtType"));
        Optional<Object> obj3 = Optional.ofNullable(JSONArray.parseObject(msg).get("evtTypeCode"));
        // 最终的
        String code = obj1.map(Object::toString)
                .orElse(obj2.map(Object::toString).orElse(obj3.map(Object::toString).orElse("code")));
        return code;
    }

}
