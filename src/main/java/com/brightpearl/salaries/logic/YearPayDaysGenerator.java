package com.brightpearl.salaries.logic;

import com.brightpearl.salaries.dto.MonthPayDays;
import org.joda.time.YearMonth;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class YearPayDaysGenerator {

    private static final Logger LOGGER = LoggerFactory.getLogger(YearPayDaysGenerator.class);

    private final MonthPayDaysGenerator monthPayDaysGenerator;

    @Autowired
    public YearPayDaysGenerator(MonthPayDaysGenerator monthPayDaysGenerator) {
        this.monthPayDaysGenerator = monthPayDaysGenerator;
    }

    public Collection<MonthPayDays> generate(YearMonth startMonth) {

        YearMonth month = new YearMonth(startMonth);
        List<MonthPayDays> paydayRows = new ArrayList<>();

        while (month.getYear() == startMonth.getYear()) {
            LOGGER.debug("Calculating payment dates for {}", ISODateTimeFormat.yearMonth().print(month));
            MonthPayDays monthPayDays = monthPayDaysGenerator.generate(month);

            paydayRows.add(monthPayDays);
            month = month.plusMonths(1);
        }

        return paydayRows;
    }
}
