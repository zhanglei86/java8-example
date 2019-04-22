package win.leizhang.java8example.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * bd
 * Created by zealous on 2019-04-22.
 */
public class BdTest {

    @Test
    public void testJia() {
        BigDecimal bAtom = new BigDecimal("1.25");
        BigDecimal bAtom2 = new BigDecimal("1.25");
        BigDecimal bAtom3 = new BigDecimal("0.00");
        BigDecimal bNum = new BigDecimal(7);

        BigDecimal jia = bAtom.add(bNum);
        System.out.println(jia);
        BigDecimal jian = bAtom.subtract(bAtom2);
        BigDecimal chen = bAtom.multiply(bNum);

        int re = bAtom3.compareTo(BigDecimal.ZERO);

        BigDecimal pointsCount = jia.multiply(new BigDecimal(1));
        long fen = pointsCount.setScale(0, BigDecimal.ROUND_DOWN).longValue();
        System.out.println(fen);

        BigDecimal b0 = new BigDecimal("1.2").setScale(6, BigDecimal.ROUND_HALF_UP);
        BigDecimal b1 = new BigDecimal("1.2").setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b2 = new BigDecimal("1.235").setScale(2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b3 = new BigDecimal("1.235").setScale(2, BigDecimal.ROUND_HALF_DOWN);
        BigDecimal b4 = b1.add(b2);

        if (b0.compareTo(b1) == 0) {
            System.out.println("eq");
        }
        System.out.println("ok");
    }

}
