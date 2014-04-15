package com.brightpearl.salaries.dto;

import org.joda.time.LocalDate;
import org.joda.time.YearMonth;

public class MonthPayDays {

    private final YearMonth month;
    private final LocalDate salaryPayDay;
    private final LocalDate bonusPayDay;

    public MonthPayDays(YearMonth month, LocalDate salaryPayDay, LocalDate bonusPayDay) {
        this.month = month;
        this.salaryPayDay = salaryPayDay;
        this.bonusPayDay = bonusPayDay;
    }

    public final YearMonth getMonth() {
        return month;
    }

    public final LocalDate getSalaryPayDay() {
        return salaryPayDay;
    }

    public final LocalDate getBonusPayDay() {
        return bonusPayDay;
    }
}
