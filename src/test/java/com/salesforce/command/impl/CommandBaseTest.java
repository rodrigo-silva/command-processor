package com.salesforce.command.impl;
import org.junit.BeforeClass;

import java.io.File;

/**
 * Base class to tests commands
 */
public class CommandBaseTest {
    protected static File workingDir;

    @BeforeClass
    public static void setUpClass() {
        workingDir = new File("src/test/resources");
    }
}
