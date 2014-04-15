package com.brightpearl.salaries.output;

import com.brightpearl.salaries.dto.MonthPayDays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collection;

@Service
public class PayDaysFileWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayDaysFileWriter.class);

    private final PayDaysOutputStreamWriter payDaysOutputStreamWriter;

    @Autowired
    public PayDaysFileWriter(PayDaysOutputStreamWriter payDaysOutputStreamWriter) {
        this.payDaysOutputStreamWriter = payDaysOutputStreamWriter;
    }

    public void writeToFile(File file, Collection<MonthPayDays> payDays) {

        try (OutputStreamWriter outputStream = new FileWriter(file)) {
            payDaysOutputStreamWriter.writeToStream(outputStream, payDays);
        } catch (IOException e) {
            LOGGER.error("Error occurred writing to file", e);
        }
    }
}
