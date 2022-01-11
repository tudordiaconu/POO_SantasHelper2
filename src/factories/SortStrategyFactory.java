package factories;

import data.Database;
import enums.CityStrategyEnum;
import sortingstrategy.IdStrategy;
import sortingstrategy.NiceScoreCityStrategy;
import sortingstrategy.NiceScoreStrategy;
import sortingstrategy.SortStrategy;

public final class SortStrategyFactory {
    private SortStrategyFactory() {
        // constructor for checkstyle
    }

    public static SortStrategy createStrategy(CityStrategyEnum strategy,
                                              Database database) {
        switch (strategy) {
            case ID -> {
                return new IdStrategy(database);
            }

            case NICE_SCORE -> {
                return new NiceScoreStrategy(database);
            }

            case NICE_SCORE_CITY -> {
                return new NiceScoreCityStrategy(database);
            }

            default -> {
                return null;
            }
        }
    }
}
