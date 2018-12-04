package win.leizhang.java8example.constant;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Created by zealous on 2018/6/5.
 */
public class TimeConstant {

    // 时区
    @Deprecated
    public static final ZoneId DEFAULT_ZONEID_OLD = TimeZone.getDefault().toZoneId();
    public static final ZoneId DEFAULT_ZONEID = ZoneId.systemDefault();
    public static final ZoneId ZONEID_CTT = ZoneId.of("Asia/Shanghai");
    public static final ZoneId ZONEID_NEWYORK = ZoneId.of("America/New_York");

    // 时间格式
    public static final String DEFAULT_FORMAT_LDT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_LDT2 = "yyyyMMddHHmmss";
    public static final String DEFAULT_FORMAT_LD = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_LD_CN = "yyyy年MM月dd日";
    public static final String DEFAULT_FORMAT_LT = "HH:mm:ss";

    public static final String DEFAULT_FORMAT_LDT_A1 = "yyyy-MM-dd HH:mm:ss SSS";
    public static final String DEFAULT_FORMAT_LDT_A2 = "yyyyMMddHHmmssSSS";
    public static final String DEFAULT_FORMAT_LDT_A3 = "yyyyMMddHHmmss";

    // 时间格式化
    public static final DateTimeFormatter DEFAULT_FORMATTER_LDT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LDT);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LDT2 = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LDT2);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LD = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LD);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LD_CN = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LD_CN);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LT);

    public static final DateTimeFormatter DEFAULT_FORMATTER_LDT_A1 = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LDT_A1);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LDT_A2 = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LDT_A2);
    public static final DateTimeFormatter DEFAULT_FORMATTER_LDT_A3 = DateTimeFormatter.ofPattern(DEFAULT_FORMAT_LDT_A3);

    // other

}
