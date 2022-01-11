package sortingstrategy;

import data.Database;

import java.util.Objects;

public class NiceScoreStrategy implements SortStrategy{
    private final Database database;

    public NiceScoreStrategy(final Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    @Override
    public void sortChildren() {
        database.setSortedChildren(database.getChildren().stream()
                .sorted((c1, c2) -> {
                    if (Objects.equals(c1.getAverageScore(), c2.getAverageScore())) {
                        return c1.getId() - c2.getId();
                    }

                    return Double.compare(c2.getAverageScore(), c1.getAverageScore());
                }).toList());
    }
}
