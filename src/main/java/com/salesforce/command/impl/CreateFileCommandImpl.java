package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.CreateFileCommand;
import com.salesforce.command.MakeDirectoryCommand;
import com.salesforce.output.Output;

import java.io.File;
import java.io.IOException;

/**
 * CreateFileCommand implementation
 */
public class CreateFileCommandImpl extends BaseCommand implements CreateFileCommand {

    @Inject
    public CreateFileCommandImpl(Output output) {
        super(output);
    }

    @Override
    protected boolean argumentsAreValid(String[] arguments) {
        if(arguments.length != 1) {
            return false;
        }

        if(arguments[0].length() > 100) {
            return false;
        }

        return true;
    }

    @Override
    protected String getHelpText() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Syntax: touch <fileName>\n");
        sb.append("<fileName> : the name of the file to create. Character limit of 100");

        return sb.toString();
    }

    @Override
    protected void doExecute(String[] arguments, ContextStatus contextStatus) throws IOException {
        File fileToCreate = new File(contextStatus.getCurrentPath() + File.separatorChar + arguments[0]);
        if( fileToCreate.exists()) {
            output.println("File already exists");
        } else {
            fileToCreate.createNewFile();
        }
    }
}
