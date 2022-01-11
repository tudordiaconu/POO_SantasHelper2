package commands;

import common.Constants;
import data.Database;
import michelaneous.Child;

public class BlackElf implements ElfCommand {
    private final Database database;
    private final Child child;

    public BlackElf(final Child child, final Database database) {
        this.child = child;
        this.database = database;
    }

    /** executes a black elf budget restructuration */
    @Override
    public void execute() {
        Double budget = child.getAssignedBudget();
        budget -= budget * Constants.THIRTY / Constants.HUNDRED;
        child.setAssignedBudget(budget);
    }
}
