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

import io.github.meness.roozh.Roozh;

/**
 * @since 2.0
 */
public abstract class AbstractComponent {
    private static int MAX_DIGITS = 4;
    protected Roozh roozh;
    private int minDigits = 1;

    public AbstractComponent() {

    }

    public AbstractComponent(int minDigits) {
        this.minDigits = minDigits;
    }

    public abstract Object process(Roozh roozh);

    public int getMinimumLength() {
        return minDigits;
    }

    public AbstractComponent setMinimumLength(int i) {
        // i is between min digits and max digits
        minDigits = i < minDigits ? minDigits : i > MAX_DIGITS ? MAX_DIGITS : i;
        return this;
    }
}
