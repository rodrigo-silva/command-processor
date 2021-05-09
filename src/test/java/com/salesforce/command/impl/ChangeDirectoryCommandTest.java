package com.salesforce.command.impl;


import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ChangeDirectoryCommandTest extends CommandBaseTest {

    private ChangeDirectoryCommandImpl changeDirectoryCommand;

    @Before
    public void setUp() {
        changeDirectoryCommand = spy(new ChangeDirectoryCommandImpl(outputMock));
    }

    @Test
    public void testChangeExistingDir() throws IOException {
        // when
        changeDirectoryCommand.execute("folderA", contextStatusSpy);

        // then
        assertEquals(workingDir.getCanonicalPath() + File.separator + "folderA", contextStatusSpy.getCurrentPath());
    }

    @Test
    public void testChangeNonExistingDir() throws IOException {
        // when
        changeDirectoryCommand.execute("folderX", contextStatusSpy);

        // then
        assertEquals(workingDir.getCanonicalPath(), contextStatusSpy.getCurrentPath());
        verify(outputMock).println(eq("Directory not found"));
    }

    @Test
    public void testChangeFromWindowsRootDir() throws IOException {
        // given
        contextStatusSpy.setCurrentPath("C:\\");

        // when
        changeDirectoryCommand.execute("..", contextStatusSpy);

        // then
        assertEquals("C:\\", contextStatusSpy.getCurrentPath());
    }

    @Test
    public void testChangeFromLinuxRootDir() throws IOException {
        // given
        contextStatusSpy.setCurrentPath("/");

        // when
        changeDirectoryCommand.execute("..", contextStatusSpy);

        // then
        assertEquals("/", contextStatusSpy.getCurrentPath());
    }

    @Test
    public void testChangeExistingDirNoArguments() throws IOException {
        // when
        changeDirectoryCommand.execute("", contextStatusSpy);
        verify(changeDirectoryCommand, never()).doExecute(eq(new String[]{""}), eq(this.contextStatusSpy));
        verify(outputMock).println(changeDirectoryCommand.getHelpText());
    }

    @Test
    public void testChangeParentExistingDir() throws IOException {
        // given
        String parentPath = contextStatusSpy.getCurrentPath();
        contextStatusSpy.setCurrentPath(contextStatusSpy.getCurrentPath() + File.separator + "folderA");

        // when
        changeDirectoryCommand.execute("..", contextStatusSpy);

        // then
        assertEquals(parentPath, contextStatusSpy.getCurrentPath());
    }

}
