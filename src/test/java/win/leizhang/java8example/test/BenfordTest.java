package win.leizhang.java8example.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索[Benford's law], 做个测试用例
 * 本福特定律：说明一堆从实际生活得出的数据中，以1为首位数字的数的出现概率约为总数的三成，接近直觉得出之期望值1/9的3倍。推广来说，越大的数，以它为首几位的数出现的概率就越低。它可用于检查各种数据是否有造假。
 * Created by zealous on 2019-02-22.
 */
public class BenfordTest {

    @Test
    public void test1() throws Exception {
        List<Double> dl = Arrays.asList(130.62, 428.07, 40.40, 201.51, 300.23, 136.31, 173.65);

        System.out.println("/********/");
        System.out.println("evt08样本，输出==>");
        String src08 = "/Volumes/IntelSSD/evt08.log";
        cal(loadData(src08));

        System.out.println("/********/");
        System.out.println("evt13样本，输出==>");
        String src13 = "/Volumes/IntelSSD/evt13.log";
        cal(loadData(src13));

        System.out.println("/********/");
        System.out.println("evt19样本，输出==>");
        String src19 = "/Volumes/IntelSSD/evt19.log";
        cal(loadData(src19));
    }

    /**
     * 计算
     *
     * @param dl 链表
     */
    private void cal(List<Double> dl) {
        final double size = Double.parseDouble(dl.size() + ".0");

        Map<Integer, Long> result = dl.parallelStream()
                .map(p -> Integer.valueOf(String.valueOf(p).substring(0, 1)))
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        DecimalFormat df = new DecimalFormat("#.0");
        result.forEach((k, v) -> {
            String percent = String.format("%d => %s%%", k, df.format(v / size * 100));
            System.out.println(percent);
        });
    }

    /**
     * 加载数据
     *
     * @param src 订单金额文件
     * @return 链表
     * @throws IOException
     */
    private List<Double> loadData(String src) throws IOException {
        FileReader fr = new FileReader(src);
        BufferedReader bfr = new BufferedReader(fr);

        List<Double> list = new ArrayList<>(2000);
        String srcLine;
        // 按行读
        while ((srcLine = bfr.readLine()) != null) {
            list.add(Double.parseDouble(srcLine));
        }
        bfr.close();
        fr.close();
        return list;
    }

}
