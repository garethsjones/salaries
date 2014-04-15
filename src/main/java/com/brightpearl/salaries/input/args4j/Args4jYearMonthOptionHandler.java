package com.brightpearl.salaries.input.args4j;

import org.joda.time.YearMonth;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.OneArgumentOptionHandler;
import org.kohsuke.args4j.spi.Setter;

public class Args4jYearMonthOptionHandler extends OneArgumentOptionHandler<YearMonth> {

    private static final String DATE_FORMAT = "yyyy-MM";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = ISODateTimeFormat.yearMonth();

    public Args4jYearMonthOptionHandler(CmdLineParser parser, OptionDef option, Setter<? super YearMonth> setter) {
        super(parser, option, setter);
    }

    @Override
    protected YearMonth parse(String argument) throws CmdLineException {

        YearMonth yearMonth;

        try {
            yearMonth = YearMonth.parse(argument, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            throw new CmdLineException(owner, String.format("Unable to parse month '%s' use format '%s'", argument, DATE_FORMAT), e);
        }

        return yearMonth;
    }

    @Override
    public String getDefaultMetaVariable() {
        return DATE_FORMAT;
    }
}
