package scorestrategy;

import common.Constants;
import michelaneous.Child;

public class BabyScoreStrategy implements ScoreStrategy {
    private final Child child;

    public BabyScoreStrategy(final Child child) {
        this.child = child;
    }

    /** sets the score of a baby */
    @Override
    public void getScore() {
        double score = Constants.BABY_SCORE;
        score += score * child.getNiceScoreBonus() / Constants.HUNDRED;

        if (score > 10) {
            score = 10;
        }

        child.setAverageScore(score);
    }
}
