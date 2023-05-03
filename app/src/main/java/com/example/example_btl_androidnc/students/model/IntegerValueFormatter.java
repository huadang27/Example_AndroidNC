package com.example.example_btl_androidnc.students.model;

import com.github.mikephil.charting.formatter.PercentFormatter;

public class IntegerValueFormatter extends PercentFormatter {
    @Override
    public String getFormattedValue(float value) {
        return String.valueOf(Math.round(value));
    }

}
