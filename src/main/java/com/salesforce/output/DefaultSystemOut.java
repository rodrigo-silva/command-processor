package com.salesforce.output;

/**
 *
 */
public class DefaultSystemOut implements Output {

    public void println(final String line) {
        System.out.println(line);
    }

    public void print(String line) {
        System.out.print(line);
    }
}
