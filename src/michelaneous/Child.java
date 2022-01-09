package michelaneous;

import data.Database;
import enums.Category;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Child {
    private Integer id;
    private String lastName;
    private String firstName;
    private Integer age;
    private String city;
    private Double niceScore;
    private ArrayList<Category> giftsPreferences;
    private Double averageScore;
    private String ageCategory;
    private ArrayList<Double> niceScoreHistory;
    private Double assignedBudget;
    private ArrayList<Gift> receivedGifts;
    private ArrayList<Category> receivedCategories;
    private ArrayList<GiftWriter> writerReceivedGifts;
    private Double niceScoreBonus;
    private ElvesType elf;

    public Child() {
        this.id = -1;
        this.lastName = null;
        this.firstName = null;
        this.age = 0;
        this.city = null;
        this.niceScore = (double) 0;
        this.giftsPreferences = new ArrayList<>();
        this.averageScore = (double) 0;
        this.ageCategory = null;
        this.niceScoreHistory = new ArrayList<>();
        this.assignedBudget = (double) 0;
        this.receivedGifts = new ArrayList<>();
        this.receivedCategories = new ArrayList<>();
        this.writerReceivedGifts = new ArrayList<>();
        this.niceScoreBonus = (double) 0;
        this.elf = null;
    }

    public ArrayList<GiftWriter> getWriterReceivedGifts() {
        return writerReceivedGifts;
    }

    public void setWriterReceivedGifts(ArrayList<GiftWriter> writerReceivedGifts) {
        this.writerReceivedGifts = writerReceivedGifts;
    }

    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    public void setNiceScoreBonus(Double niceScoreBonus) {
        this.niceScoreBonus = niceScoreBonus;
    }

    public ElvesType getElf() {
        return elf;
    }

    public void setElf(ElvesType elf) {
        this.elf = elf;
    }

    /** getter for the history of nice scores */
    public ArrayList<Double> getNiceScoreHistory() {
        return niceScoreHistory;
    }

    /** setter for the history of nice scores */
    public void setNiceScoreHistory(final ArrayList<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /** getter for the list of received gifts */
    public ArrayList<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /** setter for the list of received gifts */
    public void setReceivedGifts(final ArrayList<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /** getter for the list of the categories of the received gifts */
    public ArrayList<Category> getReceivedCategories() {
        return receivedCategories;
    }

    /** setter for the list of the categories of the received gifts */
    public void setReceivedCategories(final ArrayList<Category> receivedCategories) {
        this.receivedCategories = receivedCategories;
    }

    /** getter for assigned budget */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /** setter for assigned budget */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /** getter for the average score */
    public Double getAverageScore() {
        return averageScore;
    }

    /** setter for the average score */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
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

    /** getter for the age */
    public Integer getAge() {
        return age;
    }

    /** setter for the age */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /** getter for the city */
    public String getCity() {
        return city;
    }

    /** setter for the city */
    public void setCity(final String city) {
        this.city = city;
    }

    /** getter for the nice score */
    public Double getNiceScore() {
        return niceScore;
    }

    /** setter for the nice score */
    public void setNiceScore(final Double niceScore) {
        this.niceScore = niceScore;
    }

    /** getter for the gift preferences */
    public ArrayList<Category> getGiftsPreferences() {
        return giftsPreferences;
    }

    /** setter for the gift preferences */
    public void setGiftsPreferences(final ArrayList<Category> giftsPreferences) {
        this.giftsPreferences = giftsPreferences;
    }

    /** getter for the age category */
    public String getAgeCategory() {
        return ageCategory;
    }

    /** setter for the age category */
    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    /** method that allows a child to receive a gift */
    public void receiveGift(final List<Gift> sortedGifts) {
        writerReceivedGifts = new ArrayList<>();

        /* the assigned budget for a child */
        Double money = this.assignedBudget;

        /* browsing through all the gift preferences */
        for (Category category : this.getGiftsPreferences()) {
            /* if child doesn't already have a gift from this category */
            if (!this.getReceivedCategories().contains(category)) {
                for (Gift gift : sortedGifts) {
                    if (gift.getQuantity() == 0) {
                        continue;
                    }
                    /* if he has money for the gift */
                    if (money >= gift.getPrice()) {
                        if (gift.getCategory() == category) {
                            /* if he doesn't already have this gift */
                            if (!this.getReceivedGifts().contains(gift)) {
                                /* adds the gift and the category to the respective lists */
                                this.getReceivedGifts().add(gift);
                                this.getReceivedCategories().add(category);
                                this.writerReceivedGifts.add(new GiftWriter(gift.getProductName(),
                                        gift.getPrice(), gift.getCategory()));
                                money -= gift.getPrice();
                                gift.setQuantity(gift.getQuantity() - 1);
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void calculateBudget(final Database database) {
        List<Child> sortedChildren = database.getChildren().stream()
                .sorted(Comparator.comparingInt(Child::getId)).toList();

        double allAverage = 0;
        for (Child child : sortedChildren) {
            allAverage += child.getAverageScore();
        }

        double budgetUnit = database.getSantaBudget() / allAverage;
        this.setAssignedBudget(this.getAverageScore() * budgetUnit);

        switch (this.elf) {
            case BLACK -> {
                Double budget = this.getAssignedBudget();
                budget -= budget * 30 / 100;
                this.setAssignedBudget(budget);
            }

            case PINK -> {
                Double budget = this.getAssignedBudget();
                budget += budget * 30 / 100;
                this.setAssignedBudget(budget);
            }
        }
    }

    public void yellowElf(final List <Gift> sortedGifts) {
        if (this.receivedGifts.size() == 0) {
            Category favouriteCategory = this.giftsPreferences.get(0);
            for (Gift gift : sortedGifts) {
                if (gift.getCategory() == favouriteCategory) {
                    if (gift.getQuantity() > 0) {
                        this.receivedGifts.add(gift);
                        this.receivedCategories.add(favouriteCategory);
                        gift.setQuantity(gift.getQuantity() - 1);
                    }
                }
            }
        }
    }
}
