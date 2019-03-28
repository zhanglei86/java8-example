package win.leizhang.java8example.test.core;

import org.junit.Test;

import java.io.*;

/**
 * Created by zealous on 2019-03-28.
 */
public class FileTest {

    @Test
    public void test1() throws IOException {
        StringBuilder sb = new StringBuilder();
        // FIXME 有bug，读取竟然不完整？！是buffer溢出了吗，有空调整下

        // 读
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("filePathSrc")), "UTF-8"));
        while (br.readLine() != null) {
            sb.append(br.readLine()).append("\n");
        }
        br.close();

        // 写
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("filePathDesc")), "UTF-8"));
        bw.write(sb.toString());
        bw.newLine();
        bw.write("==end==\n");
        bw.close();
    }
}
