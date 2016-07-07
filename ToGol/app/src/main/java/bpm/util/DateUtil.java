package bpm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static java.util.Calendar.HOUR;
import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;

/**
 * Created by igor on 14/06/16.
 */
public class DateUtil {

    public static Date createDate(int year, int mounth, int day, String hour){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(year, mounth -1, day);
        buildHourMinute(hour, gregorianCalendar);

        return gregorianCalendar.getTime();
    }

    private static void buildHourMinute(String hour, GregorianCalendar gregorianCalendar) {
        if (hour != null && !hour.isEmpty()) {
            String[] hourMinute = hour.split(":");

            if (hourMinute != null){
                gregorianCalendar.set(HOUR_OF_DAY, Integer.parseInt(hourMinute[0]));
                gregorianCalendar.set(MINUTE, Integer.parseInt(hourMinute[1]));
            }
        }
    }

    public static boolean hasHour(Date date){
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);
        return gregorianCalendar.get(HOUR_OF_DAY) != 0;
    }

    public static String getFormatedHour(Date date){
        StringBuilder formatedHour = new StringBuilder();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(date);

        if (gregorianCalendar.get(HOUR) != 0) {
            formatedHour.append(String.format("%02d", gregorianCalendar.get(HOUR_OF_DAY)));
            formatedHour.append(":");
            formatedHour.append(String.format("%02d", gregorianCalendar.get(MINUTE)));
        }

        return formatedHour.toString();
    }
}
