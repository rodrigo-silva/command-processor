package com.salesforce.command.factory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.salesforce.command.Command;
import com.salesforce.command.impl.ListFilesCommand;
import com.salesforce.command.impl.UnrecognizedCommand;

public class CommandFactory {

    @Inject
    Injector injector;

    public Command getCommandFromName(final String commandName) {
        return injector.getInstance(ListFilesCommand.class);
    }
}
