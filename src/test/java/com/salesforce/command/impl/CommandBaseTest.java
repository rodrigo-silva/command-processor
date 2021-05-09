package com.salesforce.command.impl;
import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

/**
 * Base class to tests commands
 */
public class CommandBaseTest {
    protected static File workingDir;
    protected Output outputMock;
    protected ContextStatus contextStatusSpy;

    @BeforeClass
    public static void setUpClass() {
        workingDir = new File("src/test/resources");
    }

    @Before
    public void setUpBase() throws IOException {
        outputMock = mock(Output.class);
        contextStatusSpy = spy(new ContextStatus(workingDir.getCanonicalPath()));
    }
}
