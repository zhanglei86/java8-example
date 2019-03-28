package win.leizhang.java8example.test.core;

import org.junit.Test;

/**
 * Created by zealous on 2019-03-14.
 */
public class SomeTest {

    /**
     * byte存储整型数据，占据1个字节(8 bits)，能够存储的数据范围是-128～+127。
     * Byte是java.lang中的一个类，目的是为基本数据类型byte进行封装。
     * 封装的好处，比如：1. Byte可以将对象的引用传递，使得多个function共同操作一个byte类型的数据，而byte基本数据类型是赋值之后要在stack(栈区域)进行存储的；
     * 2. 定义了和String之间互相转化的方法。Byte的大小是8个字节。因为Byte是需要通过关键字new来申请对象，而此部分申请出来的对象放在内存的heap(堆区域)。
     * <p>
     * https://www.cnblogs.com/Free-Thinker/p/4690040.html
     * https://www.cnblogs.com/zl181015/p/9435035.html
     */
    @Test
    public void testB() {
        System.out.printf("min=%d, max:%d\n", Byte.MIN_VALUE, Byte.MAX_VALUE);
        Byte by1 = new Byte("123");
        Byte by2 = new Byte("123");
        int length = by1.SIZE;

        if (by1 == by2) System.out.println("==");
        if (by1.equals(by2)) System.out.println("eql");

        Byte by3 = by1;
        if (by1 == by3) System.out.println("==2");

        byte temp1 = (byte) 241; // 241的二进制表示为11110001（补码），其中第一位为符号位，那么剩余的计算结果为15，最终结果为-15
        System.out.println(temp1);
        byte temp2 = (byte) 456;//同理
        System.out.println(temp2);
    }

    /**
     * https://blog.csdn.net/ibelieve618/article/details/54948729
     * https://www.cnblogs.com/seakt/p/4478045.html
     */
    @Test
    public void testC() {
        Character c1 = '是';
        char c2 = 12345;
        char m = '中' + '国';
        System.out.printf("out ==>%c\n", c1);
        System.out.println(Integer.valueOf(c1));
    }

}
