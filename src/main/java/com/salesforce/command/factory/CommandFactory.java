package com.salesforce.command.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.salesforce.command.*;

/**
 * Creates the specific command for the given command name. It basically maps command names to concrete classes.
 */
public class CommandFactory {
    final Injector injector;

    @Inject
    public CommandFactory(Injector injector) {
        this.injector = injector;
    }


    /**
     * For a given command name it returns the specific Command class
     * @param commandName string alias for the command
     * @return concrete class
     */
    public Command getCommandFromName(final String commandName) {
        if("".equals(commandName)) {
            return injector.getInstance(NoActionCommand.class);
        }
        if("cd".equals(commandName)) {
            return injector.getInstance(ChangeDirectoryCommand.class);
        }
        if("ls".equals(commandName)) {
            return injector.getInstance(ListFilesCommand.class);
        }
        if("mkdir".equals(commandName)) {
            return injector.getInstance(MakeDirectoryCommand.class);
        }
        if("pwd".equals(commandName)) {
            return injector.getInstance(CurrentDirectoryCommand.class);
        }
        if("touch".equals(commandName)) {
            return injector.getInstance(CreateFileCommand.class);
        }
        // default
        return injector.getInstance(UnrecognizedCommand.class);
    }
}
