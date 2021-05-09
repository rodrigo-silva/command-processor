package com.salesforce.command.impl;

import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static  org.mockito.Mockito.*;

public class ListFilesCommandTest extends CommandBaseTest{
    private Output outputMock;
    private ListFilesCommand listFilesCommandSpy;
    private ContextStatus contextStatusSpy;

    @Before
    public void setUp() throws IOException {
        outputMock = mock(Output.class);
        contextStatusSpy = spy(new ContextStatus(workingDir.getCanonicalPath()));
        listFilesCommandSpy = spy(new ListFilesCommand(outputMock));
    }

    @Test
    public void testListFilesNoArguments() throws IOException {
        // when
        listFilesCommandSpy.execute("", this.contextStatusSpy);

        // then
        verify(listFilesCommandSpy).doExecute(eq(new String[0]), eq(this.contextStatusSpy));
        verify(contextStatusSpy, times(0)).setCurrentPath(anyString());
        verify(outputMock, times(5)).println(anyString());
        verify(outputMock).println("emptyFolder");
        verify(outputMock).println("folderA");
        verify(outputMock).println("folderB");
        verify(outputMock).println("rootFile1.txt");
        verify(outputMock).println("rootFile2.txt");
    }

    @Test
    public void testListFilesRecursiveArgument() throws IOException {
        // when
        listFilesCommandSpy.execute("-r", this.contextStatusSpy);

        // then
        verify(listFilesCommandSpy).doExecute(eq(new String[]{"-r"}), eq(this.contextStatusSpy));
        verify(contextStatusSpy, times(0)).setCurrentPath(anyString());
        verify(outputMock, times(11)).println(anyString());
        verify(outputMock).println(this.contextStatusSpy.getCurrentPath());
        verify(outputMock).println("\\emptyFolder");
        verify(outputMock).println("\\folderA");
        verify(outputMock).println("\\folderA\\subfolderA");
        verify(outputMock).println("\\folderA\\subfolderA\\subfileA.txt");
        verify(outputMock).println("\\folderA\\fileA1.txt");
        verify(outputMock).println("\\folderA\\fileA2.txt");
        verify(outputMock).println("\\folderB");
        verify(outputMock).println("\\folderB\\fileB.txt");
        verify(outputMock).println("\\rootFile1.txt");
        verify(outputMock).println("\\rootFile2.txt");
    }

    @Test
    public void testListInvalidArguments() throws IOException {
        // when
        listFilesCommandSpy.execute("-notValid", this.contextStatusSpy);

        // then
        verify(listFilesCommandSpy, never()).doExecute(eq(new String[]{"-notValid"}), eq(this.contextStatusSpy));
        verify(outputMock).println(listFilesCommandSpy.getHelpText());
    }
}
