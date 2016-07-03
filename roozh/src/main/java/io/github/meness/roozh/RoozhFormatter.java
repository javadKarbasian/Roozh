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
     * Append space
     *
     * @return this
     * @see RoozhFormatter#appendCharacter(char)
     * @see RoozhFormatter#appendText(String)
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
     */
    public RoozhFormatter appendCharacter(char c) {
        elements.add(c);
        return this;
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
     * Append day of month with minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendDayOfMonth()
     */
    public RoozhFormatter appendDayOfMonth(int minDigits) {
        elements.add(new DayOfMonth().setMinimumLength(minDigits));
        return this;
    }

    /**
     * Append day of month with default minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendDayOfMonth(int)
     */
    public RoozhFormatter appendDayOfMonth() {
        elements.add(new DayOfMonth());
        return this;
    }

    /**
     * Append month with minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendMonth()
     */
    public RoozhFormatter appendMonth(int minDigits) {
        elements.add(new Month().setMinimumLength(minDigits));
        return this;
    }

    /**
     * Append month with default minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendMonth(int)
     */
    public RoozhFormatter appendMonth() {
        elements.add(new Month());
        return this;
    }

    /**
     * Append year with minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendYear()
     */
    public RoozhFormatter appendYear(int minDigits) {
        elements.add(new Year().setMinimumLength(minDigits));
        return this;
    }

    /**
     * Append year with default minimum digits
     *
     * @return this
     * @see RoozhFormatter#appendYear(int)
     */
    public RoozhFormatter appendYear() {
        elements.add(new Year());
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
                return Roozh.Months.getPersianName(roozh.getMonth());
            case NUMBER:
                return Integer.toString(roozh.getDayOfMonth());
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
}
