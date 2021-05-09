package com.salesforce.command.impl;

import com.google.inject.Inject;
import com.salesforce.command.ChangeDirectoryCommand;
import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;

import java.io.File;
import java.io.IOException;

/**
 * ChangeDirectoryCommand implementations
 */
public class ChangeDirectoryCommandImpl extends BaseCommand implements ChangeDirectoryCommand {

    @Inject
    public ChangeDirectoryCommandImpl(Output output) {
        super(output);
    }

    @Override
    protected boolean argumentsAreValid(String[] arguments) {
        if(arguments.length != 1) {
            return false;
        }
        return true;
    }

    @Override
    protected String getHelpText() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Syntax: cd <dirName | ..>\n");
        sb.append("<dirName> : the name of the directory to change to");
        sb.append("<..> : means parent directory");
        return sb.toString();
    }

    @Override
    protected void doExecute(String[] arguments, ContextStatus contextStatus) throws IOException {
        if(".".equals(arguments[0])) {
            // should not change directory
            return;
        }

        if("..".equals(arguments[0])) {
            contextStatus.setCurrentPath(getCanonicalParentPath(contextStatus));
        } else {
            File dirToGo = new File(contextStatus.getCurrentPath() + File.separatorChar + arguments[0]);
            if(dirToGo.exists()) {
                contextStatus.setCurrentPath(dirToGo.getCanonicalPath());
            } else {
                output.println("Directory not found");
            }
        }
    }

    /**
     * Gets the parent directory from the context status
     *
     * @param contextStatus current context
     * @return String canonical path
     */
    private String getCanonicalParentPath(final ContextStatus contextStatus) {
        String currentPath = contextStatus.getCurrentPath();
        if(isRootPath(currentPath)) {
            // root has no parent
            return currentPath;
        } else {
            return currentPath.substring(0, currentPath.lastIndexOf(File.separatorChar));
        }
    }

    /**
     * Tells if the string path is root or not
     * @param path any path
     * @return true if is root, false otherwise
     */
    private boolean isRootPath(final String path) {
        if(isLinuxPath(path)) {
            // LINUX
            return path.equals("/");
        } else {
            // Windows is X:\
            return path.length() == 3;
        }
    }

    /**
     * Tells if this path is linux path
     * @param path string path
     * @return true if this path is linux path
     */
    private boolean isLinuxPath(String path) {
        return path.startsWith("/");
    }
}
