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
public class BonusPayDayCalculator {

    private static final Logger LOGGER = LoggerFactory.getLogger(BonusPayDayCalculator.class);

    private static final int BONUS_MONTH_DAY = 15;
    private static final int ALTERNATIVE_DAY_IF_SUNDAY = 18;
    private static final int ALTERNATIVE_DAY_IF_SATURDAY = 19;

    public LocalDate calculate(YearMonth yearMonth) {
        Assert.notNull(yearMonth);

        LocalDate result = getBonusDayOfTheMonth(yearMonth);

        if (result.getDayOfWeek() == SUNDAY) {
            result = result.dayOfMonth().setCopy(ALTERNATIVE_DAY_IF_SUNDAY);
        } else if (result.getDayOfWeek() == SATURDAY) {
            result = result.dayOfMonth().setCopy(ALTERNATIVE_DAY_IF_SATURDAY);
        }

        LOGGER.debug("Bonus day for {} is {}",
                ISODateTimeFormat.yearMonth().print(yearMonth),
                ISODateTimeFormat.yearMonthDay().print(result));

        return result;
    }

    private LocalDate getBonusDayOfTheMonth(YearMonth yearMonth) {
        LocalDate result = new LocalDate(yearMonth.toDateTime(null));
        result = result.dayOfMonth().setCopy(BONUS_MONTH_DAY);
        return result;
    }
}
