package com.brightpearl.salaries.output.csv;

import static org.joda.time.DateTimeConstants.*;

import com.brightpearl.salaries.dto.MonthPayDays;
import org.joda.time.LocalDate;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PayDaysCsvOutputStreamWriterTest {

    private PayDaysCsvOutputStreamWriter payDaysCsvOutputStreamWriter;

    private ByteArrayOutputStream byteArrayOutputStream;
    private OutputStreamWriter outputStreamWriter;

    private static final int YEAR = 2014;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Before
    public void setUp() throws Exception {
        payDaysCsvOutputStreamWriter = new PayDaysCsvOutputStreamWriter();

        byteArrayOutputStream = new ByteArrayOutputStream();
        outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
    }

    @Test
    public void mustWriteHeadersToOutputStreamInCsvFormat() throws Exception {

        payDaysCsvOutputStreamWriter.writeToStream(outputStreamWriter, null);

        String output = byteArrayOutputStream.toString();
        assertThat(output, is("Month,Salary Payment Date,Bonus Payment Date" + LINE_SEPARATOR));
    }

    @Test
    public void mustWriteEachPayDayRowToOutputStreamInCsvFormat() throws Exception {

        List<MonthPayDays> yearPayDays = new ArrayList<>();
        yearPayDays.add(new MonthPayDays(new YearMonth(YEAR, NOVEMBER), new LocalDate(YEAR, NOVEMBER, 28), new LocalDate(YEAR, NOVEMBER, 19)));
        yearPayDays.add(new MonthPayDays(new YearMonth(YEAR, DECEMBER), new LocalDate(YEAR, DECEMBER, 31), new LocalDate(YEAR, DECEMBER, 15)));

        payDaysCsvOutputStreamWriter.writeToStream(outputStreamWriter, yearPayDays);

        String output = byteArrayOutputStream.toString();
        String[] lines = output.split(LINE_SEPARATOR);
        assertThat(lines.length, is(3));
        assertThat(lines[1], is("November,Friday 28,Wednesday 19"));
        assertThat(lines[2], is("December,Wednesday 31,Monday 15"));
    }
}
