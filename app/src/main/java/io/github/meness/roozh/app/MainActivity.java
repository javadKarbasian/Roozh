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

package io.github.meness.roozh.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import io.github.meness.roozh.Roozh;
import io.github.meness.roozh.RoozhFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppCompatTextView textView = (AppCompatTextView) findViewById(R.id.outputTxt);
        AppCompatButton updateBtn = (AppCompatButton) findViewById(R.id.updateBtn);

        // build formatter with current time
        textView.setText(newFormatter());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // rebuild formatter with current time
                textView.setText(newFormatter());
            }
        });
    }

    private String newFormatter() {
        return new RoozhFormatter(Roozh.getInstance().gregorianToPersian())
                .appendDayOfMonth(false)
                .appendSpace()
                .appendMonthName()
                .appendSpace()
                .appendYear(false)
                .appendNewLine()
                .appendHour(true)
                .appendColon()
                .appendMinute(true)
                .appendColon()
                .appendSecond(true)
                .appendDot()
                .appendMillisecond()
                .appendSpace()
                .appendAmPm()
                .build();
    }
}
