package com.brightpearl.salaries.dto;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

public class MonthPayDaysBuilder {

    private YearMonth month;
    private LocalDate salaryPayDay;
    private LocalDate bonusPayDay;

    public MonthPayDaysBuilder setMonth(YearMonth month) {
        this.month = month;
        return this;
    }

    public MonthPayDaysBuilder setSalaryPayDay(LocalDate salaryPayDay) {
        this.salaryPayDay = salaryPayDay;
        return this;
    }

    public MonthPayDaysBuilder setBonusPayDay(LocalDate bonusPayDay) {
        this.bonusPayDay = bonusPayDay;
        return this;
    }

    public MonthPayDays build() {
        return new MonthPayDays(month, salaryPayDay, bonusPayDay);
    }
}
