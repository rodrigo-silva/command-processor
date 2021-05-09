package com.salesforce.command.impl;

import com.salesforce.command.Command;
import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;
import java.io.IOException;

/**
 * Base class for all Command implementations. Contains common behavior and template workflow
 */
public abstract class BaseCommand implements Command {
    protected Output output;

    public BaseCommand(Output output) {
        this.output = output;
    }

    /**
     * Executes the command for the given argumentsLine, in the given context status.
     * @param argumentsLine string representing user's arguments and switches separated by spaces. Cannot be null.
     * @param contextStatus the context status, cannot be null.
     */
    public void execute(String argumentsLine, ContextStatus contextStatus) throws IOException {
        String[] arguments = this.getArgumentsFromArgumentsLine(argumentsLine);
        if(this.argumentsAreValid(arguments)) {
            this.doExecute(arguments, contextStatus);
        } else {
            output.println("Invalid syntax!");
            output.println(this.getHelpText());
        }
    }

    /**
     * Validates if arguments are valid. Subclasses must implement its own rules
     * @param arguments list of arguments, cannot be null
     * @return true if arguments are valid, false otherwise
     */
    protected abstract boolean argumentsAreValid(final String[] arguments);

    /**
     * Generates and returns help for the command. Implementations should provide a multiline string for user
     * reading.
     * @return help text
     */
    protected abstract String getHelpText();

    /**
     * Custom execute implementation. Each subclass should provide its own implementation.
     * @param arguments user arguments, already validated. Cannot be null.
     * @param contextStatus current context status. Cannot be null.
     */
    protected abstract void doExecute(final String[] arguments, ContextStatus contextStatus) throws IOException;

    /**
     * Gets string array arguments from the arguments line. If empty or all blanks string, it returns zero size
     * array.
     * @param argumentsLine arguments passed by the user. Cannot be null
     * @return String[] containing arguments. Can be zero length when no arguments
     */
    private String[] getArgumentsFromArgumentsLine(final String argumentsLine) {

        if(argumentsLine.trim().isEmpty()) {
            return new String[0];
        }
        return argumentsLine.trim().split("\\s*\\s");
    }
}
