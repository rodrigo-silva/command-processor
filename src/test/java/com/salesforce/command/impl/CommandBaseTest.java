package com.salesforce.command.impl;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;

public class CommandBaseTest {
    private final static String TEMP_FOLDER = "COMMAND_PROCESSOR_TEST";
    protected static File workingDir;

    @BeforeClass
    public static void setUpClass() {
        workingDir = new File("src/test/resources");
    }
}
