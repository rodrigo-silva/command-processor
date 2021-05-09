package com.salesforce.output;

/**
 * Represents application output. It can be screen, file, etc
 */
public interface Output {

    /**
     * Prints a String and then terminate the line.
     * @param line line to print
     */
    void println(String line);

    /**
     * Prints a string
     * @param line
     */
    void print(String line);
}
