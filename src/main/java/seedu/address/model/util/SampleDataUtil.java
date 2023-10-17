package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Deck;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Card;
import seedu.address.model.person.Question;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Card[] getSampleCard() {
        return new Card[] {new Card(new Question("What is 1 + 1"),
                new Answer("2"), "new"), new Card(new Question("What is 1 + 3"), new Answer("4"),
                "easy")
        };
    }

    public static Deck getSampleDeck() {
        Deck sampleAb = new Deck();
        for (Card sampleCard : getSampleCard()) {
            sampleAb.addCard(sampleCard);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
