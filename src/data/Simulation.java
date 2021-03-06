package data;

import client.Client;
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
import factories.ScoreStrategyFactory;
import sortingstrategy.SortStrategy;
import factories.SortStrategyFactory;

import java.util.ArrayList;
import java.util.Comparator;

public final class Simulation {
    private Simulation() {
        // constructor for checkstyle
    }

    /** method that gives gifts to children from database */
    public static void givingGifts(final Database database, final WriteDatabase writeDatabase,
                                   final CityStrategyEnum strategy) {
        ChildWriterList auxiliarList = new ChildWriterList();

        /* sorts the gifts by the price in order to get the cheapest one */
        database.setSortedGifts(database.getGifts().stream()
                .sorted(Comparator.comparingDouble(Gift::getPrice)).toList());

        database.setAgeCategories();
        database.setSortedChildren(new ArrayList<>());

        /* calculates the average score for each child, based on its age */
        for (Child child : database.getChildren()) {
            ScoreStrategy scoreStrategy = ScoreStrategyFactory.createStrategy(child);

            if (scoreStrategy != null) {
                scoreStrategy.getScore();
            }
        }

        /* strategy pattern for sorting the list of children based on the strategy given
        * in the input */
        SortStrategy sortStrategy = SortStrategyFactory.createStrategy(strategy, database);
        if (sortStrategy != null) {
            sortStrategy.sortChildren();
        }

        // goes through the list of sorted children
        for (Child child : database.getSortedChildren()) {
            // initialization of the client for the command pattern
            Client client = new Client(child, database);

            // calculates the budget for each child
            child.calculateBudget(database);

            // makes the changes on the budget, depending on the elf
            switch (child.getElf()) {
                case BLACK -> client.executeAction("black elf", child, database);

                case PINK -> client.executeAction("pink elf", child, database);

                default -> {
                    Double budget = child.getAssignedBudget();
                    child.setAssignedBudget(budget);
                }
            }

            // enables the child to receive his gifts
            child.receiveGift(database.getSortedGifts());

            // if the child is eligible to be helped by the yellow elf, he is helped
            if (child.getElf() == ElvesType.YELLOW) {
                client.executeAction("yellow elf", child, database);
            }
        }

        for (Child child : database.getChildren()) {
            // creates a new arraylist of unique categories, used for printing
            ArrayList<Category> uniqueGiftsPreferences = new ArrayList<>();
            for (Category category : child.getGiftsPreferences()) {
                if (!uniqueGiftsPreferences.contains(category)) {
                    uniqueGiftsPreferences.add(category);
                }
            }

            /* creates a new childwriter in order to print the child */
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
