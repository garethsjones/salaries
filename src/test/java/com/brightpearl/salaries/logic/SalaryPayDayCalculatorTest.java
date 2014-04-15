package com.brightpearl.salaries.logic;

import static org.hamcrest.CoreMatchers.is;
import static org.joda.time.DateTimeConstants.*;
import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;

public class SalaryPayDayCalculatorTest {

    private SalaryPayDayCalculator calculator;

    private LocalDate output;

    @Before
    public void setUp() {
        calculator = new SalaryPayDayCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowExceptionWhenYearMonthIsNull() {
        calculator.calculate(null);
        fail("Exception should have been thrown since yearMonth is null");
    }

    @Test
    public void mustReturnLastDayOfMonthWhenMonthEndsOnAWeekday() {
        YearMonth input = new YearMonth(2011, NOVEMBER);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2011));
        assertThat(output.getMonthOfYear(), is(NOVEMBER));
        assertThat(output.getDayOfMonth(), is(30));
    }

    @Test
    public void mustReturnLastFridayWhenMonthEndsOnASaturday() {
        YearMonth input = new YearMonth(2013, AUGUST);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2013));
        assertThat(output.getMonthOfYear(), is(AUGUST));
        assertThat(output.getDayOfMonth(), is(30));
    }

    @Test
    public void mustReturnLastFridayWhenMonthEndsOnASunday() {
        YearMonth input = new YearMonth(2012, SEPTEMBER);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2012));
        assertThat(output.getMonthOfYear(), is(SEPTEMBER));
        assertThat(output.getDayOfMonth(), is(28));
    }
}
