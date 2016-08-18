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
import io.github.meness.roozh.utils.FormatUtils;

/**
 * @since 2.6.1
 */
public class Hour extends AbstractComponent {
    private Clock clock;

    public Hour(Clock clock) {
        this.clock = clock;
    }

    public Clock getClock() {
        return clock;
    }

    @Override
    public Object process(Roozh roozh) {
        if (clock == Hour.Clock.CLOCK_12) {
            if (getMinimumLength() == 1) {
                return Integer.toString(roozh.getHour());
            }
            return FormatUtils.leadingZero(roozh.getHour());
        } else {
            if (getMinimumLength() == 1) {
                return Integer.toString(roozh.getHourOfDay());
            }
            return FormatUtils.leadingZero(roozh.getHourOfDay());
        }
    }

    public enum Clock {
        CLOCK_12, CLOCK_24
    }
}