package seedu.address.testutil;
import seedu.address.model.Deck;
import seedu.address.model.person.Card;

/**
 * A utility class to help with building Deck objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new DeckBuilder().withPerson("John", "Doe").build();}
 */
public class DeckBuilder {

    private Deck deck;

    public DeckBuilder() {
        deck = new Deck();
    }

    public DeckBuilder(Deck deck) {
        this.deck = deck;
    }

    /**
     * Adds a new {@code Person} to the {@code AddressBook} that we are building.
     */
    public DeckBuilder withCard(Card card) {
        deck.addCard(card);
        return this;
    }

    public Deck build() {
        return deck;
    }
}
