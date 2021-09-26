package net.wangjifeng.watermelon.javax;

import net.wangjifeng.watermelon.base.WatermelonException;
import net.wangjifeng.watermelon.util.Nils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: wjf
 * @date: 2021/9/22 13:04
 *
 * 作为{@link java.util.Date}的扩展。
 */
public class DateX extends Date implements X {

    private static final String yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    private static final String yyyy_MM_dd = "yyyy-MM-dd";
    private static final String HH_mm_ss = "HH:mm:ss";

    // ***** PUBLIC *****

    public DateX() {}

    public DateX(Date date) {
        super(date.getTime());
    }

    /**
     * 格式化日期。默认为[yyyy-MM-dd]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd格式的时间字符串表示
     */
    public String formatDate(String... pattern) {
        return formatDate(this, pattern);
    }

    /**
     * 格式化时间。默认为[HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return HH:mm:ss格式的时间字符串表示
     */
    public String formatTime(String... pattern) {
        return formatTime(this, pattern);
    }

    /**
     * 格式化日期时间。默认为[yyyy-MM-dd HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd HH:mm:ss格式的时间字符串表示
     */
    public String formatDateTime(String... pattern) {
        return formatDateTime(this, pattern);
    }

    /**
     * 获取当前时间。
     *
     * @return DateX
     */
    public static DateX now() {
        return new DateX();
    }

    /**
     * 格式化日期。默认为[yyyy-MM-dd]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd格式的时间字符串表示
     */
    public static String formatDate(Date date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{yyyy_MM_dd});
        return newDateFormat(pattern[0]).format(date);
    }

    /**
     * 格式化时间。默认为[HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return HH:mm:ss格式的时间字符串表示
     */
    public static String formatTime(Date date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{HH_mm_ss});
        return newDateFormat(pattern[0]).format(date);
    }

    /**
     * 格式化日期时间。默认为[yyyy-MM-dd HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd HH:mm:ss格式的时间字符串表示
     */
    public static String formatDateTime(Date date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{yyyy_MM_dd_HH_mm_ss});
        return newDateFormat(pattern[0]).format(date);
    }

    /**
     * 解析日期。默认为[yyyy-MM-dd]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd格式的时间表示
     */
    public static Date parseDate(String date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{yyyy_MM_dd});
        return internalParse(date, pattern[0]);
    }

    /**
     * 解析时间。默认为[HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return HH:mm:ss格式的时间表示
     */
    public static Date parseTime(String date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{HH_mm_ss});
        return internalParse(date, pattern[0]);
    }

    /**
     * 解析日期时间。默认为[yyyy-MM-dd HH:mm:ss]。
     *
     * @param pattern 格式化模板字符串
     * @return yyyy-MM-dd HH:mm:ss格式的时间字符串表示
     */
    public static Date parseDateTime(String date, String... pattern) {
        pattern = Nils.isNilOrDefault(pattern, new String[]{yyyy_MM_dd_HH_mm_ss});
        return internalParse(date, pattern[0]);
    }

    /**
     * 获取给定时间的那天的开始时间。
     *
     * @param date date
     * @return date-start
     */
    public static Date startOfDay(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取给定时间的那天的结束时间。
     *
     * @param date date
     * @return date-end
     */
    public static Date endOfDay(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    /**
     * 获取给定时间的那周的开始时间。
     *
     * @param date date
     * @return date-start
     */
    public static Date startOfWeek(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return startOfDay(calendar.getTime());
    }

    /**
     * 获取给定时间的那周的结束时间。
     *
     * @param date date
     * @return date-end
     */
    public static Date endOfWeek(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.DAY_OF_WEEK, 7);
        return endOfDay(calendar.getTime());
    }

    /**
     * 获取给定时间的那月的开始时间。
     *
     * @param date date
     * @return date-start
     */
    public static Date startOfMonth(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        return startOfDay(calendar.getTime());
    }

    /**
     * 获取给定时间的那月的结束时间。
     *
     * @param date date
     * @return date-end
     */
    public static Date endOfMonth(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return endOfDay(calendar.getTime());
    }

    /**
     * 获取给定时间的那周的开始时间。(中国人习惯[周一 ---> 周日])
     *
     * @param date date
     * @return date-start
     */
    public static Date startOfChineseWeek(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            calendar.add(Calendar.DATE, -1);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, -(dayOfWeek - Calendar.MONDAY));
        return startOfDay(calendar.getTime());
    }

    /**
     * 获取给定时间的那周的结束时间。(中国人习惯[周一 ---> 周日])
     *
     * @param date date
     * @return date-end
     */
    public static Date endOfChineseWeek(Date... date) {
        Calendar calendar = getCalendar(Nils.isNilOrDefault(date, new Date[]{ now() })[0]);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return endOfDay(date);
        }
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE, (Calendar.SATURDAY - dayOfWeek) + 1);
        return endOfDay(calendar.getTime());
    }

    /**
     * 创建一个新的日期格式化。
     *
     * @param pattern 格式化模板字符串
     * @return DateFormat
     */
    public static DateFormat newDateFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 获取一个指定时间的日历。
     *
     * @param date date
     * @return Calendar
     */
    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    // ***** PRIVATE *****

    /**
     * 解析日期。
     *
     * @param date 日期字符串
     * @param pattern 格式化模板字符串
     * @return DateX
     */
    private static DateX internalParse(String date, String pattern) {
        try {
            return new DateX(newDateFormat(pattern).parse(date));
        } catch (ParseException e) {
            throw WatermelonException.newEx(e.getMessage());
        }
    }

    @Override
    public String description() {
        return "作为{java.util.Date}的扩展。";
    }

    // ***** CLASS *****

}
