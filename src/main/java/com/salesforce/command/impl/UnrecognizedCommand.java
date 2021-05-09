package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;

/**
 * Encapsulates all unknown Commands
 */
public class UnrecognizedCommand extends BaseCommand {

    @Inject
    public UnrecognizedCommand(Output output) {
        super(output);
    }

    /**
     * Always true
     * @param arguments list of arguments
     * @return Always true
     */
    protected boolean argumentsAreValid(String[] arguments) {
        return true;
    }

    /**
     * Returns empty message
     * @return empty string
     */
    protected String getHelpText() {
        return "";
    }

    /**
     * Prints out an error message
     * @param arguments user arguments, already validated
     * @param contextStatus current context status
     */
    protected void doExecute(String[] arguments, ContextStatus contextStatus) {
        this.output.println("Unrecognized command!");
    }
}
