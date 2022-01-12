package sortingstrategy;

import data.Database;
import michelaneous.Child;

import java.util.List;
import java.util.Objects;

public class NiceScoreCityStrategy implements SortStrategy {
    private final Database database;

    public NiceScoreCityStrategy(final Database database) {
        this.database = database;
    }

    /** getter for the database */
    public Database getDatabase() {
        return database;
    }

    /** sorts the children by their city's nice score */
    @Override
    public void sortChildren() {
        database.calculateCityScore();
        List<String> sortedCities = database.getCitiesScore().keySet().stream()
                .sorted((c1, c2) -> {
                    Double value1 = database.getCitiesScore().get(c1);
                    Double value2 = database.getCitiesScore().get(c2);

                    if (Objects.equals(value1, value2)) {
                        return c1.compareTo(c2);
                    } else {
                        return Double.compare(value2, value1);
                    }
                }).toList();

        for (String city : sortedCities) {
            for (Child child : database.getChildren()) {
                if (Objects.equals(child.getCity(), city)) {
                    database.getSortedChildren().add(child);
                }
            }
        }
    }
}
