package win.leizhang.java8example.test.core.collection;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import win.leizhang.java8example.bo.MainOutputDTO;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zealous on 2019-02-22.
 */
public class MapTest {

    @Test
    public void testItem() {
        Map<String, Object> map = new HashMap<>();
        map.put("1", "zea");
        map.put("2", "lous");
        map.put("3", "zealous");

        // 遍历1
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getValue());
        }

        // 遍历2
        map.forEach((k, v) -> {
            if (StringUtils.equals(k, "1")) {
                System.out.println("the first key, value=" + v);
            }
        });

        System.out.println("ok");
    }

    @Test
    public void testObj2Map() {
        MainOutputDTO outputDTO = new MainOutputDTO();
        Map<String, Object> map = JSON.parseObject(JSON.toJSONString(outputDTO), new TypeReference<Map<String, Object>>() {
        });
        map.put("type", 2);
        map.put("zea", "lous");
        map.put("pai", new BigDecimal("3.141592653"));

        System.out.println(JSON.toJSON(map));
    }

}
