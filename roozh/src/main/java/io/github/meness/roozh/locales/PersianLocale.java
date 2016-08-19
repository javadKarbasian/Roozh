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

package io.github.meness.roozh.locales;

import java.util.Calendar;

import io.github.meness.roozh.Roozh;
import io.github.meness.roozh.RoozhLocale;

/**
 * Persian locale
 *
 * @since 3.0
 */
public class PersianLocale extends Roozh {
    public static final String AM = "\u0642\u002e\u0638";
    public static final String PM = "\u0628\u002e\u0638";

    public PersianLocale() {
        locale = RoozhLocale.PERSIAN;
    }

    @Override
    public String getDayOfWeekText(int dow) {
        return DayOfWeek.getS(dow);
    }

    @Override
    public String getAmPmText(int am_pm) {
        if (am_pm == Calendar.AM) {
            return AM;
        } else {
            return PM;
        }
    }

    @Override
    public String getMonthName(int month) {
        return Month.getS(month);
    }

    public enum DayOfWeek {
        SATURDAY("\u0634\u0646\u0628\u0647"),
        SUNDAY("\u06cc\u06a9\u200c\u0634\u0646\u0628\u0647"),
        MONDAY("\u062f\u0648\u0634\u0646\u0628\u0647"),
        TUESDAY("\u0633\u0647\u200c\u0634\u0646\u0628\u0647"),
        WEDNESDAY("\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647"),
        THURSDAY("\u067e\u0646\u062c\u200c\u0634\u0646\u0628\u0647"),
        FRIDAY("\u062c\u0645\u0639\u0647");
        private String s;

        DayOfWeek(String m) {
            this.s = m;
        }

        public static String getS(int i) {
            return DayOfWeek.values()[i].s;
        }
    }

    public enum Month {
        FARVARDIN("\u0641\u0631\u0648\u0631\u062f\u06cc\u0646"),
        ORDIBEHESHT("\u0627\u0631\u062f\u06cc\u0628\u0647\u0634\u062a"),
        KHORDAD("\u062e\u0631\u062f\u0627\u062f"),
        TIR("\u062a\u06cc\u0631"),
        MORDAD("\u0645\u0631\u062f\u0627\u062f"),
        SHAHRIVAR("\u0634\u0647\u0631\u06cc\u0648\u0631"),
        MEHR("\u0645\u0647\u0631"),
        ABAN("\u0622\u0628\u0627\u0646"),
        AZAR("\u0622\u0630\u0631"),
        DEY("\u062f\u06cc"),
        BAHMAN("\u0628\u0647\u0645\u0646"),
        ESFAND("\u0627\u0633\u0641\u0646\u062f");

        private String s;

        Month(String m) {
            this.s = m;
        }

        public static String getS(int i) {
            return Month.values()[i - 1].s;
        }
    }
}
