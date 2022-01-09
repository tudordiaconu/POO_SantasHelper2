package data;

import michelaneous.Child;
import michelaneous.Gift;

import java.util.ArrayList;

public class InitialData {
    private ArrayList<Child> children;
    private ArrayList<Gift> santaGiftsList;


    /** getter for the list of children in the initial data */
    public ArrayList<Child> getChildren() {
        return children;
    }


    /** setter for the list of children in the initial data */
    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }

    /** getter for the list of gifts in the initial data */
    public ArrayList<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    /** setter for the list of gifts in the initial data */
    public void setSantaGiftsList(final ArrayList<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }
}
