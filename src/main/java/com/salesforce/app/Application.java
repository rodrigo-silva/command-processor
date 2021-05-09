package com.salesforce.app;

import com.google.inject.Injector;
import com.salesforce.command.Command;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.factory.CommandFactory;
import com.salesforce.input.Input;
import com.salesforce.output.Output;

import java.io.File;

/**
 * Represents the running application
 */
public class Application {
    final Injector injector;
    private Output output;

    public Application(Injector injector) {
        this.injector = injector;
    }

    /**
     * Runs the application
     */
    public void run(){
        final Input scanner = this.injector.getInstance(Input.class);
        output = injector.getInstance(Output.class);
        final CommandFactory commandFactory = injector.getInstance(CommandFactory.class);

        try {
            final ContextStatus contextStatus = new ContextStatus(new File(".").getCanonicalPath());
            UserInput userInput = getUserInput(contextStatus, scanner);

            while(!"quit".equals(userInput.commandName)) {
                Command command = commandFactory.getCommandFromName(userInput.commandName);
                command.execute(userInput.arguments, contextStatus);
                userInput = getUserInput(contextStatus, scanner);
            }
        } catch (Exception e) {
            output.println("Unexpected error has occurred and command processor will terminate.");
            output.println(e.getMessage());
        } finally {
            scanner.close();
            output.println("Have a nice day!");
        }
    }

    /**
     * Gets the user input in the form of {@link UserInput}.
     *
     * @param contextStatus current contextStatus
     * @param scanner to read input from
     * @return UserInput
     */
    private UserInput getUserInput(ContextStatus contextStatus, Input scanner) {
        output.print(">> ");
        final String[] input = scanner.nextLine().trim().split(" ", 2);
        final String arguments = input.length > 1 ? input[1] : "";

        return new UserInput(input[0], arguments);
    }

    /**
     * Represents User's input as command name and its arguments
     */
    private class UserInput {
        private String commandName;
        private String arguments;

        /**
         * Creates valid userInput
         * @param commandName name of the command
         * @param arguments arguments if any
         */
        private UserInput(String commandName, String arguments) {
            this.commandName = commandName;
            this.arguments = arguments;
        }
    }
}
