package scorestrategy;

import common.Constants;
import michelaneous.Child;

public class KidScoreStrategy implements ScoreStrategy {
    private final Child child;

    public KidScoreStrategy(final Child child) {
        this.child = child;
    }

    /** sets the score of a kid */
    @Override
    public void getScore() {
        double score = 0;

        for (int j = 0; j < child.getNiceScoreHistory().size(); j++) {
            score += child.getNiceScoreHistory().get(j);
        }

        score = score / child.getNiceScoreHistory().size();
        score += score * child.getNiceScoreBonus() / Constants.HUNDRED;

        child.setAverageScore(score);
    }
}
