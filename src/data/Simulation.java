package data;

import common.Constants;
import enums.Category;
import enums.CityStrategyEnum;
import enums.ElvesType;
import michelaneous.AnnualChange;
import michelaneous.Child;
import michelaneous.ChildWriter;
import michelaneous.ChildWriterList;
import michelaneous.Gift;
import scorestrategy.ScoreStrategy;
import scorestrategy.ScoreStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class Simulation {
    private Simulation() {
        // constructor for checkstyle
    }

    /** method that gives gifts to children from database */
    public static void givingGifts(final Database database, final WriteDatabase writeDatabase,
                                   final CityStrategyEnum strategy) {
        ChildWriterList auxiliarList = new ChildWriterList();

        /* sorts the gifts by the price in order to get the cheapest one */
        List<Gift> sortedGifts = database.getGifts().stream()
                .sorted(Comparator.comparingDouble(Gift::getPrice)).toList();

        database.setAgeCategories();

        List<Child> children = new ArrayList<>();

        /* calculates the average score for each child, based on its age */
        for (Child child : database.getChildren()) {
            ScoreStrategy scoreStrategy = ScoreStrategyFactory.createStrategy(child);

            if (scoreStrategy != null) {
                scoreStrategy.getScore();
            }
        }

        switch (strategy) {
            case ID -> {
                children = database.getChildren().stream()
                        .sorted(Comparator.comparingInt(Child::getId)).toList();
            }

            case NICE_SCORE -> {
                children = database.getChildren().stream()
                        .sorted((c1, c2) -> {
                            if (Objects.equals(c1.getAverageScore(), c2.getAverageScore())) {
                                return c1.getId() - c2.getId();
                            }

                            return Double.compare(c2.getAverageScore(), c1.getAverageScore());
                        }).toList();
            }

            case NICE_SCORE_CITY -> {
                database.calculateCityScore();
                List<String> sortedCities = database.getCitiesScore().keySet().stream()
                        .sorted((c1, c2) -> {
                            Double value1 = database.getCitiesScore().get(c1);
                            Double value2 = database.getCitiesScore().get(c2);

                            if (Objects.equals(value1, value2)) {
                                return c1.compareTo(c2);
                            } else {
                                return Double.compare(value2, value1);
                            }
                        }).toList();
                for (String city : sortedCities) {
                    for (Child child : database.getChildren()) {
                        if (Objects.equals(child.getCity(), city)) {
                            children.add(child);
                        }
                    }
                }
            }
        }

        for (Child child : children) {
            child.calculateBudget(database);
            child.receiveGift(sortedGifts);
            if (child.getElf() == ElvesType.YELLOW) {
                child.yellowElf(sortedGifts);
            }
        }

        for (Child child : database.getChildren()) {
            /* creates a new childwriter in order to print the child */
            ArrayList<Category> uniqueGiftsPreferences = new ArrayList<>();

            for (Category category : child.getGiftsPreferences()) {
                if (!uniqueGiftsPreferences.contains(category)) {
                    uniqueGiftsPreferences.add(category);
                }
            }

            auxiliarList.getChildren().add(new ChildWriter(child.getId(), child.getLastName(),
                    child.getFirstName(), child.getCity(), child.getAge(),
                    uniqueGiftsPreferences, child.getAverageScore(),
                    child.getNiceScoreHistory(), child.getAssignedBudget(),
                    child.getWriterReceivedGifts()));
        }

        /* adds to the output database the list of childwriters for this year */
        writeDatabase.getAnnualChildren().add(auxiliarList);
    }

    /** method that simulates round zero */
    public static void roundZero(final Database database, final WriteDatabase writeDatabase) {
        /* sets the children list and the gift list to the database*/
        database.setChildren(database.getInitialData().getChildren());
        database.setGifts(database.getInitialData().getSantaGiftsList());


        /* removes the young adults */
        database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));

        /* adds to the nice score history for each child */
        for (Child child : database.getChildren()) {
            child.getNiceScoreHistory().add(child.getNiceScore());
        }

        /* shares gifts to the children */
        Simulation.givingGifts(database, writeDatabase, CityStrategyEnum.ID);
    }

    /** method that does the rounds following round zero */
    public static void action(final Database database, final WriteDatabase writeDatabase) {
        for (int i = 0; i < database.getNumberOfYears(); i++) {
            /* erases the received gifts from the previous year */
            for (Child child : database.getChildren()) {
                child.setReceivedGifts(new ArrayList<>());
                child.setReceivedCategories(new ArrayList<>());
            }

            /* ages the child by one year */
            for (Child child : database.getChildren()) {
                child.setAge(child.getAge() + 1);
            }

            /* removes young adults */
            database.getChildren().removeIf(child -> (child.getAge() > Constants.TEEN_MAX));

            AnnualChange currentChange = database.getAnnualChanges().get(i);
            /* adds the new children if they're not young adults */
            for (Child child : currentChange.getNewChildren()) {
                if (child.getAge() <= Constants.TEEN_MAX) {
                    if (!database.getChildren().contains(child)) {
                        database.getChildren().add(child);
                        child.getNiceScoreHistory().add(child.getNiceScore());
                    }
                }
            }

            /* makes the children specific updates */
            currentChange.updateChildren(database);

            /* adds the new gifts */
            for (Gift gift : currentChange.getNewGifts()) {
                if (!database.getGifts().contains(gift)) {
                    database.getGifts().add(gift);
                }
            }

            CityStrategyEnum strategy = currentChange.getStrategy();

            /* updates Santa's budget */
            database.setSantaBudget(currentChange.getNewSantaBudget());
            /* shares gifts to the children */
            Simulation.givingGifts(database, writeDatabase, strategy);
        }
    }
}
