package win.leizhang.java8example.oom.heap;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 内存泄漏3, copy from crossoverJie
 * 方法区内存溢出 1.8之后修改为元数据区
 * 参考: -https://crossoverjie.top/JCSprout/#/jvm/OOM-analysis, -XX:MaxMetaspaceSize=10M
 * Created by zealous on 2019/1/28.
 */
public class MemoryLeakTest3 {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(MemoryLeakTest3.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o, objects);
                }
            });
            enhancer.create();
        }
    }
}
