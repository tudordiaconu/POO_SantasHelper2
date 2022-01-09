package michelaneous;

import enums.Category;

import java.util.ArrayList;

public class ChildWriter {
    private Integer id;
    private String lastName;
    private String firstName;
    private String city;
    private Integer age;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<GiftWriter> receivedGifts;

    public ChildWriter(final Integer id, final String lastName, final String firstName,
                       final String city, final Integer age,
                       final ArrayList<Category> giftsPreferences, final Double averageScore,
                       final ArrayList<Double> niceScoreHistory, final Double assignedBudget,
                       final ArrayList<GiftWriter> receivedGifts) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.city = city;
        this.age = age;
        this.giftsPreferences = new ArrayList<>();
        this.giftsPreferences.addAll(giftsPreferences);
        this.averageScore = averageScore;
        this.niceScoreHistory = new ArrayList<>();
        this.niceScoreHistory.addAll(niceScoreHistory);
        this.assignedBudget = assignedBudget;
        this.receivedGifts = new ArrayList<>();
        this.receivedGifts.addAll(receivedGifts);
    }

    /** getter for the id */
    public Integer getId() {
        return id;
    }

    /** setter for the id */
    public void setId(final Integer id) {
        this.id = id;
    }

    /** getter for the lastname */
    public String getLastName() {
        return lastName;
    }

    /** setter for the lastname */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /** getter for the firstname */
    public String getFirstName() {
        return firstName;
    }

    /** setter for the firstname */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /** getter for the city */
    public String getCity() {
        return city;
    }

    /** setter for the city */
    public void setCity(final String city) {
        this.city = city;
    }

    /** getter for the age */
    public Integer getAge() {
        return age;
    }

    /** setter for the age */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /** getter for the gift preferences */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /** setter for the gift preferences */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /** getter for the average score */
    public Double getAverageScore() {
        return averageScore;
    }

    /** setter for the average score */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /** getter for the nice score history */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /** setter for the nice score history */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /** getter for the assigned budget */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /** setter for the assigned budget */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /** getter for the received gifts */
    public ArrayList<GiftWriter> getReceivedGifts() {
        return receivedGifts;
    }

    /** setter for the received gifts */
    public void setReceivedGifts(final ArrayList<GiftWriter> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }
}
