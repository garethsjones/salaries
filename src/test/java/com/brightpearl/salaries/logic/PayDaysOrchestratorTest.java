package com.brightpearl.salaries.logic;

import com.brightpearl.salaries.dto.MonthPayDays;
import com.brightpearl.salaries.input.InputParameters;
import com.brightpearl.salaries.output.PayDaysFileWriter;
import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.util.Collection;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PayDaysOrchestratorTest {

    private PayDaysOrchestrator payDaysOrchestrator;

    @Mock private YearPayDaysGenerator yearPayDaysGenerator;
    @Mock private PayDaysFileWriter payDaysFileWriter;

    @Mock private InputParameters inputParameters;
    private YearMonth startMonth;
    @Mock private File outputFile;
    @Mock private Collection<MonthPayDays> yearPayDays;

    @Before
    public void setUp() throws Exception {
        payDaysOrchestrator = new PayDaysOrchestrator(yearPayDaysGenerator, payDaysFileWriter);

        startMonth = new YearMonth();
        when(inputParameters.getStartMonth()).thenReturn(startMonth);
        when(inputParameters.getOutputFile()).thenReturn(outputFile);
        when(yearPayDaysGenerator.generate(startMonth)).thenReturn(yearPayDays);
        when(outputFile.getAbsolutePath()).thenReturn("path/to/file.csv");
    }

    @Test
    public void mustPassInputStartMonthToGenerator() throws Exception {

        payDaysOrchestrator.run(inputParameters);

        verify(yearPayDaysGenerator).generate(startMonth);
    }

    @Test
    public void mustPassGeneratedYearPayDaysToWriter() throws Exception {

        payDaysOrchestrator.run(inputParameters);

        verify(payDaysFileWriter).writeToFile(outputFile, yearPayDays);
    }
}
