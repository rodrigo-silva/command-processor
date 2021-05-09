package com.salesforce.command.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class NoActionCommandTest extends CommandBaseTest {
    private NoActionCommandImpl noActionCommandSpy;

    @Before
    public void setUp() throws IOException {
        noActionCommandSpy = spy(new NoActionCommandImpl(outputMock));
    }

    @Test
    public void testNoArguments() throws IOException {
        // when
        noActionCommandSpy.execute("", contextStatusSpy);

        // then
        verify(noActionCommandSpy).doExecute(eq(new String[0]), eq(this.contextStatusSpy));
        verifyZeroInteractions(this.contextStatusSpy);
        verifyZeroInteractions(outputMock);
    }

}
