package com.brightpearl.salaries.logic;

import com.brightpearl.salaries.dto.MonthPayDays;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MonthPayDaysGeneratorTest {

    private MonthPayDaysGenerator monthPayDaysGenerator;

    @Mock private SalaryPayDayCalculator salaryPayDayCalculator;
    @Mock private BonusPayDayCalculator bonusPayDayCalculator;

    private YearMonth yearMonth;
    private LocalDate salaryDateTime;
    private LocalDate bonusDateTime;

    private MonthPayDays output;

    @Before
    public void setUp() throws Exception {
        monthPayDaysGenerator = new MonthPayDaysGenerator(salaryPayDayCalculator, bonusPayDayCalculator);
    }

    @Test
    public void mustReturnIncomingYearMonthInReturnObject() throws Exception {
        yearMonth = new YearMonth(2010, 12);

        output = monthPayDaysGenerator.generate(yearMonth);

        assertNotNull(output);
        assertThat(output.getMonth(), is(yearMonth));
    }

    @Test
    public void mustReturnDateFromSalaryCalculatorInReturnObject() throws Exception {
        yearMonth = new YearMonth(2011, 1);
        salaryDateTime = new LocalDate(2011, 1, 1);
        when(salaryPayDayCalculator.calculate(yearMonth)).thenReturn(salaryDateTime);

        output = monthPayDaysGenerator.generate(yearMonth);

        assertNotNull(output);
        assertThat(output.getSalaryPayDay(), is(salaryDateTime));
        verify(salaryPayDayCalculator).calculate(yearMonth);
    }

    @Test
    public void mustReturnDateFromBonusCalculatorInReturnObject() throws Exception {
        yearMonth = new YearMonth(2012, 2);
        bonusDateTime = new LocalDate(2012, 2, 2);
        when(bonusPayDayCalculator.calculate(yearMonth)).thenReturn(bonusDateTime);

        output = monthPayDaysGenerator.generate(yearMonth);

        assertNotNull(output);
        assertThat(output.getBonusPayDay(), is(bonusDateTime));
        verify(bonusPayDayCalculator).calculate(yearMonth);
    }
}
