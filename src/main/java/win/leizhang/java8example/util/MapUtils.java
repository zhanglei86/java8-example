package win.leizhang.java8example.util;

import win.leizhang.java8example.bo.Apple;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zealous on 2019-08-09.
 */
public class MapUtils {

    public static Object transMap(Class type, Map map) throws Exception {
        //实例化类
        Object obj = type.newInstance();
        //获取类中属性
        BeanInfo info = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyPermissions = info.getPropertyDescriptors();
        for (PropertyDescriptor pro : propertyPermissions) {
            String proName = pro.getName();
            if (map.containsKey(proName)) {
                Object methodName = map.get(proName);
                Object[] args = {methodName};
                try {
                    pro.getWriteMethod().invoke(obj, args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return obj;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("weight", 11);
        map.put("color", "red");

        Apple ap = (Apple) transMap(Apple.class, map);
        System.out.println("ok");
    }

}
