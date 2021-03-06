package win.leizhang.java8example.test.time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static win.leizhang.java8example.constant.TimeConstant.DEFAULT_FORMATTER_LDT;
import static win.leizhang.java8example.constant.TimeConstant.DEFAULT_ZONEID;

/**
 * 转换
 * Created by zealous on 2018/12/3.
 */
public class TransformTest {

    @Test
    public void testLdtAndDate() {
        // LocalDateTime -> Date
        LocalDateTime ldt1 = LocalDateTime.now();

        Instant instant1 = ldt1.atZone(DEFAULT_ZONEID).toInstant();
        Date date1 = Date.from(instant1);
        System.out.println("date: " + date1);


        // Date -> LocalDateTime
        Date date2 = new Date();

        Instant instant2 = date2.toInstant();
        LocalDateTime ldtDate = instant2.atZone(DEFAULT_ZONEID).toLocalDateTime();
        System.out.println("ldt: " + ldtDate);
    }

    @Test
    public void testLdAndDate() {
        // LocalDate -> Date
        LocalDate ld1 = LocalDate.now();

        Instant instant1 = ld1.atStartOfDay().atZone(DEFAULT_ZONEID).toInstant();
        Date date1 = Date.from(instant1);
        System.out.println("date: " + date1);


        // Date -> LocalDate
        Date date2 = new Date();

        Instant instant2 = date2.toInstant();
        LocalDate ldDate = instant2.atZone(DEFAULT_ZONEID).toLocalDate();
        System.out.println("ld: " + ldDate);
    }

    @Test
    public void testLdtAndStr() {
        // LocalDateTime -> String
        LocalDateTime ldt = LocalDateTime.now();
        String str = DEFAULT_FORMATTER_LDT.format(ldt);
        System.out.println("str: " + str);

        // String -> LocalDateTime
        String str2 = "2018-07-22 00:00:00";
        LocalDateTime ldt2 = LocalDateTime.parse(str2, DEFAULT_FORMATTER_LDT);
        System.out.println("ldt2: " + ldt2);
    }

}
