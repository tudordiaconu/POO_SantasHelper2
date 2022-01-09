package data;

import michelaneous.ChildWriterList;

import java.util.ArrayList;

public class WriteDatabase {
    private ArrayList<ChildWriterList> annualChildren;

    public WriteDatabase() {
        this.annualChildren = new ArrayList<>();
    }

    /** getter for the list of ChildWriters */
    public ArrayList<ChildWriterList> getAnnualChildren() {
        return annualChildren;
    }


    /** setter for the list of ChildWriters */
    public void setAnnualChildren(final ArrayList<ChildWriterList> annualChildren) {
        this.annualChildren = annualChildren;
    }
}
