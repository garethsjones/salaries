package com.brightpearl.salaries.input.args4j;

import static org.joda.time.DateTimeConstants.*;

import org.joda.time.YearMonth;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.OptionDef;
import org.kohsuke.args4j.spi.Setter;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class Args4jYearMonthOptionHandlerTest {

    private Args4jYearMonthOptionHandler args4jYearMonthOptionHandler;

    @Mock private CmdLineParser cmdLineParser;
    @Mock private OptionDef optionDef;
    @Mock private Setter setter;

    @Before
    public void setUp() throws Exception {
        args4jYearMonthOptionHandler = new Args4jYearMonthOptionHandler(cmdLineParser, optionDef, setter);
    }

    @Test
     public void mustReturnYearMonthWhenParamIsParsable() throws Exception {

        YearMonth result = args4jYearMonthOptionHandler.parse("2014-03");

        assertNotNull(result);
        assertThat(result.getYear(), is(2014));
        assertThat(result.getMonthOfYear(), is(MARCH));
    }

    @Test (expected = CmdLineException.class)
    public void mustThrowCmdLineExceptionWhenParamIsNotParseable() throws Exception {

        args4jYearMonthOptionHandler.parse("wibble");
    }

    @Test
    public void mustReturnDateFormatAsDefaultMetaVariable() throws Exception {

        assertThat(args4jYearMonthOptionHandler.getDefaultMetaVariable(), is("yyyy-MM"));
    }
}
