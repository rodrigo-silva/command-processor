package com.salesforce.command.impl;

import com.salesforce.command.ContextStatus;
import com.salesforce.output.Output;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

/**
 * This command lists the contents (directories and files) of the current directory.
 * It writes a single item per line. If there are no items, print nothing.
 * If printing recursively, before printing contents, print the full path of the current directory.
 */
public class ListFilesCommand extends BaseCommand {

    @Inject
    public ListFilesCommand(final Output output) {
       super(output);
    }

    @Override
    protected boolean argumentsAreValid(String[] arguments) {
        if (arguments.length > 1) {
            return false;
        }
        if (arguments.length == 1 && !arguments[0].equals("-r")) {
            return false;
        }
        return true;
    }

    @Override
    protected String getHelpText() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[-r] Recursive. If provided, prints everything in the current directory and all subdirectories\n");

        return sb.toString();
    }

    /**
     * Lists the contents (directories and files) of the current directory.
     * It writes a single item per line. If there are no items, print nothing.
     * If printing recursively, before printing contents, print the full path of the current directory.
     *
     * @param arguments user arguments, already validated
     * @param contextStatus current context status
     */
    protected void doExecute(String[] arguments, ContextStatus contextStatus) throws IOException {
        File currentDirectory = new File(contextStatus.getCurrentPath());
        if(arguments.length > 0) {
            output.println(currentDirectory.getCanonicalPath());
            Iterator<File> iterator = getFilesRecursively(currentDirectory);
            while (iterator.hasNext()) {
                File file = iterator.next();
                if (file.getCanonicalPath().equals(currentDirectory.getCanonicalPath())) {
                    // commons-io includes current directory as well. Skip it
                    continue;
                }
                String relativePath = getRelativePathTo(currentDirectory, file);
                output.println(relativePath);
            }
        } else {
            for(File file : currentDirectory.listFiles()) {
                output.println(file.getName());
            }
        }
    }

    /**
     * From a base dir, it gets relative path to it. It is supposed the file is contained in the directory.
     *
     * @param currentDirectory containing the fil
     * @param file exiting file/directory within currentDirectory
     * @return String representation relative path
     * @throws IOException
     */
    private String getRelativePathTo(File currentDirectory, File file) throws IOException {
        String fileFullPath = file.getCanonicalPath();
        return fileFullPath.substring(currentDirectory.getCanonicalPath().length());
    }

    /**
     * Gets all files recursively from a base directory
     * @param currentDirectory to lookup from
     * @return File iterator
     */
    private Iterator<File> getFilesRecursively(File currentDirectory) {
        Collection<File> files = FileUtils.listFilesAndDirs(
                currentDirectory, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);

        return files.iterator();
    }
}
