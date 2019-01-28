package win.leizhang.java8example.oom.heap;

import win.leizhang.java8example.bo.Apple;

import java.util.ArrayList;
import java.util.List;

/**
 * 内存泄漏1
 * 参考: -[eclipseMemoryAnalyzer工具分析java内存溢出代码](https://blog.csdn.net/liangrui1988/article/details/56667444) 2017年02月23日 11:53:25 java的爪哇
 * Created by zealous on 2019/1/28.
 */
public class MemoryLeakTest2 {
    public static void main(String[] args) {
        toRun();
    }

    private static void toRun() {
        List<Apple> list = new ArrayList<>();
        int i = 0;
        while (true) {
            System.out.println("hello world>>>" + (i++));
            list.add(new Apple(i, "red"));
        }
    }

}
