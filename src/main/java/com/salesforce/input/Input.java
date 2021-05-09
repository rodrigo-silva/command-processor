package com.salesforce.input;

import java.io.Closeable;

/**
 * Represents application input
 */
public interface Input {

    /**
     * Gets next read line from input
     * @return
     */
    String nextLine();

    /**
     * Closes this input
     */
    void close();
}
