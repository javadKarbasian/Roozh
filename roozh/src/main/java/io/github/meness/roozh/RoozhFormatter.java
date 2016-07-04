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
import io.github.meness.roozh.components.DayOfMonth;
import io.github.meness.roozh.components.Hour;
import io.github.meness.roozh.components.Millisecond;
import io.github.meness.roozh.components.Minute;
import io.github.meness.roozh.components.Month;
import io.github.meness.roozh.components.Second;
import io.github.meness.roozh.components.Year;

/**
 * @since 2.0
 */
public class RoozhFormatter {
    private ArrayList<Object> elements = new ArrayList<>();
    private StringBuilder stringBuilder;

    /**
     * New string builder will be used
     *
     * @see RoozhFormatter#RoozhFormatter(StringBuilder) for providing already defined string builder
     * @see RoozhFormatter#RoozhFormatter(RoozhFormatter) for usind old formatter fields
     */
    public RoozhFormatter() {
        stringBuilder = new StringBuilder();
    }

    /**
     * Fields of fresh instance of formatter will be replaced with the old one provided.
     *
     * @param formatter Old formatter
     * @see RoozhFormatter#RoozhFormatter(StringBuilder) for providing already defined string builder
     * @see RoozhFormatter#RoozhFormatter()
     */
    public RoozhFormatter(RoozhFormatter formatter) {
        elements = formatter.elements;
        stringBuilder = formatter.stringBuilder;
    }

    /**
     * Last result will be appended to provided string builder
     *
     * @param stringBuilder already defined StringBuilder
     * @see RoozhFormatter#RoozhFormatter()
     * @see RoozhFormatter#RoozhFormatter(RoozhFormatter) for usind old formatter fields
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
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendHyphen()
     * @see RoozhFormatter#appendColon()
     */
    public RoozhFormatter appendSpace() {
        return appendCharacter(' ');
    }

    /**
     * Append character
     *
     * @param c character
     * @return this
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSpace()
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendHyphen()
     * @see RoozhFormatter#appendColon()
     */
    public RoozhFormatter appendCharacter(char c) {
        elements.add(c);
        return this;
    }

    /**
     * Append slash character
     *
     * @return this
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSpace()
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendHyphen()
     * @see RoozhFormatter#appendColon()
     */
    public RoozhFormatter appendSlash() {
        return appendCharacter('/');
    }

    /**
     * Append hyphen character
     *
     * @return this
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSpace()
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendColon()
     */
    public RoozhFormatter appendHyphen() {
        return appendCharacter('-');
    }

    /**
     * Append text
     *
     * @param t text
     * @return this
     * @see RoozhFormatter#appendSpace()
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendHyphen()
     * @see RoozhFormatter#appendColon()
     */
    public RoozhFormatter appendText(String t) {
        if (t == null) {
            throw new NullPointerException("Argument cannot be null.");
        }
        elements.add(t);
        return this;
    }

    /**
     * Append color character
     *
     * @return this
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSpace()
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendHyphen()
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
     * @see RoozhFormatter#appendHourOfDay(boolean) for 24-clock
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
     * Append 24-clock hour with/without leading zero
     *
     * @return this
     * @see RoozhFormatter#appendHour(boolean) for 12-clock
     */
    public RoozhFormatter appendHourOfDay(boolean leadingZero) {
        elements.add(new Hour(Hour.Clock.CLOCK_24).setMinimumLength(leadingZero ? 2 : 1));
        return this;
    }

    /**
     * Append month with leading zero
     *
     * @return this
     * @see RoozhFormatter#appendMonth()
     * @see RoozhFormatter#appendMonthFullName()
     * @see RoozhFormatter#appendMonthShortName()
     */
    public RoozhFormatter appendMonthLeadingZero() {
        elements.add(new Month().setMinimumLength(2));
        return this;
    }

    /**
     * Append month number with no leading zero
     *
     * @return this
     * @see RoozhFormatter#appendMonthLeadingZero()
     * @see RoozhFormatter#appendMonthFullName()
     * @see RoozhFormatter#appendMonthShortName()
     */
    public RoozhFormatter appendMonth() {
        elements.add(new Month().setMinimumLength(1));
        return this;
    }

    /**
     * Append month with short name
     *
     * @return this
     * @see RoozhFormatter#appendMonth()
     * @see RoozhFormatter#appendMonthLeadingZero()
     * @see RoozhFormatter#appendMonthFullName()
     */
    public RoozhFormatter appendMonthShortName() {
        elements.add(new Month().setMinimumLength(3));
        return this;
    }

    /**
     * Append month with full name
     *
     * @return this
     * @see RoozhFormatter#appendMonth()
     * @see RoozhFormatter#appendMonthLeadingZero()
     * @see RoozhFormatter#appendMonthShortName()
     */
    public RoozhFormatter appendMonthFullName() {
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
     * Build with current time
     * Note: Elements order is important.
     *
     * @return Formatted string
     * @see RoozhFormatter#build(Roozh)
     */
    public String build() {
        return build(new Roozh().gregorianToPersian());
    }

    /**
     * Build with provided Roozh
     *
     * @param roozh Roozh
     * @return Formatted string
     * @see RoozhFormatter#build()
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
                    return formatByLeadingZero(roozh.getMonth());
                } else if (component.getMinimumLength() == 3) {
                    return Roozh.Months.Short.getName(roozh.getMonth());
                }
                return Roozh.Months.getName(roozh.getMonth());
            case NUMBER:
                if (component instanceof Hour) {
                    if (((Hour) component).getClock() == Hour.Clock.CLOCK_12) {
                        if (component.getMinimumLength() == 1) {
                            return Integer.toString(roozh.getHour());
                        } else if (component.getMinimumLength() == 2) {
                            return formatByLeadingZero(roozh.getHour());
                        }
                    } else {
                        if (component.getMinimumLength() == 1) {
                            return Integer.toString(roozh.getHourOfDay());
                        } else if (component.getMinimumLength() == 2) {
                            return formatByLeadingZero(roozh.getHourOfDay());
                        }
                    }
                } else if (component instanceof Minute) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getMinute());
                    }
                    return formatByLeadingZero(roozh.getMinute());
                } else if (component instanceof Second) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getSecond());
                    }
                    return formatByLeadingZero(roozh.getSecond());
                } else if (component instanceof Millisecond) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getMillisecond());
                    }
                    return formatByLeadingZero(roozh.getMillisecond());
                } else if (component instanceof DayOfMonth) {
                    if (component.getMinimumLength() == 1) {
                        return Integer.toString(roozh.getDayOfMonth());
                    }
                    return formatByLeadingZero(roozh.getDayOfMonth());
                }
            case TEXT:
                // IMPLEMENT
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
     * Format by leading zero
     *
     * @param i Integer to be formatted
     * @return Formatted string
     */
    private String formatByLeadingZero(int i) {
        String sI = Integer.toString(i);
        if (sI.length() == 1) {
            return new StringBuilder(sI).insert(0, '0').toString();
        }
        return sI;
    }
}
