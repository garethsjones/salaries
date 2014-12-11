package com.brightpearl.salaries.output;

import com.brightpearl.salaries.dto.MonthPayDays;

import java.io.OutputStreamWriter;
import java.util.List;

public interface PayDaysOutputStreamWriter {
    void writeToStream(OutputStreamWriter outputStreamWriter, List<MonthPayDays> payDays);
}
