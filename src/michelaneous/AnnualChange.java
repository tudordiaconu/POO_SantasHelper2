package michelaneous;

import data.Database;
import enums.Category;
import enums.CityStrategyEnum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class AnnualChange {
    private Double newSantaBudget;
    private ArrayList<Gift> newGifts;
    private ArrayList<Child> newChildren;
    private ArrayList<ChildUpdate> childrenUpdates;
    private CityStrategyEnum strategy;

    public AnnualChange() {
        this.newSantaBudget = null;
        this.newGifts = null;
        this.newChildren = null;
        this.childrenUpdates = null;
        this.strategy = null;
    }

    public void setChildrenUpdates(ArrayList<ChildUpdate> childrenUpdates) {
        this.childrenUpdates = childrenUpdates;
    }

    public CityStrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(CityStrategyEnum strategy) {
        this.strategy = strategy;
    }

    /** getter for the new budget */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /** setter for the new budget */
    public void setNewSantaBudget(final Double newSantaBudget) {
        this.newSantaBudget = newSantaBudget;
    }

    /** getter for the new gifts */
    public ArrayList<Gift> getNewGifts() {
        return newGifts;
    }

    /** setter for the new gifts */
    public void setNewGifts(final ArrayList<Gift> newGifts) {
        this.newGifts = newGifts;
    }

    /** getter for the new children */
    public ArrayList<Child> getNewChildren() {
        return newChildren;
    }

    /** setter for the new children */
    public void setNewChildren(final ArrayList<Child> newChildren) {
        this.newChildren = newChildren;
    }

    /** getter for the children updates */
    public ArrayList<ChildUpdate> getChildrenUpdates() {
        return childrenUpdates;
    }

    /** setter for the children updates */
    public void setChildUpdates(final ArrayList<ChildUpdate> childrenUpdate) {
        this.childrenUpdates = childrenUpdate;
    }

    /** makes the updates on the children every year */
    public void updateChildren(final Database database) {
        for (ChildUpdate childUpdate : this.getChildrenUpdates()) {
            for (Child child : database.getChildren()) {
                if (Objects.equals(child.getId(), childUpdate.getId())) {
                    /* if the child receives a new nice score, it is added to
                    *  the history of nice scores */
                    if (childUpdate.getNiceScore() != null) {
                        child.getNiceScoreHistory().add(childUpdate.getNiceScore());
                    }

                    /* if the child receives new gift preferences */
                    if (childUpdate.getGiftsPreferences() != null) {
                        ArrayList<Category> childReversedGiftPreferences =
                                child.getGiftsPreferences();
                        ArrayList<Category> childUpdateReversedGiftPreferences =
                                childUpdate.getGiftsPreferences();

                        /* reverses the old preferences and the new preferences */
                        Collections.reverse(childReversedGiftPreferences);
                        Collections.reverse(childUpdateReversedGiftPreferences);

                        /* now the preferences are from the least wanted to the most wanted */
                        /* in both lists */

                        /* adds the new preferences from the least wanted at the end of the
                        *  old preferences list */
                        for (Category category : childUpdateReversedGiftPreferences) {
                            if (childReversedGiftPreferences.contains(category)) {
                                childReversedGiftPreferences.remove(category);
                                childReversedGiftPreferences.add(category);
                            } else {
                                childReversedGiftPreferences.add(category);
                            }
                        }

                        /* now the list that has all the preferences is sorted from the
                        *  least wanted to the most wanted so we need to reverse it again */
                        Collections.reverse(childReversedGiftPreferences);
                        child.setGiftsPreferences(childReversedGiftPreferences);
                    }

                    if (childUpdate.getElf() != null) {
                        child.setElf(childUpdate.getElf());
                    }
                }
            }
        }
    }
}
