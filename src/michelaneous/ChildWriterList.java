package michelaneous;

import java.util.ArrayList;

public class ChildWriterList {
    private ArrayList<ChildWriter> children;

    public ChildWriterList() {
        this.children = new ArrayList<>();
    }


    /** getter for the children */
    public ArrayList<ChildWriter> getChildren() {
        return children;
    }

    /** setter for the children */
    public void setChildren(final ArrayList<ChildWriter> children) {
        this.children = children;
    }
}
