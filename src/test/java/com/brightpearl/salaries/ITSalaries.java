package com.brightpearl.salaries;

import com.brightpearl.salaries.main.Main;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ITSalaries {

    private static final String FILE_NAME = "test.csv";

    private static final String EXPECTED_FILE_CONTENT = "" +
            "Month,Salary Payment Date,Bonus Payment Date" +
            "January,Friday 31,Wednesday 15" +
            "February,Friday 28,Wednesday 19" +
            "March,Monday 31,Wednesday 19" +
            "April,Wednesday 30,Tuesday 15" +
            "May,Friday 30,Thursday 15" +
            "June,Monday 30,Wednesday 18" +
            "July,Thursday 31,Tuesday 15" +
            "August,Friday 29,Friday 15" +
            "September,Tuesday 30,Monday 15" +
            "October,Friday 31,Wednesday 15" +
            "November,Friday 28,Wednesday 19" +
            "December,Wednesday 31,Monday 15";

    @Before
    public void setUp() throws Exception {

        File file = new File(FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void mustWriteToCSV() throws Exception {

        String[] args = {"-f", FILE_NAME, "-m", "2014-01"};

        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Main main = context.getBean(Main.class);
        main.run(args);

        StringBuilder actualFileContent = new StringBuilder();

        try (
            FileReader fileReader = new FileReader(FILE_NAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                actualFileContent.append(line);
            }
        }

        assertThat(actualFileContent.toString(), is(EXPECTED_FILE_CONTENT));
    }
}
