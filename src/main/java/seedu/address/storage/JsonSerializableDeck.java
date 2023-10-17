package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Deck;
import seedu.address.model.ReadOnlyDeck;
import seedu.address.model.person.Card;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "deck")
class JsonSerializableDeck {

    public static final String MESSAGE_DUPLICATE_CARD = "Cards list contains duplicate card(s).";

    private final List<JsonAdaptedCard> cards = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given cards.
     */
    @JsonCreator
    public JsonSerializableDeck(@JsonProperty("cards") List<JsonAdaptedCard> cards) {
        this.cards.addAll(cards);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableDeck(ReadOnlyDeck source) {
        cards.addAll(source.getCardList().stream().map(JsonAdaptedCard::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data co nstraints violated.
     */
    public Deck toModelType() throws IllegalValueException {
        Deck deck = new Deck();

        for (JsonAdaptedCard jsonAdaptedCard : cards) {
            Card card = jsonAdaptedCard.toModelType();
            if (deck.hasCard(card)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CARD);
            }
            deck.addCard(card);
        }

        return deck;
    }

}
