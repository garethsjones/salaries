package com.brightpearl.salaries.main;

import com.brightpearl.salaries.input.InputFetcher;
import com.brightpearl.salaries.input.InputParameters;
import com.brightpearl.salaries.logic.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static final int STATUS_OK = 0;
    public static final int STATUS_CMD_LINE_ARG_PARSE_FAILURE = 1;
    public static final int STATUS_PAY_DATE_GENERATION_ERROR = 2;

    private final InputFetcher inputFetcher;
    private final PayDaysOrchestrator payDaysOrchestrator;

    @Autowired
    public Main(InputFetcher inputFetcher, PayDaysOrchestrator payDaysOrchestrator) {
        this.inputFetcher = inputFetcher;
        this.payDaysOrchestrator = payDaysOrchestrator;
    }

    public int run(String[] args) {

        InputParameters inputParameters;

        try {
            inputParameters = inputFetcher.fetchInputParameters(args);
        } catch (Exception e) {
            LOGGER.error("An error occurred while parsing arguments", e);
            return STATUS_CMD_LINE_ARG_PARSE_FAILURE;
        }

        try {
            payDaysOrchestrator.run(inputParameters);
        } catch (Exception e) {
            LOGGER.error("An error occurred while generating payments file", e);
            return STATUS_PAY_DATE_GENERATION_ERROR;
        }

        return STATUS_OK;
    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Main main = context.getBean(Main.class);
        int status = main.run(args);
        System.exit(status);
    }
}
