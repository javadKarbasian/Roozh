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

import io.github.meness.roozh.Roozh;
import io.github.meness.roozh.RoozhLocale;

/**
 * Persian locale
 *
 * @since 3.0
 */
public class PersianLocale extends Roozh {
    public PersianLocale() {
        locale = RoozhLocale.PERSIAN;
    }

    public static class Month {
        public enum Long {
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

            Long(String s) {
                this.s = s;
            }

            public static String getS(int i) {
                return Long.values()[i - 1].getS();
            }

            public String getS() {
                return s;
            }
        }

        public enum Short {
            FARVARDIN_SHORT("\u0641\u0631\u0648"),
            ORDIBEHESHT_SHORT("\u0627\u0631\u062f"),
            KHORDAD_SHORT("\u062e\u0631\u062f"),
            TIR_SHORT("\u062a\u06cc\u0631"),
            MORDAD_SHORT("\u0645\u0631\u062f"),
            SHAHRIVAR_SHORT("\u0634\u0647\u0631"),
            MEHR_SHORT("\u0645\u0647\u0631"),
            ABAN_SHORT("\u0622\u0628\u0627"),
            AZAR_SHORT("\u0622\u0630\u0631"),
            DEY_SHORT("\u062f\u06cc"),
            BAHMAN_SHORT("\u0628\u0647\u0645"),
            ESFAND_SHORT("\u0627\u0633\u0641");

            private String s;

            Short(String s) {
                this.s = s;
            }

            public static String getS(int i) {
                return Short.values()[i - 1].getS();
            }

            public String getS() {
                return s;
            }
        }
    }
}
