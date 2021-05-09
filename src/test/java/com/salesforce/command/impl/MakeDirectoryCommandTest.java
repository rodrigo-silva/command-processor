package com.salesforce.command.impl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class MakeDirectoryCommandTest extends CommandBaseTest {
    private MakeDirectoryCommandImpl makeDirectoryCommandSpy;
    private String dirName = "temp";

    @Before
    public void setUp() {
        makeDirectoryCommandSpy = spy(new MakeDirectoryCommandImpl(outputMock));
    }
    @After
    public void cleanUp() throws IOException {
        File createdDir = new File(workingDir.getCanonicalPath() + File.separator + dirName);
        createdDir.delete();
    }

    @Test
    public void testMakeDir() throws IOException {
        // when
        makeDirectoryCommandSpy.execute(dirName, contextStatusSpy);

        // then
        File createdDir = new File(workingDir.getCanonicalPath() + File.separator + dirName);
        assertTrue("Should have created " + dirName, createdDir.exists());
    }

    @Test
    public void testMakeExistingDir() throws IOException {
        // when
        makeDirectoryCommandSpy.execute("folderA", contextStatusSpy);

        // then
        verify(outputMock).println(eq("Directory already exists"));
    }

    @Test
    public void testNoArguments() throws IOException {
        // when
        makeDirectoryCommandSpy.execute("", contextStatusSpy);

        // then
        verify(outputMock).println(eq(makeDirectoryCommandSpy.getHelpText()));
        verify(makeDirectoryCommandSpy, never()).doExecute(eq(new String[]{""}), eq(contextStatusSpy));
    }

    @Test
    public void testTwoArguments() throws IOException {
        // when
        makeDirectoryCommandSpy.execute("foo bar", contextStatusSpy);

        // then
        verify(outputMock).println(eq(makeDirectoryCommandSpy.getHelpText()));
        verify(makeDirectoryCommandSpy, never()).doExecute(eq(new String[]{""}), eq(contextStatusSpy));
    }

}
