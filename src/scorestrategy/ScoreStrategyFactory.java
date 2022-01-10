package scorestrategy;

import michelaneous.Child;

public final class ScoreStrategyFactory {
    private ScoreStrategyFactory() {
        // constructor for checkstyle
    }

    /** creates a strategy depending on the age category */
    public static ScoreStrategy createStrategy(final Child child) {
        switch (child.getAgeCategory()) {
            case "Baby" -> {
                return new BabyScoreStrategy(child);
            }

            case "Kid" -> {
                return new KidScoreStrategy(child);
            }

            case "Teen" -> {
                return new TeenScoreStrategy(child);
            }

            default -> {
                return null;
            }
        }
    }
}
