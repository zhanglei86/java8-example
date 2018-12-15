package win.leizhang.java8example.test.core;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by zealous on 2018/12/15.
 */
public class FileTest {

    private final static String FILE_NAME = "src/main/resources/data.txt";

    @Test
    public void test1() throws IOException {
        //getFile("");

        // method we want to refactor to make more flexible
        String result = processFileLimited();
        System.out.println(result);
        System.out.println("---");

        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);
        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(twoLines);
    }

    private void getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        URL url = classLoader.getResource(fileName);

        System.out.println(url.getFile());
        File file = new File(url.getFile());
        System.out.println(file.exists());
    }

    private static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            return br.readLine();
        }
    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            return p.process(br);
        }
    }

    public interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }
}
