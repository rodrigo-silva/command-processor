package com.salesforce.output;

/**
 *
 */
public class DefaultSystemOut implements Output {

    public void println(final String line) {
        System.out.println(line);
    }
}
