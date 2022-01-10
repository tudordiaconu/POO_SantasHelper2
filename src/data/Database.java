package data;

import common.Constants;
import michelaneous.AnnualChange;
import michelaneous.Child;
import michelaneous.Gift;

import java.util.ArrayList;
import java.util.HashMap;

public final class Database {
    private Integer numberOfYears;
    private Double santaBudget;
    private InitialData initialData;
    private ArrayList<Child> children;
    private ArrayList<Gift> gifts;
    private HashMap<String, ArrayList<Double>> citiesScores;

    public void setCitiesScores(final HashMap<String, ArrayList<Double>> citiesScores) {
        this.citiesScores = citiesScores;
    }

    public HashMap<String, Double> getCitiesScore() {
        return citiesScore;
    }

    public void setCitiesScore(final HashMap<String, Double> citiesScore) {
        this.citiesScore = citiesScore;
    }

    private HashMap<String, Double> citiesScore;
    private ArrayList<AnnualChange> annualChanges;

    private static Database instance = null;

    private Database() { }

    /** Singleton pattern lazy instantiation */
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }

        return instance;
    }

    /** sets the age categories for the children */
    public void setAgeCategories() {
        for (Child child : this.getChildren()) {
            if (child.getAge() < Constants.BABY_MAX) {
                child.setAgeCategory("Baby");
            }

            if (child.getAge() >= Constants.BABY_MAX && child.getAge() < Constants.KID_MAX) {
                child.setAgeCategory("Kid");
            }

            if (child.getAge() >= Constants.KID_MAX && child.getAge() <= Constants.TEEN_MAX) {
                child.setAgeCategory("Teen");
            }
        }
    }

    /** getter for the number of years */
    public Integer getNumberOfYears() {
        return numberOfYears;
    }

    /** setter for the number of years */
    public void setNumberOfYears(final Integer numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    /** getter for Santa's budget */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /** setter for Santa's budget */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /** getter for the initial data */
    public InitialData getInitialData() {
        return initialData;
    }

    /** setter for the initial data */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /** getter for the list of children in the database */
    public ArrayList<Child> getChildren() {
        return children;
    }

    /** setter for the list of children in the database */
    public void setChildren(final ArrayList<Child> children) {
        this.children = children;
    }


    /** getter for the list of gifts in the database */
    public ArrayList<Gift> getGifts() {
        return gifts;
    }


    /** setter for the list of gifts in the database */
    public void setGifts(final ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }


    /** getter for the list of cities in the database */
    public HashMap<String, ArrayList<Double>> getCitiesScores() {
        return citiesScores;
    }


    /** setter for the list of cities in the database */
    public void setCities(final HashMap<String, ArrayList<Double>> cities) {
        this.citiesScores = cities;
    }

    /** populates the hashmap of cities with the list of scores for each city */
    public void populateCities() {
        this.setCities(new HashMap<>());

        for (Child child : this.children) {
            if (!this.citiesScores.containsKey(child.getCity())) {
                this.citiesScores.put(child.getCity(), new ArrayList<>());
            }
            this.citiesScores.get(child.getCity()).add(child.getAverageScore());
        }
    }

    /** calculates the nice score for each city */
    public void calculateCityScore() {
        this.citiesScore = new HashMap<>();
        this.populateCities();

        for (String city : this.citiesScores.keySet()) {
            ArrayList<Double> scores = this.citiesScores.get(city);
            Double sum = (double) 0;

            for (Double score : scores) {
                sum += score;
            }

            citiesScore.put(city, sum / scores.size());
        }
    }

    /** getter for the list of annual changes */
    public ArrayList<AnnualChange> getAnnualChanges() {
        return annualChanges;
    }


    /** getter for the list of annual changes */
    public void setAnnualChanges(final ArrayList<AnnualChange> annualChanges) {
        this.annualChanges = annualChanges;
    }
}
