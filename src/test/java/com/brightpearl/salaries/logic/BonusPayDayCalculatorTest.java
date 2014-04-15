package com.brightpearl.salaries.logic;

import static org.joda.time.DateTimeConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;

public class BonusPayDayCalculatorTest {

    private BonusPayDayCalculator calculator;

    private LocalDate output;

    @Before
    public void setUp() {
        calculator = new BonusPayDayCalculator();
    }

    @Test(expected = IllegalArgumentException.class)
    public void mustThrowExceptionWhenYearMonthIsNull() {
        calculator.calculate(null);
        fail("Exception should have been thrown since YearMonth is null");
    }

    @Test
    public void mustReturnFifteenthWhenFifteenthIsAWeekday() {
        YearMonth input = new YearMonth(2013, FEBRUARY);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2013));
        assertThat(output.getMonthOfYear(), is(FEBRUARY));
        assertThat(output.getDayOfMonth(), is(15));
    }

    @Test
    public void mustReturnFollowingWednesdayWhenFifteenthIsASaturday() {
        YearMonth input = new YearMonth(2012, SEPTEMBER);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2012));
        assertThat(output.getMonthOfYear(), is(SEPTEMBER));
        assertThat(output.getDayOfMonth(), is(19));
    }

    @Test
    public void mustReturnFollowingWednesdayWhenFifteenthIsASunday() {
        YearMonth input = new YearMonth(2012, JULY);

        output = calculator.calculate(input);

        assertNotNull(output);
        assertThat(output.getYear(), is(2012));
        assertThat(output.getMonthOfYear(), is(JULY));
        assertThat(output.getDayOfMonth(), is(18));
    }
}
