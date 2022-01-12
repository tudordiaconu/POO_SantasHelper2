package client;

import commands.ElfCommand;

public class Invoker {

    /** executes a command */
    public void execute(final ElfCommand command) {
        command.execute();
    }
}
