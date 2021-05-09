package com.salesforce.input;

import java.io.IOException;
import java.util.Scanner;

/**
 * Default application input, this is system in user input
 */
public class DefaultInput implements Input {

    private final Scanner scanner;

    public DefaultInput() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }

    @Override
    public void close() {
        this.scanner.close();
    }
}
