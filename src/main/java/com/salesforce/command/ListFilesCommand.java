package com.salesforce.command;

/**
 * This command lists the contents (directories and files) of the current directory.
 * It writes a single item per line. If there are no items, print nothing.
 * If printing recursively, before printing contents, print the full path of the current directory.
 */
public interface ListFilesCommand extends Command {
}
