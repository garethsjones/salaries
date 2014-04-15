package com.brightpearl.salaries;

import static com.brightpearl.salaries.main.Main.*;

import com.brightpearl.salaries.input.InputFetcher;
import com.brightpearl.salaries.input.InputParameters;
import com.brightpearl.salaries.logic.PayDaysOrchestrator;
import com.brightpearl.salaries.main.Main;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {
    
    private Main main;
    
    @Mock private InputFetcher inputFetcher;
    @Mock private PayDaysOrchestrator payDaysOrchestrator;
    @Mock private InputParameters inputParameters;

    private String[] args;
    private int status;
    
    @Before
    public void setUp() throws Exception {
        main = new Main(inputFetcher, payDaysOrchestrator);

        when(inputFetcher.fetchInputParameters(args)).thenReturn(inputParameters);
    }

    @Test
    public void mustReturnOkStatusOnSuccessfulCompletion() throws Exception {

        status = main.run(args);

        assertThat(status, is(STATUS_OK));
    }

    @Test
    public void mustReturnErrorStatusWhenInputFetchFails() throws Exception {

        when(inputFetcher.fetchInputParameters(args)).thenThrow(new RuntimeException("BANG!"));

        status = main.run(args);

        assertThat(status, is(STATUS_CMD_LINE_ARG_PARSE_FAILURE));
    }

    @Test
    public void mustReturnErrorStatusWhenOrchestratorFails() throws Exception {

        doThrow(new RuntimeException("BANG!")).when(payDaysOrchestrator).run(inputParameters);

        status = main.run(args);

        assertThat(status, is(STATUS_PAY_DATE_GENERATION_ERROR));
    }
}
