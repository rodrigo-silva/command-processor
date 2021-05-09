package com.salesforce.command;

/**
 * Represent Application context status, like the current dir path. This class is mutable
 */
public class ContextStatus {
    private String currentPath;

    /**
     * Creates valid ContextStatus
     *
     * @param currentPath valid current path
     */
    public ContextStatus(String currentPath) {
        this.currentPath = currentPath;
    }

    public String getCurrentPath() {
        return currentPath;
    }

    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }
}
