package com.salesforce;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.salesforce.app.Application;
import com.salesforce.command.Command;
import com.salesforce.command.ContextStatus;
import com.salesforce.command.factory.CommandFactory;
import com.salesforce.output.Output;

import java.io.File;
import java.util.Scanner;

/**
 * Represents the Command Processor Application
 */
public class MainClass {

    public static void main(String[] args) {
        final Injector injector = Guice.createInjector(new ApplicationModule());
        new Application(injector).run();
    }
}
