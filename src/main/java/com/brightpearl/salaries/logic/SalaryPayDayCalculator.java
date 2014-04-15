package com.brightpearl.salaries.logic;

import static org.joda.time.DateTimeConstants.*;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SalaryPayDayCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SalaryPayDayCalculator.class);

    private static final int OFFSET_IF_SUNDAY = 2;
    private static final int OFFSET_IF_SATURDAY = 1;

    public LocalDate calculate(YearMonth yearMonth) {
        Assert.notNull(yearMonth);

        LocalDate result = getLastDayOfMonth(yearMonth);

        if (result.getDayOfWeek() == SUNDAY) {
            result = result.minusDays(OFFSET_IF_SUNDAY);
        } else if (result.getDayOfWeek() == SATURDAY) {
            result = result.minusDays(OFFSET_IF_SATURDAY);
        }

        LOGGER.debug("Salary day for {} is {}",
                ISODateTimeFormat.yearMonth().print(yearMonth),
                ISODateTimeFormat.yearMonthDay().print(result));

        return result;
    }

    private LocalDate getLastDayOfMonth(YearMonth yearMonth) {
        LocalDate result = new LocalDate(yearMonth.toDateTime(null));
        result = result.dayOfMonth().withMaximumValue();

        return result;
    }
}
