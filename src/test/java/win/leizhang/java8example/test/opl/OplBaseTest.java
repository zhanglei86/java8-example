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
        // 原报文
        String msg1 = "{\"type\":\"E001\",\"status\":\"1\"}";
        String msg2 = "{\"eventType\":\"E002\",\"status\":\"1\"}";
        String msg3 = "{\"eventTypeCode\":\"E003\",\"status\":\"1\"}";

        String code = validEventType(msg1);
        System.out.println(code);
        System.out.println("ok");
    }

    public String validEventType(String msg) {
        // 解析出事件编码
        Optional<Object> obj1 = Optional.ofNullable(JSONArray.parseObject(msg).get("eventType"));
        Optional<Object> obj2 = Optional.ofNullable(JSONArray.parseObject(msg).get("eventTypeCode"));
        // 最终的
        String code = obj1.map(Object::toString)
                .orElse(obj2.map(Object::toString).orElse("code"));
        return code;
    }

}
