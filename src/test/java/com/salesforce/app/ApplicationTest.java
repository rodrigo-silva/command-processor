package com.salesforce.app;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.salesforce.command.*;
import com.salesforce.command.factory.CommandFactory;
import com.salesforce.input.Input;
import com.salesforce.output.Output;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationTest {

    @Mock
    ChangeDirectoryCommand changeDirectoryCommand;
    @Mock
    CreateFileCommand createFileCommand;
    @Mock
    CurrentDirectoryCommand currentDirectoryCommand;
    @Mock
    ListFilesCommand listFilesCommand;
    @Mock
    MakeDirectoryCommand makeDirectoryCommand;
    @Mock
    NoActionCommand noActionCommand;
    @Mock
    UnrecognizedCommand unrecognizedCommand;
    @Mock
    CommandFactory commandFactory;
    @Mock
    Output output;
    @Mock
    Input input;

    @Before
    public void setUpFactory() {
        when(commandFactory.getCommandFromName("cd")).thenReturn(changeDirectoryCommand);
        when(commandFactory.getCommandFromName("ls")).thenReturn(listFilesCommand);
        when(commandFactory.getCommandFromName("mkdir")).thenReturn(makeDirectoryCommand);
        when(commandFactory.getCommandFromName("")).thenReturn(noActionCommand);
    }

    @Test
    public void testUserInteraction() throws IOException {
        // given
        when(input.nextLine()).thenReturn("cd", "ls -r", "", "mkdir foo bar", "quit");

        // when
        Application application = new Application(Guice.createInjector(new MockModule()));
        application.run();

        // then
        verify(changeDirectoryCommand, times(1)).execute(eq(""), any(ContextStatus.class));
        verify(listFilesCommand, times(1)).execute(eq("-r"), any(ContextStatus.class));
        verify(noActionCommand, times(1)).execute(eq(""), any(ContextStatus.class));
        verify(makeDirectoryCommand, times(1)).execute(eq("foo bar"), any(ContextStatus.class));
        verify(input, times(1)).close();
    }

    @Test
    public void testUserInteractionExceptionThrown() throws IOException {
        // given
        when(input.nextLine()).thenReturn("cd");
        doThrow(Exception.class).when(changeDirectoryCommand).execute(anyString(), any(ContextStatus.class));

        // when
        Application application = new Application(Guice.createInjector(new MockModule()));
        application.run();

        // then
        verify(changeDirectoryCommand, times(1)).execute(eq(""), any(ContextStatus.class));
        verify(input, times(1)).close();
    }

    /**
     * Mock module
     */
    private class MockModule extends AbstractModule {
        @Override
        protected void configure() {
            bind(CommandFactory.class).toInstance(commandFactory);
            bind(Output.class).toInstance(output);
            bind(Input.class).toInstance(input);

            bind(ChangeDirectoryCommand.class).toInstance(changeDirectoryCommand);
            bind(CreateFileCommand.class).toInstance(createFileCommand);
            bind(CurrentDirectoryCommand.class).toInstance(currentDirectoryCommand);
            bind(ListFilesCommand.class).toInstance(listFilesCommand);
            bind(MakeDirectoryCommand.class).toInstance(makeDirectoryCommand);
            bind(NoActionCommand.class).toInstance(noActionCommand);
            bind(UnrecognizedCommand.class).toInstance(unrecognizedCommand);
        }
    }

}
