package com.salesforce.command.impl;

import com.salesforce.command.UnrecognizedCommand;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class UnrecognizedCommandTest extends CommandBaseTest {
    private UnrecognizedCommand unrecognizedCommand;

    @Before
    public void setUp() throws IOException {
        unrecognizedCommand = spy(new UnrecognizedCommandImpl(outputMock));
    }

    @Test
    public void testNoArguments() throws IOException {
        // when
        unrecognizedCommand.execute("", contextStatusSpy);

        // then
        verify(outputMock).println(eq("Unrecognized command!"));
    }

    @Test
    public void testWithArguments() throws IOException {
        // when
        unrecognizedCommand.execute("-foo", contextStatusSpy);

        // then
        verify(outputMock).println(eq("Unrecognized command!"));
    }
}
