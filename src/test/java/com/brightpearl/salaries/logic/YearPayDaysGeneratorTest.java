package com.brightpearl.salaries.logic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.joda.time.DateTimeConstants.*;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import com.brightpearl.salaries.dto.MonthPayDays;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class YearPayDaysGeneratorTest {

    private YearPayDaysGenerator yearPayDaysGenerator;

    @Mock private MonthPayDaysGenerator monthPayDaysGenerator;

    private static final int YEAR = 2015;
    private Map<YearMonth, MonthPayDays> monthPayDaysGeneratorResults;
    private Collection<MonthPayDays> output;

    @Before
    public void setUp() throws Exception {
        yearPayDaysGenerator = new YearPayDaysGenerator(monthPayDaysGenerator);

        monthPayDaysGeneratorResults = new HashMap<>();
        for (int i = JANUARY; i <= DECEMBER; i++) {
            YearMonth key = new YearMonth(YEAR, i);
            MonthPayDays value = new MonthPayDays(key, null, null);
            monthPayDaysGeneratorResults.put(key, value);
            when(monthPayDaysGenerator.generate(key)).thenReturn(value);
        }
    }

    @Test
    public void mustReturnAllPayDaysFromJanToDecWhenStartMonthIsJan() throws Exception {
        YearMonth startMonth = new YearMonth(YEAR, JANUARY);

        output = yearPayDaysGenerator.generate(startMonth);

        assertNotNull(output);
        assertThat(output.size(), is(12));
        Iterator<MonthPayDays> outputIterator = output.iterator();

        for (int i = JANUARY; i <= DECEMBER; i++) {
            YearMonth key = new YearMonth(YEAR, i);
            MonthPayDays expected = monthPayDaysGeneratorResults.get(key);
            MonthPayDays actual = outputIterator.next();

            assertThat(actual, is(expected));
        }
    }

    @Test
    public void mustReturnAllPayDaysFromNovToDecWhenStartMonthIsNov() throws Exception {
        YearMonth startMonth = new YearMonth(YEAR, NOVEMBER);

        output = yearPayDaysGenerator.generate(startMonth);

        assertNotNull(output);
        assertThat(output.size(), is(2));
        Iterator<MonthPayDays> outputIterator = output.iterator();

        for (int i = NOVEMBER; i <= DECEMBER; i++) {
            YearMonth key = new YearMonth(YEAR, i);
            MonthPayDays expected = monthPayDaysGeneratorResults.get(key);
            MonthPayDays actual = outputIterator.next();

            assertThat(actual, is(expected));
        }
    }
}
