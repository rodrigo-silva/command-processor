package com.salesforce;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.salesforce.command.*;
import com.salesforce.command.factory.CommandFactory;
import com.salesforce.command.impl.*;
import com.salesforce.input.DefaultInput;
import com.salesforce.input.Input;
import com.salesforce.output.DefaultSystemOut;
import com.salesforce.output.Output;

public class ApplicationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandFactory.class).in(Singleton.class);
        bind(Output.class).to(DefaultSystemOut.class).in(Singleton.class);
        bind(Input.class).to(DefaultInput.class).in(Singleton.class);

        // Commands
        bind(ChangeDirectoryCommand.class).to(ChangeDirectoryCommandImpl.class).in(Singleton.class);
        bind(CreateFileCommand.class).to(CreateFileCommandImpl.class).in(Singleton.class);
        bind(CurrentDirectoryCommand.class).to(CurrentDirectoryCommandImpl.class).in(Singleton.class);
        bind(ListFilesCommand.class).to(ListFilesCommandImpl.class).in(Singleton.class);
        bind(MakeDirectoryCommand.class).to(MakeDirectoryCommandImpl.class).in(Singleton.class);
        bind(NoActionCommand.class).to(NoActionCommandImpl.class).in(Singleton.class);
        bind(UnrecognizedCommand.class).to(UnrecognizedCommandImpl.class).in(Singleton.class);
    }
}
