package win.leizhang.java8example.oom.fd;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 测试, FileDescriptor的
 * [从JVM heap dump里查找没有关闭文件的引用](https://hengyunabc.github.io/jvm-heap-dump-find-fd/) 发表于 2018-07-02
 * Created by zealous on 2019/1/31.
 */
public class FileTest {

    public static void main(String[] args) throws Exception {
        noClose();

        // 死循环
        while (true) {
        }
        //System.in.read();
    }

    private static void noClose() throws IOException {
        File tempFile = File.createTempFile("test", "ttt");
        FileInputStream fi = new FileInputStream(tempFile);
        System.out.println("打开文件了");
        // TODO: 2019/1/31 记得关闭哦
    }
}
