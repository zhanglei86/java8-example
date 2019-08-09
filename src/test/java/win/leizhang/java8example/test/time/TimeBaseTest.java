package win.leizhang.java8example.test.time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import static win.leizhang.java8example.constant.TimeConstant.*;

/**
 * jdk8时间的测试
 * <p>官方给出的解释:simple beautiful strong immutable thread-safe.</p>
 * Created by zealous on 2018/6/21.
 */
public class TimeBaseTest {

    /**
     * 参考
     * <p>https://blog.csdn.net/chenleixing/article/details/44408875</p>
     * <p>https://blog.csdn.net/tzs_1041218129/article/details/80754428</p>
     * 阿里java手册解释
     * <p>使用 Instant 代替 Date，LocalDateTime 代替 Calendar， DateTimeFormatter 代替 SimpleDateFormat</p>
     */

    @Test
    public void testBase() {
        LocalDateTime now = LocalDateTime.now();

        System.out.println(now);
        System.out.println(now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(now.format(DEFAULT_FORMATTER_LDT));
        System.out.println(now.format(DEFAULT_FORMATTER_LDT_A1));
        System.out.println(now.format(DEFAULT_FORMATTER_LDT_A2));
        System.out.println(now.format(DEFAULT_FORMATTER_LDT_A3));

        int intYar = now.getYear();
        int intMonth = now.getMonthValue();
        int intDay = now.getDayOfMonth();
        System.out.println("=>" + intYar + "年" + intMonth + "月" + intDay + "日");

        int intHour = now.getHour();
        int intMinute = now.getMinute();
        int intSecond = now.getSecond();
        int intNano = now.getNano();
        System.out.println("=>" + intHour + "时" + intMinute + "分" + intSecond + "秒 " + intNano);
    }

    @Test
    public void testConstructor() {
        LocalDateTime ldt = LocalDateTime.of(2018, Month.FEBRUARY, 9, 15, 10, 30);
        LocalDateTime ldt2 = LocalDateTime.ofInstant(Instant.ofEpochMilli(1538137755000L), DEFAULT_ZONEID);
        LocalDateTime now = LocalDateTime.now();
        LocalDate ld = now.toLocalDate();
        LocalDate ld2 = LocalDate.of(2014, Month.DECEMBER, 12);
        LocalTime lt = now.toLocalTime();
        LocalTime lt2 = LocalTime.of(22, 15);
        LocalTime lt3 = LocalTime.parse("20:15:30");

        // 1年后
        LocalDateTime ldtPlus1Year = now.plusYears(1);
        // 今天的开始
        LocalDateTime nowStart = now.toLocalDate().atStartOfDay();
        // 今天的结束
        LocalDateTime nowEnd = nowStart.plusDays(1L);

        // 生日那天，会提醒
        LocalDate dateOfBirth = LocalDate.of(2010, 1, 6);
        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(LocalDate.now());
        if (currentMonthDay.equals(birthday)) {
            System.out.println("Many Many happy returns of the day !!");
        } else {
            System.out.println("Sorry, today is not your birthday");
        }

        System.out.println("ok");
    }

    @Test
    public void testZone() {

        // 时区取
        LocalDateTime ldtOfSecond = LocalDateTime.ofEpochSecond(1529572063, 501000000, ZoneOffset.UTC);
        LocalDateTime ldtOfAsia = LocalDateTime.now(ZONEID_CTT);
        LocalDate ldOfAsia = LocalDate.now(ZONEID_CTT);
        LocalTime ltOfAsia = LocalTime.now(ZONEID_CTT);

        // 带时区的完整时间
        ZonedDateTime zdtDefault = LocalDateTime.now().atZone(DEFAULT_ZONEID);
        ZonedDateTime zdtNewYork = ZonedDateTime.of(LocalDateTime.now(), ZONEID_NEWYORK);
        ZonedDateTime zdt08 = ZonedDateTime.of(LocalDateTime.of(2018, Month.FEBRUARY, 20, 3, 30, 20), ZoneId.of("+08"));
        ZonedDateTime zdt09 = ZonedDateTime.parse("2015-12-03T10:15:30+05:30[Asia/Shanghai]");

        // 偏量，北京时间
        OffsetDateTime offDt08 = Instant.now().atOffset(ZoneOffset.ofHours(8));

        // 时间戳，时刻，瞬时时间
        Instant ist1 = Instant.now();
        Instant ist2 = LocalDateTime.now().atZone(DEFAULT_ZONEID).toInstant();
        Instant ist3 = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8));
        Instant ist4 = (new Date()).toInstant();
        Instant ist5 = Calendar.getInstance().toInstant();
        Instant ist6 = Clock.systemDefaultZone().instant();
        Instant ist7 = Clock.systemUTC().instant();

        //时间戳，可以给前端
        long t1 = System.currentTimeMillis();
        long t2 = Instant.now().toEpochMilli();
        long t3 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long t4 = ist3.toEpochMilli();
        long t5 = Instant.now().getEpochSecond();
        int nano = Instant.now().getNano();

        // 持续时间
        Duration duration = Duration.ofDays(30);

        System.out.println("ok");
    }

    @Test
    public void testBetween() {
        LocalDateTime now = LocalDateTime.now();
        LocalDate nowDate = now.toLocalDate();
        LocalTime nowTime = now.toLocalTime();
        Instant ist = Instant.now();

        // 年/月/周/日以上
        LocalDate birthDate = LocalDate.of(2018, Month.OCTOBER, 2);
        Period p = Period.between(birthDate, nowDate);
        System.out.printf("年龄: %d年 %d月 %d日", p.getYears(), p.getMonths(), p.getDays());
        System.out.println();

        long diff1 = ChronoUnit.YEARS.between(birthDate, now);
        long diff2 = ChronoUnit.MONTHS.between(birthDate, now);
        long diff3 = ChronoUnit.WEEKS.between(birthDate, now);
        long diff4 = ChronoUnit.DAYS.between(birthDate, now);
        long diff42 = nowDate.toEpochDay() - birthDate.toEpochDay();


        // 日以下，毫秒级别
        Instant ist1 = ist.plus(Duration.ofSeconds(600));
        Duration du1 = Duration.between(ist, ist1);
        System.out.println("Diff in milliseconds: " + du1.toMillis() + "ms, Diff in seconds: " + du1.getSeconds() + "s.");

        Duration du0 = Duration.between(now.minusYears(500), now);

        LocalTime lt1 = LocalTime.of(9, 37, 18, 748);
        LocalTime lt2 = LocalTime.of(9, 38, 8, 170);
        LocalTime lt3 = LocalTime.of(9, 39, 54, 318);
        Duration du2 = Duration.between(lt1, lt2);
        Duration du3 = Duration.between(lt2, lt3);
        Duration du4 = Duration.between(lt1, lt3);
        Duration du5 = Duration.between(lt1, nowTime);

        System.out.println("ok");
    }

    @Test
    public void test1() {
        LocalDate now = LocalDate.now();
        // 开始
        LocalDate start = now.plusDays(10);
        if (now.with(TemporalAdjusters.firstDayOfMonth()).equals(start.with(TemporalAdjusters.firstDayOfMonth()))) {
            System.out.println("ok1");
        } else {
            System.out.println("ok2");
        }

        System.out.println("ok");
    }

}
