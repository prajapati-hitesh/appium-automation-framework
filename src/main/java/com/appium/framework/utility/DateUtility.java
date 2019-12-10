package com.appium.framework.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtility {

    public String getCurrentTimeStampWithFormatAs(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    public String getCurrentTimeStamp() {
        return new SimpleDateFormat("dd-MM-yyyy_HH.mm.ss").format(new Date());
    }

    public String getCurrentMonth() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return  localDate.getMonth().name();
    }

    public int getCurrentDate() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return  localDate.getDayOfMonth();
    }

    public int getCurrentYear() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return  localDate.getYear();
    }

    public int getCurrentMonthAsNumber() {
        LocalDate localDate = LocalDate.now();

        /* Methods that can be used
            localDate.getYear() --> This will return year in int. i.e. 2019,2018 etc
            localDate.getDayOfMonth() --> This will return current date. i.e. 1,2,3,4....31
            localDate.getDayOfWeek() --> This will return day of week. i.e. Monday, Tuesday
            localDate.getDayOfYear() --> This will return day of year for current date. i.e. 311th day for date 7th November 2019
            localDate.getMonth().name() --> This will return the current month as string. i.e. JANUARY, FEBRUARY .... DECEMBER
         */

        return  localDate.getMonthValue();
    }
}
