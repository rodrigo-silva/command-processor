package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.CurrentDirectoryCommand;
import com.salesforce.output.Output;

import java.io.IOException;

/**
 * CurrentDirectoryCommand implementation
 */
public class CurrentDirectoryCommandImpl extends BaseCommand implements CurrentDirectoryCommand {

    @Inject
    public CurrentDirectoryCommandImpl(Output output) {
        super(output);
    }

    @Override
    protected boolean argumentsAreValid(String[] arguments) {
        if(arguments.length > 0) {
            return false;
        }
        return true;
    }

    @Override
    protected String getHelpText() {
        return "Syntax: pwd";
    }

    @Override
    protected void doExecute(String[] arguments, ContextStatus contextStatus) throws IOException {
        output.println(contextStatus.getCurrentPath());
    }
}
