package cn.os.test.common.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 日期工具类
 * @author oushuo
 * @date 2020/10/14
 */
public class DateUtil {


    /**
     * Date转LocalDate
     *
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 获取两个日期中包含的日期
     * @return
     */
//    public static List<Date> getBothDate(Date startDate, Date endDate) {
//        List<Date> dates = new ArrayList<>();
//        Calendar calBegin = Calendar.getInstance();
//        // 使用给定的 Date 设置此 Calendar 的时间
//        calBegin.setTime(startDate);
//        Calendar calEnd = Calendar.getInstance();
//        // 使用给定的 Date 设置此 Calendar 的时间
//        calEnd.setTime(endDate);
//        while (calEnd.after(calBegin)) {
//            dates.add(calBegin.getTime());
//            calBegin.add(Calendar.DAY_OF_MONTH, 1);
//        }
//        return dates;
//    }

    /**
     * 获取两个日期中指定格式的日期
     * @param startDate
     * @param endDate
     * @param format
     * @return
     */
//    public static List<String> getBothDate(Date startDate, Date endDate, String format) {
//        List<String> dates = new ArrayList<>();
//        SimpleDateFormat sdf = null;
//        if (StringUtils.isBlank(format)) {
//            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        } else {
//            sdf = new SimpleDateFormat(format);
//        }
//        for (Date date : getBothDate(startDate, endDate)) {
//            dates.add(sdf.format(date));
//        }
//        return dates;
//    }


//    public static List<String> getBothDate(LocalDate startDate, LocalDate endDate, String format, String dateSpan) {

//        switch (dateSpan) {
//            case "day" : break;
//            case "month" : break;
//            case "quarter" : break;
//            case "semester" : break;
//            case "year" : break;
//        }
//        List<String> dates = new ArrayList<>();
//        SimpleDateFormat sdf = null;
//        if (StringUtils.isBlank(format)) {
//            sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        } else {
//            sdf = new SimpleDateFormat(format);
//        }
//        for (Date date : getBothDate(startDate, endDate)) {
//            dates.add(sdf.format(date));
//        }
//        return dates;
//        return null;
//    }


    /**
     * 获取两个日期中间的日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getBothDate(Date startDate, Date endDate, String dateSpan) {
        if (startDate == null || endDate == null) {
            return null;
        }
        return getBothDate(date2LocalDate(startDate), date2LocalDate(endDate), dateSpan);
    }

    /**
     * 获取两个日期中间的所有day
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getBothDay(LocalDate startDate, LocalDate endDate) {
        return getBothDate(startDate, endDate, "day");
    }

    /**
     * 获取两个日期中间的所有day
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getBothDay(Date startDate, Date endDate) {
        return getBothDate(startDate, endDate, "day");
    }

    public static List<String> getBothMonth(Date startDate, Date endDate) {
        return getBothDate(startDate, endDate, "month");
    }

    /**
     * 获取两个日期中间的日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> getBothDate(LocalDate startDate, LocalDate endDate, String dateSpan) {
        List<String> result = new ArrayList<>();
        while (startDate.toEpochDay() - endDate.toEpochDay() <= 0) {
            String year = "" + startDate.getYear();
            String month = "" + startDate.getMonthValue();
            month = month.length() == 1 ? "0" + month : month;
            String day = "" + startDate.getDayOfMonth();
            switch (dateSpan) {
                case "day":
                    result.add(startDate.toString());
                    startDate = startDate.plusDays(1);
                    break;
                case "month":
                    result.add(year + "-" + month);
                    startDate = startDate.plusMonths(1).withDayOfMonth(1);
                    break;
                case "year":
                    result.add(year);
                    startDate = startDate.plusYears(1).withMonth(1).withDayOfMonth(1);
                    break;
                default:
                    return getTimeBucket(startDate, endDate, dateSpan);
            }
        }
        System.out.println(result);
        return result;
    }

    /**
     * 获取两个日期中间的时间段
     *
     * @param startDate
     * @param endDate
     * @return
     */
    private static List<String> getTimeBucket(LocalDate startDate, LocalDate endDate, String dateSpan) {
        List<String> result = new ArrayList<>();
        boolean res = startDate.toEpochDay() - endDate.toEpochDay() <= 0;
        while (res) {
            String year = "" + startDate.getYear();
            String month = "" + startDate.getMonthValue();
            month = month.length() == 1 ? "0" + month : month;
            String beginMonth = year + "-" + month;
            switch (dateSpan) {
                case "quarter":
                    startDate = startDate.plusMonths(2).withDayOfMonth(1);
                    break;
                case "semester":
                    startDate = startDate.plusMonths(5).withDayOfMonth(1);
                    break;
                default:
                    return null;
            }
            res = startDate.toEpochDay() - endDate.toEpochDay() <= 0;
            if (res) {
                year = "" + startDate.getYear();
                month = "" + startDate.getMonthValue();
                month = month.length() == 1 ? "0" + month : month;
                String endMonth = year + "-" + month;
                result.add(beginMonth + "," + endMonth);
            } else {
                year = "" + endDate.getYear();
                month = "" + endDate.getMonthValue();
                month = month.length() == 1 ? "0" + month : month;
                String endMonth = year + "-" + month;
                result.add(beginMonth + "," + endMonth);
                break;
            }
            startDate = startDate.plusMonths(1);
            res = startDate.toEpochDay() - endDate.toEpochDay() <= 0;
        }
        return result;
    }

}
