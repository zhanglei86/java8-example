package win.leizhang.java8example.juc.wbl;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Created by zealous on 2020/9/24.
 */
public class ForkjoinTest {

    @Test
    public void test1() {
        String[] fc = {"hello world",
                "hello me",
                "hello fork",
                "hello join",
                "hello test",
        };

        ForkJoinPool fjp = new ForkJoinPool(3);

        // 创建任务
        MR mr = new MR(fc, 0, fc.length);
        // 启动任务
        Map<String, Long> result = fjp.invoke(mr);
        // 输出结果
        result.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    // 模拟类
    static class MR extends RecursiveTask<Map<String, Long>> {
        private String[] fc;
        private int start, end;

        public MR(String[] fc, int fr, int to) {
            this.fc = fc;
            this.start = fr;
            this.end = to;
        }

        @Override
        protected Map<String, Long> compute() {
            if (end - start == 1) {
                return calc(fc[start]);
            }

            int mid = (start + end) / 2;
            MR mr1 = new MR(fc, start, mid);
            mr1.fork();
            MR mr2 = new MR(fc, mid, end);
            return merge(mr2.compute(), mr1.join());
        }

        // 合并结果
        private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
            Map<String, Long> result = new HashMap<>();
            result.putAll(r1);
            // h结果
            r2.forEach((k, v) -> {
                Long c = r2.get(k);
                if (c != null) {
                    // TODO xx
                } else {
                    // TODO x2
                }
            });
            return result;
        }

        // 统计单词数量
        private Map<String, Long> calc(String line) {
            Map<String, Long> result = new HashMap<>();
            // 分割单词
            String[] words = line.split("\\s+");
            // t统计
            for (String w : words) {
                Long v = result.get(w);
                if (v != null) {
                    result.put(w, v + 1);
                } else {
                    result.put(w, 1L);
                }
            }
            return result;
        }
    }
}
