/*
 * Copyright 2016 Alireza Eskandarpour Shoferi
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.meness.roozh;

import java.util.Calendar;
import java.util.TimeZone;

import io.github.meness.roozh.locales.DariLocale;
import io.github.meness.roozh.locales.EnglishLocale;
import io.github.meness.roozh.locales.KurdishLocale;
import io.github.meness.roozh.locales.PashtoLocale;
import io.github.meness.roozh.locales.PersianLocale;

/**
 * This class contains methods for converting Jalali (Solar) and Gregorian dates
 * into each other based Kazimierz M. Borkowski paper about Jalali date.
 *
 * @author Alireza Eskandarpour Shoferi
 * @author Kaveh Shahbazian
 * @version 3.4.1
 * @see <a href="https://github.com/meNESS/Roozh/">Roozh on Github</a>
 * @see <a href="http://www.astro.uni.torun.pl/~kb/Papers/EMP/PersianC-EMP.htm">The Persian calendar for 3000 years</a>
 * @since 0.0.1-alpha
 */

public abstract class Roozh {
    public static final String AM = "\u0642\u002e\u0638";
    public static final String PM = "\u0628\u002e\u0638";
    protected RoozhLocale locale;
    private Calendar calendar;
    private int iJY, iJM, iJD;
    private int iGY, iGM, iGD;
    private int iLeap, iMarch;

    /**
     * create Roozh instance with default Persian locale
     *
     * @return Roozh instance
     */
    public static Roozh getInstance() {
        return createInstance(RoozhLocale.PERSIAN);
    }

    /**
     * return new Roozh instance based on locale
     *
     * @param locale locale
     * @return Roozh instance in defined locale
     */
    private static Roozh createInstance(RoozhLocale locale) {
        switch (locale) {
            default:
            case PERSIAN:
                return new PersianLocale();
            case KURDISH:
                return new KurdishLocale();
            case DARI:
                return new DariLocale();
            case PASHTO:
                return new PashtoLocale();
            case ENGLISH:
                return new EnglishLocale();
        }
    }

    public static Roozh getInstance(RoozhLocale locale) {
        return createInstance(locale);
    }

    /**
     * Get manipulated day
     *
     * @return Day as <code>int</code>
     * @see Calendar#DAY_OF_MONTH
     */
    public int getDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Get manipulated month
     *
     * @return Month as <code>int</code>
     * @see Calendar#MONTH
     */
    public int getMonth() {
        return calendar.get(Calendar.MONTH);
    }

    /**
     * Get manipulated millisecond
     *
     * @return Millisecond as <code>int</code>
     * @see Calendar#MILLISECOND
     */
    public int getMillisecond() {
        return calendar.get(Calendar.MILLISECOND);
    }

    /**
     * Get manipulated second
     *
     * @return Second as <code>int</code>
     * @see Calendar#SECOND
     */
    public int getSecond() {
        return calendar.get(Calendar.SECOND);
    }

    /**
     * Get manipulated minute
     *
     * @return Minute as <code>int</code>
     * @see Calendar#MINUTE
     */
    public int getMinute() {
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * Get manipulated hour
     *
     * @return Hour as <code>int</code>
     * @see Calendar#HOUR
     */
    public int getHour() {
        return calendar.get(Calendar.HOUR);
    }

    /**
     * Get manipulated hour of day
     *
     * @return Hour of day as <code>int</code>
     * @see Calendar#HOUR_OF_DAY
     */
    public int getHourOfDay() {
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * Get manipulated AM/PM
     *
     * @return AM/PM as <code>int</code>
     * @see Calendar#AM_PM
     */
    public int getAmPm() {
        return calendar.get(Calendar.AM_PM);
    }

    /**
     * Get manipulated year
     *
     * @return Year as <code>int</code>
     * @see Calendar#YEAR
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Convert current Gregorian to Persian
     *
     * @return this
     */
    public Roozh gregorianToPersian() {
        gregorianToPersian(System.currentTimeMillis());
        return this;
    }

    /**
     * Convert Gregorian time to Persian
     *
     * @param time Time
     * @return this
     */
    public Roozh gregorianToPersian(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        return gregorianToPersian(calendar);
    }

    public Roozh gregorianToPersian(Calendar cal) {
        updateCalendarForTehran(cal);
        // months start from 0
        int jd = JG2JD(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), 0);
        JD2Jal(jd);
        calendar = cal;
        calendar.set(Calendar.YEAR, iJY);
        calendar.set(Calendar.MONTH, iJM);
        calendar.set(Calendar.DAY_OF_MONTH, iJD);

        return this;
    }

    /**
     * changes to datetime calendar obj for Tehran
     */
    private void updateCalendarForTehran(Calendar cal) {
        // set first day of week in Iran
        cal.setFirstDayOfWeek(Calendar.SATURDAY);
        // set Asia/Tehran as timezone but doesn't work!
        cal.setTimeZone(TimeZone.getTimeZone("Asia/Tehran"));
    }

    /**
     * Calculates the Julian Day number (JG2JD) from Gregorian or Julian
     * calendar dates. This integer number corresponds to the noon of the date
     * (i.e. 12 hours of Universal Time). The procedure was tested to be good
     * since 1 March, -100100 (of both the calendars) up to a few millions
     * (10**6) years into the future. The algorithm is based on D.A. Hatcher,
     * Q.Jl.R.Astron.Soc. 25(1984), 53-55 slightly modified by me (K.M.
     * Borkowski, Post.Astron. 25(1987), 275-279).
     *
     * @param year  <code>int</code>
     * @param month <code>int</code>
     * @param day   <code>int</code>
     * @param J1G0  to be set to 1 for Julian and to 0 for Gregorian calendar
     * @return Julian Day number
     */
    private int JG2JD(int year, int month, int day, int J1G0) {
        int jd = (1461 * (year + 4800 + (month - 14) / 12)) / 4
                + (367 * (month - 2 - 12 * ((month - 14) / 12))) / 12
                - (3 * ((year + 4900 + (month - 14) / 12) / 100)) / 4 + day
                - 32075;

        if (J1G0 == 0)
            jd = jd - (year + 100100 + (month - 8) / 6) / 100 * 3 / 4 + 752;

        return jd;
    }

    /**
     * Converts the Julian Day number to a date in the Jalali calendar
     *
     * @param JDN the Julian Day number
     */
    private void JD2Jal(int JDN) {
        JD2JG(JDN, 0);

        iJY = iGY - 621;
        JalCal(iJY);

        int JDN1F = JG2JD(iGY, 3, iMarch, 0);
        int k = JDN - JDN1F;
        if (k >= 0) {
            if (k <= 185) {
                iJM = 1 + k / 31;
                iJD = (k % 31) + 1;
                return;
            } else {
                k = k - 186;
            }
        } else {
            iJY = iJY - 1;
            k = k + 179;
            if (iLeap == 1)
                k = k + 1;
        }

        iJM = 7 + k / 30;
        iJD = (k % 30) + 1;
    }

    /**
     * Calculates Gregorian and Julian calendar dates from the Julian Day number
     * (JD) for the period since JD=-34839655 (i.e. the year -100100 of both the
     * calendars) to some millions (10**6) years ahead of the present. The
     * algorithm is based on D.A. Hatcher, Q.Jl.R.Astron.Soc. 25(1984), 53-55
     * slightly modified by me (K.M. Borkowski, Post.Astron. 25(1987), 275-279).
     *
     * @param JD   Julian day number as <code>int</code>
     * @param J1G0 to be set to 1 for Julian and to 0 for Gregorian calendar
     */
    private void JD2JG(int JD, int J1G0) {
        int i, j;

        j = 4 * JD + 139361631;

        if (J1G0 == 0) {
            j = j + (4 * JD + 183187720) / 146097 * 3 / 4 * 4 - 3908;
        }

        i = (j % 1461) / 4 * 5 + 308;
        iGD = (i % 153) / 5 + 1;
        iGM = ((i / 153) % 12) + 1;
        iGY = j / 1461 - 100100 + (8 - iGM) / 6;
    }

    /**
     * This procedure determines if the Jalali (Persian) year is leap (366-day
     * long) or is the common year (365 days), and finds the day in March
     * (Gregorian calendar) of the first day of the Jalali year (jY)
     *
     * @param jY Jalali calendar year (-61 to 3177)
     */
    private void JalCal(int jY) {
        iMarch = 0;
        iLeap = 0;

        int[] breaks = {-61, 9, 38, 199, 426, 686, 756, 818, 1111, 1181, 1210,
                1635, 2060, 2097, 2192, 2262, 2324, 2394, 2456, 3178};

        iGY = jY + 621;
        int leapJ = -14;
        int jp = breaks[0];

        int jump;
        for (int j = 1; j <= 19; j++) {
            int jm = breaks[j];
            jump = jm - jp;
            if (jY < jm) {
                int N = jY - jp;
                leapJ = leapJ + N / 33 * 8 + (N % 33 + 3) / 4;

                if ((jump % 33) == 4 && (jump - N) == 4)
                    leapJ = leapJ + 1;

                int leapG = (iGY / 4) - (iGY / 100 + 1) * 3 / 4 - 150;

                iMarch = 20 + leapJ - leapG;

                if ((jump - N) < 6)
                    N = N - jump + (jump + 4) / 33 * 33;

                iLeap = ((((N + 1) % 33) - 1) % 4);

                if (iLeap == -1)
                    iLeap = 4;
                break;
            }

            leapJ = leapJ + jump / 33 * 8 + (jump % 33) / 4;
            jp = jm;
        }
    }

    /**
     * Converts Persian(Jalali) date to Gregorian date
     */
    public void persianToGregorian(Calendar cal) {
        updateCalendarForTehran(cal);
        int jd = Jal2JD(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
        JD2JG(jd, 0);
        calendar = cal;
        calendar.set(Calendar.YEAR, iGY);
        calendar.set(Calendar.MONTH, iGM);
        calendar.set(Calendar.DAY_OF_MONTH, iGD);
    }

    /**
     * Converts a date of the Jalali calendar to the Julian Day Number
     *
     * @param jY Jalali year as <code>int</code>
     * @param jM Jalali month as <code>int</code>
     * @param jD Jalali day as <code>int</code>
     * @return Julian day number
     */
    private int Jal2JD(int jY, int jM, int jD) {
        JalCal(jY);
        return JG2JD(iGY, 3, iMarch, 1) + (jM - 1) * 31 - jM / 7 * (jM - 7)
                + jD - 1;
    }

    /**
     * get month name
     *
     * @return String month name
     */
    public String getMonthName() {
        switch (locale) {
            default:
            case PERSIAN:
                return PersianLocale.Month.getS(calendar.get(Calendar.MONTH));
            case KURDISH:
                return KurdishLocale.Month.getS(calendar.get(Calendar.MONTH));
            case DARI:
                return DariLocale.Month.getS(calendar.get(Calendar.MONTH));
            case PASHTO:
                return PashtoLocale.Month.getS(calendar.get(Calendar.MONTH));
            case ENGLISH:
                return EnglishLocale.Month.getS(calendar.get(Calendar.MONTH));
        }
    }
}
