package client;

import commands.BlackElf;
import commands.CommandType;
import commands.ElfCommand;
import commands.PinkElf;
import commands.YellowElf;
import data.Database;
import michelaneous.Child;

public class Client {
    private final Invoker invoker;
    private final Child child;
    private final Database database;

    public Client(final Child child, final Database database) {
        invoker = new Invoker();
        this.child = child;
        this.database = database;
    }

    /** executes an action given as a parameter */
    public void executeAction(final String commandName, final Child kid, final Database data) {
        ElfCommand command;

        // converts a string to a command
        try {
            CommandType commandType = CommandType.fromString(commandName);
            command = getCommand(commandType, kid, data);

            if (command == null) {
                throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command: " + commandName);
            System.out.println("Available commands:");
            for (CommandType type : CommandType.values()) {
                System.out.println("\t- " + type.getText());
            }
            return;
        }

        // passes the command to the invoker to be executed
        invoker.execute(command);
    }

    /** factory pattern to create a command */
    private ElfCommand getCommand(final CommandType type, final Child kid, final Database data)
            throws IllegalArgumentException {
        try {
            switch (type) {
                case YELLOW_ELF -> {
                    return new YellowElf(kid, data);
                }

                case PINK_ELF -> {
                    return new PinkElf(kid, data);
                }

                case BLACK_ELF -> {
                    return new BlackElf(kid, data);
                }

                default -> {
                    return null;
                }
            }
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid command: " + type);
            System.out.println("Available commands:");
            for (CommandType commandType : CommandType.values()) {
                System.out.println("\t- " + type.getText());
            }
            return null;
        }
    }
}
