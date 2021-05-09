package com.salesforce.command.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CreateFileCommandTest extends CommandBaseTest {
    private CreateFileCommandImpl createFileCommandSpy;
    private String tempFileName = "temp.txt";

    @Before
    public void setUp() {
        createFileCommandSpy = spy(new CreateFileCommandImpl(outputMock));
    }
    @After
    public void cleanUp() throws IOException {
        File tempFile = new File(workingDir.getCanonicalPath() + File.separator + tempFileName);
        tempFile.delete();
    }

    @Test
    public void testCreateFile() throws IOException {
        // when
        createFileCommandSpy.execute(tempFileName, contextStatusSpy);

        // then
        File newFile = new File(workingDir.getCanonicalPath() + File.separator + tempFileName);
        assertTrue("Should have created " + tempFileName, newFile.exists());
    }

    @Test
    public void testMakeExistingDir() throws IOException {
        // when
        createFileCommandSpy.execute("rootFile1.txt", contextStatusSpy);

        // then
        verify(outputMock).println(eq("File already exists"));
    }

    @Test
    public void testNoArguments() throws IOException {
        // when
        createFileCommandSpy.execute("", contextStatusSpy);

        // then
        verify(outputMock).println(eq(createFileCommandSpy.getHelpText()));
        verify(createFileCommandSpy, never()).doExecute(eq(new String[]{""}), eq(contextStatusSpy));
    }

    @Test
    public void testTwoArguments() throws IOException {
        // when
        createFileCommandSpy.execute("foo bar", contextStatusSpy);

        // then
        verify(outputMock).println(eq(createFileCommandSpy.getHelpText()));
        verify(createFileCommandSpy, never()).doExecute(eq(new String[]{""}), eq(contextStatusSpy));
    }

}
