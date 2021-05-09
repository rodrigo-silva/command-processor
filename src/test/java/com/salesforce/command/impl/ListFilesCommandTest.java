package com.salesforce.command.impl;

import com.salesforce.command.ContextStatus;
import com.salesforce.output.DefaultSystemOut;
import com.salesforce.output.Output;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

public class ListFilesCommandTest extends CommandBaseTest{
    private Output outputMock;
    private ListFilesCommand listFilesCommandSpy;
    private ContextStatus contextStatusSpy;

    @Before
    public void setUp() throws IOException {
        this.outputMock = Mockito.mock(Output.class);
        this.contextStatusSpy = Mockito.spy(new ContextStatus(workingDir.getCanonicalPath()));
        this.listFilesCommandSpy = Mockito.spy(new ListFilesCommand(outputMock));
    }

    @Test
    public void testListFilesNoArguments() throws IOException {
        // when
        listFilesCommandSpy.execute("-", this.contextStatusSpy);

        // then

        Mockito.verify(listFilesCommandSpy).doExecute(Mockito.eq(new String[0]), Mockito.eq(this.contextStatusSpy));
    }
}
