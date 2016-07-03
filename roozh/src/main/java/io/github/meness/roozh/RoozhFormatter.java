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
import io.github.meness.roozh.components.Month;
import io.github.meness.roozh.components.Year;

/**
 * @since 2.0
 */
public class RoozhFormatter {
    private ArrayList<Object> elements = new ArrayList<>();

    public RoozhFormatter() {

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
     * Append space character
     *
     * @return this
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSlash()
     * @see RoozhFormatter#appendHyphen()
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
     */
    public RoozhFormatter appendHyphen() {
        return appendCharacter('-');
    }

    /**
     * Append text
     *
     * @param t text
     * @return this
     * @see RoozhFormatter#appendText(String)
     * @see RoozhFormatter#appendSpace()
     */
    public RoozhFormatter appendText(String t) {
        if (t == null) {
            throw new NullPointerException("Argument cannot be null.");
        }
        elements.add(t);
        return this;
    }

    /**
     * Append day of month with leading zero or not
     *
     * @return this
     */
    public RoozhFormatter appendDayOfMonth(boolean leadingZero) {
        elements.add(new DayOfMonth().setMinimumLength(leadingZero ? 2 : 1));
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

        StringBuilder stringBuilder = new StringBuilder();
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
                if (component.getMinimumLength() <= 1) {
                    return Integer.toString(roozh.getMonth());
                } else if (component.getMinimumLength() == 2) {
                    return formatByLeadingZero(roozh.getMonth());
                } else if (component.getMinimumLength() == 3) {
                    return Roozh.Months.Short.getName(roozh.getMonth());
                }
                return Roozh.Months.getName(roozh.getMonth());
            case NUMBER:
                if (component.getMinimumLength() <= 1) {
                    return Integer.toString(roozh.getDayOfMonth());
                }
                return formatByLeadingZero(roozh.getDayOfMonth());
            case TEXT:
                // IMPLEMENT
                break;
            case YEAR:
                if (component.getMinimumLength() <= 2) {
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
