package com.salesforce.command.factory;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.salesforce.command.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandFactoryTest {
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

    @Test
    public void testCommandInterfaceMapping() {
        CommandFactory commandFactory = new CommandFactory(Guice.createInjector(new MockModule()));

        assertEquals(this.changeDirectoryCommand, commandFactory.getCommandFromName("cd"));
        assertEquals(this.listFilesCommand, commandFactory.getCommandFromName("ls"));
        assertEquals(this.makeDirectoryCommand, commandFactory.getCommandFromName("mkdir"));
        assertEquals(this.currentDirectoryCommand, commandFactory.getCommandFromName("pwd"));
        assertEquals(this.createFileCommand, commandFactory.getCommandFromName("touch"));
        assertEquals(this.noActionCommand, commandFactory.getCommandFromName(""));
        assertEquals(this.unrecognizedCommand, commandFactory.getCommandFromName("invalid"));
    }

    /**
     * Mock module
     */
    private class MockModule extends AbstractModule {
        @Override
        protected void configure() {
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
