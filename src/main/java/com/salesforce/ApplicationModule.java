package com.salesforce;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.salesforce.command.ListFilesCommand;
import com.salesforce.command.factory.CommandFactory;
import com.salesforce.command.impl.ListFilesCommandImpl;
import com.salesforce.command.impl.UnrecognizedCommandImpl;
import com.salesforce.output.DefaultSystemOut;
import com.salesforce.output.Output;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactory.class).in(Singleton.class);
        bind(Output.class).to(DefaultSystemOut.class).in(Singleton.class);

        // Commands
        bind(ListFilesCommand.class).to(ListFilesCommandImpl.class).in(Singleton.class);
        bind(UnrecognizedCommandImpl.class).in(Singleton.class);
    }
}
