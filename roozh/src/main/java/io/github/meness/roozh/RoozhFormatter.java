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

import java.util.ArrayList;

import io.github.meness.roozh.components.AbstractComponent;
import io.github.meness.roozh.components.AmPm;
import io.github.meness.roozh.components.DayOfMonth;
import io.github.meness.roozh.components.DayOfWeek;
import io.github.meness.roozh.components.Hour;
import io.github.meness.roozh.components.Millisecond;
import io.github.meness.roozh.components.Minute;
import io.github.meness.roozh.components.Month;
import io.github.meness.roozh.components.Second;
import io.github.meness.roozh.components.Year;

/**
 * format Roozh and make desired output
 *
 * @since 2.0
 */
public class RoozhFormatter {
    private ArrayList<Object> elements = new ArrayList<>();
    private StringBuilder stringBuilder = new StringBuilder();
    private Roozh roozh;

    /**
     * New string builder will be used
     *
     * @see #RoozhFormatter(Roozh, StringBuilder) for providing already defined string builder
     * @see #RoozhFormatter(RoozhFormatter) for usind old formatter fields
     */
    public RoozhFormatter(Roozh r) {
        roozh = r;
    }

    /**
     * Fields of fresh instance of formatter will be replaced with the old one provided.
     *
     * @param formatter Old formatter
     * @see #RoozhFormatter(Roozh, StringBuilder) for providing already defined string builder
     */
    public RoozhFormatter(RoozhFormatter formatter) {
        elements = formatter.elements;
        stringBuilder = formatter.stringBuilder;
        roozh = formatter.roozh;
    }

    /**
     * Last result will be appended to provided string builder
     *
     * @param stringBuilder already defined StringBuilder
     * @param r             Roozh
     * @see #RoozhFormatter(RoozhFormatter) for usind old formatter fields
     */
    public RoozhFormatter(Roozh r, StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
        roozh = r;
    }

    /**
     * Clear elements
     *
     * @return this
     */
    public RoozhFormatter clear() {
        elements.clear();
        return this;
    }

    /**
     * Clean string builder
     *
     * @return this
     */
    public RoozhFormatter clean() {
        stringBuilder = new StringBuilder();
        return this;
    }

    /**
     * Append space character
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendSpace() {
        return appendCharacter(' ');
    }

    /**
     * Append character
     *
     * @param c character
     * @return this
     * @see #appendText(String)
     */
    public RoozhFormatter appendCharacter(char c) {
        elements.add(c);
        return this;
    }

    /**
     * Append newline
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendNewLine() {
        return appendText("\r\n");
    }

    /**
     * Append text
     *
     * @param t text
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendText(String t) {
        if (t == null) {
            throw new NullPointerException("Argument cannot be null.");
        }
        elements.add(t);
        return this;
    }

    /**
     * Append slash character
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendSlash() {
        return appendCharacter('/');
    }

    /**
     * Append dot character
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendDot() {
        return appendCharacter('.');
    }

    /**
     * Append hyphen character
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendHyphen() {
        return appendCharacter('-');
    }

    /**
     * Append color character
     *
     * @return this
     * @see #appendCharacter(char)
     */
    public RoozhFormatter appendColon() {
        return appendCharacter(':');
    }

    /**
     * Append day of month with/without leading zero
     *
     * @return this
     */
    public RoozhFormatter appendDayOfMonth(boolean leadingZero) {
        elements.add(new DayOfMonth().setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append 12-clock hour with/without leading zero
     *
     * @return this
     * @see #appendHourOfDay(boolean) for 24-clock
     */
    public RoozhFormatter appendHour(boolean leadingZero) {
        elements.add(new Hour(Hour.Clock.CLOCK_12).setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append minute with/without leading zero
     *
     * @param leadingZero With leading zero
     * @return this
     */
    public RoozhFormatter appendMinute(boolean leadingZero) {
        elements.add(new Minute().setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append second with/without leading zero
     *
     * @param leadingZero With leading zero
     * @return this
     */
    public RoozhFormatter appendSecond(boolean leadingZero) {
        elements.add(new Second().setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append millisecond
     *
     * @return this
     */
    public RoozhFormatter appendMillisecond() {
        elements.add(new Millisecond());
        return this;
    }

    /**
     * Append AM/PM
     * Note: It's useful for using with {@link #appendHour(boolean)}
     *
     * @return this
     */
    public RoozhFormatter appendAmPm() {
        elements.add(new AmPm());
        return this;
    }

    /**
     * Append 24-clock hour with/without leading zero
     *
     * @return this
     * @see #appendHour(boolean) for 12-clock
     */
    public RoozhFormatter appendHourOfDay(boolean leadingZero) {
        elements.add(new Hour(Hour.Clock.CLOCK_24).setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append month with leading zero
     *
     * @return this
     * @see #appendMonth()
     * @see #appendMonthName()
     */
    public RoozhFormatter appendMonthLeadingZero() {
        elements.add(new Month().setMinimumLength(2));
        return this;
    }

    /**
     * Append month number with no leading zero
     *
     * @return this
     * @see #appendMonthLeadingZero()
     * @see #appendMonthName()
     */
    public RoozhFormatter appendMonth() {
        elements.add(new Month().setMinimumLength(1));
        return this;
    }

    /**
     * Append day of week with leading zero
     *
     * @return this
     * @see #appendDayOfWeek()
     * @see #appendDayOfWeekLeadingZero()
     */
    public RoozhFormatter appendDayOfWeekText() {
        elements.add(new DayOfWeek().setMinimumLength(3));
        return this;
    }

    /**
     * Append day of week with leading zero
     *
     * @return this
     * @see #appendDayOfWeek()
     * @see #appendDayOfWeekText()
     */
    public RoozhFormatter appendDayOfWeekLeadingZero() {
        elements.add(new DayOfWeek().setMinimumLength(2));
        return this;
    }

    /**
     * Append day of week with no leading zero
     *
     * @return this
     * @see #appendDayOfWeekLeadingZero()
     * @see #appendDayOfWeekText()
     */
    public RoozhFormatter appendDayOfWeek() {
        elements.add(new DayOfWeek().setMinimumLength(1));
        return this;
    }

    /**
     * Append month with full name
     *
     * @return this
     * @see #appendMonth()
     * @see #appendMonthLeadingZero()
     */
    public RoozhFormatter appendMonthName() {
        elements.add(new Month().setMinimumLength(4));
        return this;
    }

    /**
     * Append year with minimum digits
     *
     * @return this
     */
    public RoozhFormatter appendYear(boolean shortYear) {
        elements.add(new Year().setMinimumLength(shortYear ? 2 : 4));
        return this;
    }

    public String build() {
        return buildFormatter(roozh);
    }

    /**
     * Build with provided Roozh
     *
     * @param roozh Roozh
     * @return Formatted string
     */
    private String buildFormatter(Roozh roozh) {
        if (elements.isEmpty()) {
            throw new RuntimeException("You have to put some elements first.");
        }

        for (Object element : elements) {
            if (element instanceof AbstractComponent) {
                stringBuilder.append(((AbstractComponent) element).process(roozh));
            } else {
                stringBuilder.append(element);
            }
        }

        return stringBuilder.toString();
    }
}
