package com.brightpearl.salaries.input;

import org.joda.time.YearMonth;

import java.io.File;

public interface InputParameters {

    File getOutputFile();

    YearMonth getStartMonth();
}
