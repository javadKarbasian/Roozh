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

package io.github.meness.roozh.components;

import java.util.Calendar;

import io.github.meness.roozh.Presentation;
import io.github.meness.roozh.RoozhLocale;
import io.github.meness.roozh.locales.DariLocale;
import io.github.meness.roozh.locales.EnglishLocale;
import io.github.meness.roozh.locales.KurdishLocale;
import io.github.meness.roozh.locales.PashtoLocale;
import io.github.meness.roozh.locales.PersianLocale;

/**
 * @since 2.7.2
 */
public class AmPm extends AbstractComponent {

    public AmPm() {
        super(Presentation.TEXT);
    }

    public String getAmPm(RoozhLocale locale, int i) {
        if (i == Calendar.AM) {
            switch (locale) {
                default:
                case PERSIAN:
                    return PersianLocale.AM;
                case DARI:
                    return DariLocale.AM;
                case ENGLISH:
                    return EnglishLocale.AM;
                case KURDISH:
                    return KurdishLocale.AM;
                case PASHTO:
                    return PashtoLocale.AM;
            }
        } else {
            switch (locale) {
                default:
                case PERSIAN:
                    return PersianLocale.PM;
                case DARI:
                    return DariLocale.PM;
                case ENGLISH:
                    return EnglishLocale.PM;
                case KURDISH:
                    return KurdishLocale.PM;
                case PASHTO:
                    return PashtoLocale.PM;
            }
        }
    }
}