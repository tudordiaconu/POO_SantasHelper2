package commands;

import data.Database;
import enums.Category;
import michelaneous.Child;
import michelaneous.Gift;
import michelaneous.GiftWriter;

public class YellowElf implements ElfCommand {
    private final Database database;
    private final Child child;

    public YellowElf(final Child child, final Database database) {
        this.child = child;
        this.database = database;
    }

    /** executes a yellow elf sharing of a gift */
    @Override
    public void execute() {
        if (child.getReceivedGifts().size() == 0) {
            // gets the favourite category from the preferences
            Category favouriteCategory = child.getGiftsPreferences().get(0);

            // goes through the list of sorted gifts
            for (Gift gift : database.getSortedGifts()) {

                // if the child is elligible to be helped by the yellow elf
                if (gift.getCategory() == favouriteCategory
                        && child.getReceivedGifts().size() == 0) {

                    // if the gift is still in stock, the child receives the gift
                    if (gift.getQuantity() > 0) {
                        child.getReceivedGifts().add(gift);
                        child.getReceivedCategories().add(favouriteCategory);
                        child.getWriterReceivedGifts().add(new GiftWriter(gift.getProductName(),
                                gift.getPrice(), gift.getCategory()));
                        gift.setQuantity(gift.getQuantity() - 1);
                    } else {
                        break;
                    }
                }
            }
        }
    }
}
