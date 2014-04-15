package com.brightpearl.salaries.output;

import com.brightpearl.salaries.dto.MonthPayDays;

import java.io.OutputStreamWriter;
import java.util.Collection;

public interface PayDaysOutputStreamWriter {
    void writeToStream(OutputStreamWriter outputStreamWriter, Collection<MonthPayDays> payDays);
}
