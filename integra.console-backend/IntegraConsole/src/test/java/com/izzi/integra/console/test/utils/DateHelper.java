package com.izzi.integra.console.test.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rafael on 27/12/2016.
 */
public class DateHelper {

    public static Date beginOfDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    public static Date settingDate(final int day, final int month, final int year) {
        final Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, day, 0, 0);
        return cal.getTime();
    }

    public static Date endOfDay(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }
}
