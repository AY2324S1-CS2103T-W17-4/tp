package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Card;



/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager2 implements Model2 {
    private static final Logger logger = LogsCenter.getLogger(ModelManager2.class);

    private final Deck deck;
    private final UserPrefs2 userPrefs;
    private final FilteredList<Card> filteredCards;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager2(ReadOnlyDeck deck, ReadOnlyUserPrefs2 userPrefs) {
        requireAllNonNull(deck, userPrefs);

        logger.fine("Initializing with address book: " + deck + " and user prefs " + userPrefs);

        this.deck = new Deck(deck);
        this.userPrefs = new UserPrefs2(userPrefs);
        filteredCards = new FilteredList<>(this.deck.getCardList());
    }

    public ModelManager2() {
        this(new Deck(), new UserPrefs2());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs2 userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs2 getUserPrefs() {
        return userPrefs;
    }
    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getDeckFilePath() {
        return userPrefs.getDeckFilePath();
    }

    @Override
    public void setDeckFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setDeckFilePath(addressBookFilePath);
    }

    //=========== Deck ================================================================================

    @Override
    public void setDeck(ReadOnlyDeck deck) {
        this.deck.resetData(deck);
    }

    @Override
    public ReadOnlyDeck getDeck() {
        return deck;
    }

    @Override
    public boolean hasCard(Card card) {
        requireNonNull(card);
        return deck.hasCard(card);
    }

    @Override
    public void deleteCard(Card target) {
        deck.removeCard(target);
    }

    @Override
    public void addCard(Card card) {
        deck.addCard(card);
        updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
    }

    @Override
    public void setCard(Card target, Card editedCard) {
        requireAllNonNull(target, editedCard);

        deck.setCard(target, editedCard);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedDeck}
     */
    @Override
    public ObservableList<Card> getFilteredCardList() {
        return filteredCards;
    }

    @Override
    public void updateFilteredCardList(Predicate<Card> predicate) {
        requireNonNull(predicate);
        filteredCards.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager2)) {
            return false;
        }

        ModelManager2 otherModelManager = (ModelManager2) other;
        return deck.equals(otherModelManager.deck)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredCards.equals(otherModelManager.filteredCards);
    }

}