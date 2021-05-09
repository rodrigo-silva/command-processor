package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.UnrecognizedCommand;
import com.salesforce.output.Output;

/**
 * Implements UnrecognizedCommand
 */
public class UnrecognizedCommandImpl extends BaseCommand implements UnrecognizedCommand {

    @Inject
    public UnrecognizedCommandImpl(Output output) {
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
