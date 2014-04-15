package com.brightpearl.salaries.logic;

import com.brightpearl.salaries.dto.MonthPayDays;
import com.brightpearl.salaries.dto.MonthPayDaysBuilder;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MonthPayDaysGenerator {

    private final SalaryPayDayCalculator salaryPayDayCalculator;
    private final BonusPayDayCalculator bonusPayDayCalculator;

    @Autowired
    public MonthPayDaysGenerator(SalaryPayDayCalculator salaryPayDayCalculator,
                                 BonusPayDayCalculator bonusPayDayCalculator) {
        this.salaryPayDayCalculator = salaryPayDayCalculator;
        this.bonusPayDayCalculator = bonusPayDayCalculator;
    }

    public MonthPayDays generate(YearMonth yearMonth) {

        return new MonthPayDaysBuilder().setMonth(yearMonth)
                .setSalaryPayDay(salaryPayDayCalculator.calculate(yearMonth))
                .setBonusPayDay(bonusPayDayCalculator.calculate(yearMonth))
                .build();
    }
}
