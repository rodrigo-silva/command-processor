package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;

/**
 * Command that does not perform any actions
 */
public class NoActionCommand extends BaseCommand {

    @Inject
    public NoActionCommand(Output output) {
        super(output);
    }

    /**
     * Always true
     * @param arguments list of arguments
     * @return always true
     */
    protected boolean argumentsAreValid(String[] arguments) {
        return true;
    }

    /**
     * Does not have any help text
     * @return empty string
     */
    protected String getHelpText() {
        return "";
    }

    /**
     * Does not do anything
     * @param arguments user arguments, already validated
     * @param contextStatus current context status
     */
    protected void doExecute(String[] arguments, ContextStatus contextStatus) {
        // no action to do
    }
}
