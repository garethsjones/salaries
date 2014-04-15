package com.brightpearl.salaries.input.args4j;

import com.brightpearl.salaries.input.InputParameters;
import org.joda.time.YearMonth;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.File;

public class Args4jCliParameters implements InputParameters {

    private File outputFile;
    private YearMonth startMonth = new YearMonth();

    public Args4jCliParameters() {
        CmdLineParser.registerHandler(YearMonth.class, Args4jYearMonthOptionHandler.class);
    }

    @Override
    public File getOutputFile() {
        return outputFile;
    }

    @Option(name = "-f", usage = "Set output file", required = true, aliases = {"--file"})
    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    @Override
    public YearMonth getStartMonth() {
        return startMonth;
    }

    @Option(name = "-m", usage = "Set start month of year", required = false, aliases = {"--month", "--yearMonth"}, handler = Args4jYearMonthOptionHandler.class)
    public void setStartMonth(YearMonth startMonth) {
        this.startMonth = startMonth;
    }
}
