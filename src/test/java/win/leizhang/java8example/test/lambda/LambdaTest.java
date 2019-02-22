package win.leizhang.java8example.test.lambda;

import org.junit.Test;

/**
 * Created by zealous on 2018/8/1.
 */
public class LambdaTest {

    @Test
    public void testMath() {
        // 类型声明
        MathOperation addition = (int a, int b) -> a + b;
        // 不用类型声明
        MathOperation subtraction = (a, b) -> a - b;
        // 大括号中的返回语句
        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };
        // 没有大括号及返回语句
        MathOperation division = (int a, int b) -> a / b;

        int i = 10;
        int j = 5;

        System.out.println(i + "+" + j + "= " + operate(i, j, addition));
        System.out.println(i + "-" + j + "= " + operate(i, j, subtraction));
        System.out.println(i + "*" + j + "= " + operate(i, j, multiplication));
        System.out.println(i + "/" + j + "= " + operate(i, j, division));

        System.out.println("ok");
    }

    @Test
    public void testMsg() {
        // 不用括号
        GreetingService greetService1 = msg -> System.out.println("Hello " + msg);
        // 用括号
        GreetingService greetService2 = (msg) -> System.out.println("Hello " + msg);

        greetService1.sayMessage("Runoob");
        greetService2.sayMessage("Google");

        System.out.println("ok");
    }

    @Test
    public void testCompile() {
        int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 输出结果为 3

        // 编译报错, 此处的num是隐性的具有 final 的语义
        //num = 5;

        System.out.println("ok");
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    /**
     * interface
     */
    interface MathOperation {
        int operation(int a, int b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

}
