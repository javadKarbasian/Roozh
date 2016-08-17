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
    private StringBuilder stringBuilder;

    /**
     * New string builder will be used
     *
     * @see #RoozhFormatter(StringBuilder) for providing already defined string builder
     * @see #RoozhFormatter(RoozhFormatter) for usind old formatter fields
     */
    public RoozhFormatter() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Fields of fresh instance of formatter will be replaced with the old one provided.
     *
     * @param formatter Old formatter
     * @see #RoozhFormatter(StringBuilder) for providing already defined string builder
     * @see #RoozhFormatter()
     */
    public RoozhFormatter(RoozhFormatter formatter) {
        elements = formatter.elements;
        stringBuilder = formatter.stringBuilder;
    }

    /**
     * Last result will be appended to provided string builder
     *
     * @param stringBuilder already defined StringBuilder
     * @see #RoozhFormatter()
     * @see #RoozhFormatter(RoozhFormatter) for usind old formatter fields
     */
    public RoozhFormatter(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
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

    /**
     * Build with current time and default locale
     * Note: Elements order is important.
     *
     * @return Formatted string
     * @see #build(Roozh)
     * @see #build(RoozhLocale)
     */
    public String build() {
        return build(Roozh.getInstance().gregorianToPersian());
    }

    /**
     * Build with provided Roozh
     *
     * @param roozh Roozh
     * @return Formatted string
     * @see #build()
     * @see #build(RoozhLocale)
     */
    public String build(Roozh roozh) {
        if (elements.isEmpty()) {
            throw new RuntimeException("You have to put some elements first.");
        }

        for (Object element : elements) {
            if (element instanceof String || element instanceof Character) {
                stringBuilder.append(element);
            } else {
                stringBuilder.append(formatByPresentation((AbstractComponent) element, roozh));
            }
        }

        return stringBuilder.toString();
    }

    /**
     * format components by provided presentations
     *
     * @param component Component
     * @param roozh     Roozh
     * @return Formatted string
     */
    private String formatByPresentation(AbstractComponent component, Roozh roozh) {
        switch (component.getPresentation()) {
            case MONTH:
                if (component.getMinimumLength() == 1) {
                    return Integer.toString(roozh.getMonth());
                } else if (component.getMinimumLength() == 2) {
                    return leadingZero(roozh.getMonth());
                } else {
                    return roozh.getMonthName();
                }
            case NUMBER:
                if (component instanceof Hour) {
                    if (((Hour) component).getClock() == Hour.Clock.CLOCK_12) {
                        if (component.getMinimumLength() == 1) {
                            return Integer.toString(roozh.getHour());
                        } else if (component.getMinimumLength() == 2) {
                            return leadingZero(roozh.getHour());
                        }
                    } else {
                        if (component.getMinimumLength() == 1) {
                            return Integer.toString(roozh.getHourOfDay());
                        } else if (component.getMinimumLength() == 2) {
                            return leadingZero(roozh.getHourOfDay());
                        }
                    }
                } else if (component instanceof Minute) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getMinute());
                    }
                    return leadingZero(roozh.getMinute());
                } else if (component instanceof Second) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getSecond());
                    }
                    return leadingZero(roozh.getSecond());
                } else if (component instanceof Millisecond) {
                    if (component.getMinimumLength() == 1) {
                        return Long.toString(roozh.getMillisecond());
                    }
                    return leadingZero(roozh.getMillisecond());
                } else if (component instanceof DayOfMonth) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getDayOfMonth());
                    }
                    return leadingZero(roozh.getDayOfMonth());
                }
            case TEXT:
                if (component instanceof AmPm) {
                    return ((AmPm) component).getAmPm(roozh.locale, roozh.getAmPm());
                }
                break;
            case YEAR:
                if (component.getMinimumLength() == 2) {
                    return Integer.toString(roozh.getYear()).replaceAll("^[0-9]{2}", "");
                }
                return Integer.toString(roozh.getYear());
        }
        return null;
    }

    /**
     * insert leading zero if needed
     *
     * @param i Integer to be formatted
     * @return Formatted string
     */
    private String leadingZero(long i) {
        String sI = Long.toString(i);
        if (sI.length() == 1) {
            return new StringBuilder(sI).insert(0, '0').toString();
        }
        return sI;
    }

    /**
     * Build with current time and defined locale
     * Note: Elements order is important.
     *
     * @return Formatted string
     * @see #build(Roozh)
     * @see #build()
     */
    public String build(RoozhLocale locale) {
        return build(Roozh.getInstance(locale).gregorianToPersian());
    }
}
