package com.brightpearl.salaries.input.args4j;

import com.brightpearl.salaries.input.InputFetcher;
import com.brightpearl.salaries.input.InputParameters;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.springframework.stereotype.Service;

@Service
public class Args4jInputParameterFetcher implements InputFetcher {

    @Override
    public InputParameters fetchInputParameters(String[] args) {

        InputParameters inputParameters = new Args4jCliParameters();
        CmdLineParser cmdLineParser = new CmdLineParser(inputParameters);

        try {
            cmdLineParser.parseArgument(args);
        } catch (CmdLineException e) {
            cmdLineParser.printUsage(System.err);
            throw new RuntimeException("Unable to parse arguments");
        }

        return inputParameters;
    }
}
