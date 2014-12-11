package com.brightpearl.salaries.output.csv;

import static au.com.bytecode.opencsv.CSVWriter.*;

import au.com.bytecode.opencsv.CSVWriter;
import com.brightpearl.salaries.dto.MonthPayDays;
import com.brightpearl.salaries.output.PayDaysOutputStreamWriter;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

@Service
public class PayDaysCsvOutputStreamWriter implements PayDaysOutputStreamWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayDaysCsvOutputStreamWriter.class);

    private static final String[] HEADERS = {"Month", "Salary Payment Date", "Bonus Payment Date"};

    private static final DateTimeFormatter FORMATTER_MONTH = DateTimeFormat.forPattern("MMMM");
    private static final DateTimeFormatter FORMATTER_DAY = DateTimeFormat.forPattern("EEEE dd");

    @Override
    public void writeToStream(OutputStreamWriter outputStreamWriter, List<MonthPayDays> payDays) {

        try (CSVWriter writer = new CSVWriter(outputStreamWriter, DEFAULT_SEPARATOR, NO_QUOTE_CHARACTER, System.getProperty("line.separator"))) {
            writer.writeNext(HEADERS);
            LOGGER.info(Arrays.deepToString(HEADERS));

            if (CollectionUtils.isEmpty(payDays)) {
                return;
            }

            for (MonthPayDays monthPayDays : payDays) {
                String month = FORMATTER_MONTH.print(monthPayDays.getMonth());
                String salaryDay = FORMATTER_DAY.print(monthPayDays.getSalaryPayDay());
                String bonusDay = FORMATTER_DAY.print(monthPayDays.getBonusPayDay());

                String[] row = new String[]{month, salaryDay, bonusDay};
                writer.writeNext(row);
                LOGGER.info(Arrays.deepToString(row));
            }
        } catch (IOException e) {
            LOGGER.error("Error occurred while writing to file", e);
        }
    }
}
