package com.salesforce.command;

import java.io.IOException;

/**
 * Interface that represent command to be executed
 */
public interface Command {

    void execute(String argumentsLine, ContextStatus contextStatus) throws IOException;

}
