package win.leizhang.java8example.typical;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
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
