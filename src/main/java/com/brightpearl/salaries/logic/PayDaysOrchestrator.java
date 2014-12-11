package com.brightpearl.salaries.logic;

import com.brightpearl.salaries.dto.MonthPayDays;
import com.brightpearl.salaries.output.PayDaysFileWriter;
import com.brightpearl.salaries.input.InputParameters;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayDaysOrchestrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayDaysOrchestrator.class);

    private final YearPayDaysGenerator yearPayDaysGenerator;
    private final PayDaysFileWriter payDaysFileWriter;

    @Autowired
    public PayDaysOrchestrator(YearPayDaysGenerator yearPayDaysGenerator, PayDaysFileWriter payDaysFileWriter) {
        this.yearPayDaysGenerator = yearPayDaysGenerator;
        this.payDaysFileWriter = payDaysFileWriter;
    }

    public void run(InputParameters inputParameters) {

        //TODO validate incoming parameters

        LOGGER.info("Pay days file will be written to: {}", inputParameters.getOutputFile().getAbsolutePath());
        LOGGER.info("Pay days will start from: {}", ISODateTimeFormat.yearMonth().print(inputParameters.getStartMonth()));

        List<MonthPayDays> yearPayDates = yearPayDaysGenerator.generate(inputParameters.getStartMonth());

        payDaysFileWriter.writeToFile(inputParameters.getOutputFile(), yearPayDates);
    }
}
