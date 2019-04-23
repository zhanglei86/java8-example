package win.leizhang.java8example.test.core;

import org.junit.Test;

/**
 * 运算符
 * Created by zealous on 2019-04-22.
 */
public class OperatorsTest {

    /**
     * 位运算
     * 所有位运算都会把值转为二进制，再进行运算
     */
    @Test
    public void test1() {
        int a = 3;
        int b = 5;
        System.out.printf("a=%s, b=%s\n", Integer.toBinaryString(a), Integer.toBinaryString(b));
        System.out.printf("a&b=%d\n", (a & b));
        System.out.printf("a|b=%d\n", (a | b));
        System.out.printf("a^b=%d\n", (a ^ b));
        System.out.printf("~a=%d, ~b=%d\n", ~a, ~b);//推导过程：反码 -> 减1 -> 再取反
    }

    /**
     * 位移运算，左
     */
    @Test
    public void test2() {
        int a = -101;
        for (int i = 0; i < 34; i++) {
            System.out.printf("a<<%d = %d\n", i, (a << i));
        }
    }

    /**
     * 位移运算，右
     * >>：带符号右移。正数右移高位补0，负数右移高位补1。
     * >>>：无符号右移。无论是正数还是负数，高位通通补0。对于正数而言，>>和>>>没区别。
     */
    @Test
    public void test3() {
        System.out.printf("4>>1=%d\n", (4 >> 1));
        System.out.printf("-4>>1=%d, -2>>1=%d\n", (-4 >> 1), (-2 >> 1));

        System.out.printf("4>>>1=%d\n", (4 >>> 1));
        System.out.printf("Integer.MAX=%d, -2>>>1=%d, -1>>>1=%d\n", Integer.MAX_VALUE, (-2 >>> 1), (-1 >>> 1));

    }

    /**
     * 其他用途，判等
     */
    @Test
    public void testEq() {
        int a = 4;
        int b = 10;
        boolean flag = ((a >> 31) ^ (b >> 31)) == 0;
        System.out.printf("判断两个数的符号是否相等，a=%d, b=%d, 符号相等=%s\n", a, b, flag);
    }

    /**
     * 其他用途，拼接
     */
    @Test
    public void testPin() {
        long high = 4294967295L;
        long low = 1297042716;
        Long val1 = (high << 32) | low;//-2997924580

        long high2 = 1074118410;
        long low2 = 2333345753L;
        Long val2 = (high2 << 32) | low2;//4613303445314865113
        double d = Double.longBitsToDouble(val2);

        System.out.println("ok");
    }

    /**
     * 其他用途，校验和最大长度？
     */
    @Test
    public void testMaxLength() {
        int op1 = 4;
        int op2 = 35;
        int op3 = 288;

        int oper1 = op1 & 0x1f;
        int oper2 = op3 & 0x3f;//FIXME 大于的话，有问题
        System.out.println("ok");
    }
}
