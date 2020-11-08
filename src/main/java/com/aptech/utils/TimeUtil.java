/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aptech.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author ducsang
 */
public class TimeUtil {

    public static final long MILIS_SEC_PER_DAY = 24 * 3600 * 1000;

    public static Timestamp removeTime(Timestamp date) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getNow() {
        return removeMilliSecond(null);
    }

    public static Timestamp removeMilliSecond(Timestamp date) {
        Calendar cal = getCalendar(date);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getToDay() {
        return removeTime(getNow());
    }

    public static Timestamp getMinDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1870);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Timestamp getMaxDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 100);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return new Timestamp(cal.getTimeInMillis());
    }

    public static Date getDateFromString(String dateStr) {
        try {
            Timestamp minDate = getMinDate();
            Timestamp maxDate = getMaxDate();

            Date date = null;

            String yyyyPattern = "[1-2][0-9]{3}";
            String yyyy = "yyyy";
            if (dateStr.matches("^" + yyyyPattern + "$")) {
                date = new SimpleDateFormat(yyyy).parse(dateStr);
                if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                    return date;
                }
            }

            String ddPattern = "[0-3][0-9]";
            String dd = "dd";
            String dPattern = "[1-9]";
            String d = "d";
            String mmPattern = "(0[1-9]|1[0-2])";
            String mm = "MM";
            String mPattern = "[1-9]";
            String m = "M";
            String yyPattern = "[0-9]{2}";
            String yy = "yy";
            String[] separateChars = {"", "/", "-", "."};

            for (String separateChar : separateChars) {
                if (dateStr.matches("^" + ddPattern + separateChar + mmPattern + separateChar + yyyyPattern + "$")) { // dd/MM/YYYY
                    date = new SimpleDateFormat(dd + separateChar + mm + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + dPattern + separateChar + mmPattern + separateChar + yyyyPattern + "$")) { // d/MM/YYYY
                    date = new SimpleDateFormat(d + separateChar + mm + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + ddPattern + separateChar + mPattern + separateChar + yyyyPattern + "$")) { // dd/M/YYYY
                    date = new SimpleDateFormat(dd + separateChar + m + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + dPattern + separateChar + mPattern + separateChar + yyyyPattern + "$")) { // d/M/YYYY
                    date = new SimpleDateFormat(d + separateChar + m + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + ddPattern + separateChar + mmPattern + separateChar + yyyyPattern + "$")) { // dd-MM-YYYY
                    date = new SimpleDateFormat(dd + separateChar + mm + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + yyyyPattern + separateChar + mmPattern + separateChar + ddPattern + "$")) { // YYYY-MM-dd
                    date = new SimpleDateFormat(yyyy + separateChar + mm + separateChar + dd).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + mmPattern + separateChar + yyyyPattern + "$")) { // MM/YYYY
                    date = new SimpleDateFormat(mm + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + mPattern + separateChar + yyyyPattern + "$")) { // M/YYYY
                    date = new SimpleDateFormat(m + separateChar + yyyy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + ddPattern + separateChar + mmPattern + separateChar + yyPattern + "$")) { // dd/MM/YY
                    date = new SimpleDateFormat(dd + separateChar + mm + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + dPattern + separateChar + mmPattern + separateChar + yyPattern + "$")) { // d/MM/YY
                    date = new SimpleDateFormat(d + separateChar + mm + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + ddPattern + separateChar + mPattern + separateChar + yyPattern + "$")) { // dd/M/YY
                    date = new SimpleDateFormat(dd + separateChar + m + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + dPattern + separateChar + mPattern + separateChar + yyPattern + "$")) { // d/M/YY
                    date = new SimpleDateFormat(d + separateChar + m + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + mmPattern + separateChar + yyPattern + "$")) { // MM/YY
                    date = new SimpleDateFormat(mm + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }

                if (dateStr.matches("^" + mPattern + separateChar + yyPattern + "$")) { // M/YY
                    date = new SimpleDateFormat(m + separateChar + yy).parse(dateStr);
                    if (date.compareTo(minDate) >= 0 && date.compareTo(maxDate) <= 0) {
                        return date;
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Timestamp getTimestampFromString(String dateStr) {
        Date time = getDateFromString(dateStr);

        if (time != null) {
            return new Timestamp(time.getTime());
        }

        return null;
    }
    
    public static LocalDateTime getLocalDateTimeFromString(String dateStr) {
        Date time = getDateFromString(dateStr);

        if (time != null) {
            return new Timestamp(time.getTime()).toLocalDateTime();
        }

        return null;
    }

    public static int getHourMinute(Timestamp time) {
        int hour = getHour(time);
        int minute = getMinute(time);
        return hour * 60 + minute;
    }

    static public int getHour(Timestamp time) {
        if (time == null) {
            return 0;
        }
        Calendar cal = getCalendar(time);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    static public int getMinute(Timestamp time) {
        if (time == null) {
            return 0;
        }
        Calendar cal = getCalendar(time);
        return cal.get(Calendar.MINUTE);
    }

    static public int getMonth(long time) {
        if (time == 0) {
            time = System.currentTimeMillis();
        }
        GregorianCalendar cal = new GregorianCalendar();
        return cal.get(Calendar.MONTH) + 1;
    }	//	getMonth

    static public int getMonth(Timestamp dayTime) {
        if (dayTime == null) {
            return getMonth(System.currentTimeMillis());
        }
        return getMonth(dayTime.getTime());
    }	//	getMonth

    static public int getYear(long time) {
        if (time == 0) {
            time = System.currentTimeMillis();
        }
        GregorianCalendar cal = new GregorianCalendar();
        return cal.get(Calendar.YEAR);
    }	//	getMonth

    static public int getYear(Timestamp dayTime) {
        if (dayTime == null) {
            return getYear(System.currentTimeMillis());
        }
        return getYear(dayTime.getTime());
    }	//	getMonth

    public static int getYearsBetween(Timestamp start, Timestamp end) {
        Calendar startCal = getCalendar(start);
        Calendar endCal = getCalendar(end);
        //
        int years = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);

        if (years < 0) {
            years = -1 * years;
        }

        if (years == 0) {
            years = 1;
        }

        return years;
    }

    public static int getYearsBetween(Timestamp start, LocalDateTime end) {
       Timestamp t = Timestamp.valueOf(end);
       return getYearsBetween(start, t);
    }

    
    static public Timestamp addYears(Timestamp day, int offset) {
        boolean isFirsDay = false;
        if (day == null) {
            isFirsDay = true;
        }
        //
        Calendar cal = getCalendar(day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        if (isFirsDay) {
            cal.set(Calendar.DATE, 1);
            cal.set(Calendar.MONTH, 0);
        }

        if (offset == 0) {
            return new Timestamp(cal.getTimeInMillis());
        }
        cal.add(Calendar.YEAR, offset);
        return new Timestamp(cal.getTimeInMillis());
    }	//	addYears

    static public boolean inRange(Timestamp start_1, Timestamp end_1, Timestamp time) {
        return inRange(start_1, end_1, time, false);
    }

    static public boolean inRange(Timestamp start_1, Timestamp end_1, Timestamp time, boolean isAcceptNull) {
        if (start_1 == null || time == null) {
            return false;
        }

        if (!isAcceptNull && end_1 == null) {
            return false;
        }

        if (end_1 != null && time.after(end_1)) {
            return false;
        }

        if (time.before(start_1)) {
            return false;
        }

        return true;
    }	//	inRange

    public static boolean compareTwoDateEx(Timestamp fromDate, Timestamp toDate) {
        if (fromDate == null || toDate == null) {
            return true;
        }
        fromDate = removeTime(fromDate);
        toDate = removeTime(toDate);
        if (fromDate.compareTo(toDate) >= 0) {
            return false;
        }
        return true;
    }

    public static String formatDate(long time, String stringFormat) {
        SimpleDateFormat sdfDate = new SimpleDateFormat(stringFormat);
        String strDate = sdfDate.format(time);
        return strDate;
    }

    static public Timestamp addDays(Timestamp day, int offset) {
        if (day == null) {
            day = new Timestamp(System.currentTimeMillis());
        }
        //
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(day);
        if (offset == 0) {
            return new Timestamp(cal.getTimeInMillis());
        }
        cal.add(Calendar.DAY_OF_YEAR, offset); // may have a problem with negative (before 1/1)
        return new Timestamp(cal.getTimeInMillis());
    }

    /**
     * Return DateTime + offset in minutes
     *
     * @param dateTime Date and Time
     * @param offset minute offset
     * @return dateTime + offset in minutes
     */
    static public Timestamp addHours(Timestamp dateTime, int offset) {
        if (dateTime == null) {
            dateTime = new Timestamp(System.currentTimeMillis());
        }
        if (offset == 0) {
            return dateTime;
        }
        //
        GregorianCalendar cal = new GregorianCalendar(getLocale());
        cal.setTime(dateTime);
        cal.add(Calendar.HOUR, offset);			//	may have a problem with negative
        return new Timestamp(cal.getTimeInMillis());
    }	//	addMinutes

    public static int getMinutesBetween(Timestamp fromDate, Timestamp toDate) {
        if (fromDate.compareTo(toDate) >= 0) {
            return 0;
        }

        long diff = toDate.getTime() - fromDate.getTime();

        return (int) (diff / (60 * 1000));
    }

    public static int getHoursBetween(Timestamp fromDate, Timestamp toDate) {
        return getMinutesBetween(fromDate, toDate) / 60;
    }

    public static int compareToTime(Timestamp start, Timestamp end) {

        int startHourMinute = getHourMinute(start);
        int endHourMinute = getHourMinute(end);
        return startHourMinute - endHourMinute;
    }

    static public Calendar getCalendar(Timestamp date) {
        GregorianCalendar cal = new GregorianCalendar(getLocale());
        if (date != null) {
            cal.setTimeInMillis(date.getTime());
        }
        return cal;
    }

    public static int calculateAge(Timestamp birthday, Timestamp timeGoIn) {
        if (birthday == null) {
            return 0;
        }

        return TimeUtil.getYearsBetween(birthday, timeGoIn);
    }

    public static int calculateAge(LocalDateTime birthday, LocalDateTime timeGoIn) {
        Timestamp b = Timestamp.valueOf(birthday);
        Timestamp t = Timestamp.valueOf(timeGoIn);
        return calculateAge(b, t);
    }

    public static LocalDateTime calculateBirthday(int age) {
        if (age <= 0) {
            return null;
        }

        return TimeUtil.addYears(null, age * -1).toLocalDateTime();
    }

    public static Locale getLocale() {
        return new Locale("vi_VN", "vi_VN", "vi_VN");
    }
}
