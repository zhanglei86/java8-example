package win.leizhang.java8example.oom.heap;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 内存泄漏1
 * 参考: -[利用MemoryAnalyzer分析内存泄露](https://blog.csdn.net/a_bcd_123/article/details/69397350) 2017年04月06日 17:18:37 strongjack
 * Created by zealous on 2019/1/28.
 */
public class MemoryLeakTest1 {
    public static void main(String[] args) {
        System.out.println("Start demo.....");

        toRun();
    }

    private static void toRun() {
        List<int[]> theList = new ArrayList<>();//存放首地址
        Runtime run = Runtime.getRuntime();

        InputStream in = System.in;
        int ch = 'a';
        while (ch != '@') {// 输入@则停止while 循环
            try {
                ch = in.read();
                int[] arr = new int[1024 * 1024];//申请1M的内存

                theList.add(arr); //加入theList
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
