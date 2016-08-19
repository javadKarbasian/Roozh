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
 * Kurdish locale
 *
 * @since 3.1.1
 */
public class KurdishLocale extends Roozh {
    public static final String AM = "\u067e\u002e\u0646";
    public static final String PM = "\u062f\u002e\u0646";

    public KurdishLocale() {
        locale = RoozhLocale.KURDISH;
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
        SATURDAY("\u0634\u06d5\u0645\u0645\u06d5"),
        SUNDAY("\u06cc\u06d5\u06a9\u0634\u06d5\u0645\u0645\u06d5"),
        MONDAY("\u062f\u0648\u0648\u0634\u06d5\u0645\u0645\u06d5"),
        TUESDAY("\u0633\u06ce\u0634\u06d5\u0645\u0645\u06d5"),
        WEDNESDAY("\u0686\u0648\u0627\u0631\u0634\u06d5\u0645\u0645\u06d5"),
        THURSDAY("\u067e\u06ce\u0646\u062c\u0634\u06d5\u0645\u0645\u06d5"),
        FRIDAY("\u06be\u06d5\u06cc\u0646\u06cc");
        private String s;

        DayOfWeek(String m) {
            this.s = m;
        }

        public static String getS(int i) {
            return DayOfWeek.values()[i].s;
        }
    }

    public enum Month {
        FARVARDIN("\u062e\u0627\u06a9\u06d5\u0644\u06ce\u0648\u06d5"),
        ORDIBEHESHT("\u06af\u0648\u06b5\u0627\u0646"),
        KHORDAD("\u062c\u06c6\u0632\u06d5\u0631\u062f\u0627\u0646"),
        TIR("\u067e\u0648\u0648\u0634\u067e\u06d5\u0695"),
        MORDAD("\u06af\u06d5\u0644\u0627\u0648\u06ce\u0698"),
        SHAHRIVAR("\u062e\u06d5\u0631\u0645\u0627\u0646\u0627\u0646"),
        MEHR("\u0695\u06d5\u0632\u0628\u06d5\u0631"),
        ABAN("\u062e\u06d5\u0632\u06d5\u06b5\u0648\u06d5\u0631"),
        AZAR("\u0633\u06d5\u0631\u0645\u0627\u0648\u06d5\u0632"),
        DEY("\u0628\u06d5\u0641\u0631\u0627\u0646\u0628\u0627\u0631"),
        BAHMAN("\u0695\u06ce\u0628\u06d5\u0646\u062f\u0627\u0646"),
        ESFAND("\u0695\u06d5\u0634\u06d5\u0645\u06d5");

        private String s;

        Month(String m) {
            this.s = m;
        }

        public static String getS(int i) {
            return Month.values()[i - 1].s;
        }
    }
}
