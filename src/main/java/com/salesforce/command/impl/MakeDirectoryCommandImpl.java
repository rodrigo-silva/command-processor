package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.MakeDirectoryCommand;
import com.salesforce.output.Output;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

/**
 * MakeDirectoryCommand implementation
 */
public class MakeDirectoryCommandImpl extends BaseCommand implements MakeDirectoryCommand {

    @Inject
    public MakeDirectoryCommandImpl(Output output) {
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
        sb.append("Syntax: mkdir <dirName>\n");
        sb.append("<dirName> : the name of the directory to create. Character limit of 100");

        return sb.toString();
    }

    @Override
    protected void doExecute(String[] arguments, ContextStatus contextStatus) throws IOException {
        File dirToCreate = new File(contextStatus.getCurrentPath() + File.separatorChar + arguments[0]);
        if( dirToCreate.exists()) {
            output.println("Directory already exists");
        } else {
            dirToCreate.mkdir();
        }
    }
}
