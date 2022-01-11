package sortingstrategy;

import data.Database;
import michelaneous.Child;

import java.util.Comparator;

public class IdStrategy implements SortStrategy{
    private final Database database;

    public IdStrategy(final Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    @Override
    public void sortChildren() {
        database.setSortedChildren(database.getChildren().stream()
                .sorted(Comparator.comparingInt(Child::getId)).toList());
    }
}
