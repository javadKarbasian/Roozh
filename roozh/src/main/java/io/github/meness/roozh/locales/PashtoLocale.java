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
 * Pashto locale
 *
 * @since 3.3.1
 */
public class PashtoLocale extends Roozh {
    public PashtoLocale() {
        locale = RoozhLocale.PASHTO;
    }

    public enum Month {
        FARVARDIN("\u0648\u0631\u06cc"),
        ORDIBEHESHT("\u063a\u0648\u064a\u06cc"),
        KHORDAD("\u063a\u0628\u0631\u06ab\u0648\u0644\u06cc"),
        TIR("\u0686\u0646\u06ab\u0627\u069a"),
        MORDAD("\u0632\u0645\u0631\u06cc"),
        SHAHRIVAR("\u0648\u0696\u06cc"),
        MEHR("\u062a\u0644\u0647"),
        ABAN("\u0644\u0693\u0645"),
        AZAR("\u0644\u064a\u0646\u062f\u06cd"),
        DEY("\u0645\u0631\u063a\u0648\u0645\u06cc"),
        BAHMAN("\u0633\u0644\u0648\u0627\u063a\u0647"),
        ESFAND("\u06a9\u0628");

        private String s;

        Month(String s) {
            this.s = s;
        }

        public static String getS(int i) {
            return Month.values()[i - 1].getS();
        }

        public String getS() {
            return s;
        }
    }
}
