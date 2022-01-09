package michelaneous;

import enums.Category;
import enums.ElvesType;

import java.util.ArrayList;

public class ChildUpdate {
    private Integer id;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private ElvesType elf;

    public ChildUpdate() {
        this.id = null;
        this.niceScore = null;
        this.giftsPreferences = null;
        this.elf = null;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }

    /** getter for the id of the update */
    public Integer getId() {
        return id;
    }

    /** setter for the id of the update */
    public void setId(final Integer id) {
        this.id = id;
    }

    /** getter for the nice score of the update */
    public Double getNiceScore() {
        return niceScore;
    }

    /** setter for the nice score of the update */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /** getter for the new gift preferences */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /** setter for the new gift preferences */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }
}
