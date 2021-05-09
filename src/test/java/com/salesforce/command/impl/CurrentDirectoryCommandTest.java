package com.salesforce.command.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static  org.mockito.Mockito.*;

public class CurrentDirectoryCommandTest extends CommandBaseTest {

    private CurrentDirectoryCommandImpl currentDirectoryCommandSpy;

    @Before
    public void setUp() {
        currentDirectoryCommandSpy = spy(new CurrentDirectoryCommandImpl(outputMock));
    }

    @Test
    public void testNoArguments() throws IOException {
        // when
        currentDirectoryCommandSpy.execute("", contextStatusSpy);

        // then
        verify(outputMock).println(eq(contextStatusSpy.getCurrentPath()));
    }

    @Test
    public void testWithAnyArguments() throws IOException {
        // when
        currentDirectoryCommandSpy.execute("foo", contextStatusSpy);

        // then
        verify(outputMock, never()).println(eq(contextStatusSpy.getCurrentPath()));
        verify(outputMock).println(eq(currentDirectoryCommandSpy.getHelpText()));
    }
}
