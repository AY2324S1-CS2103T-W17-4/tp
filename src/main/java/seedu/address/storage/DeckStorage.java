package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyDeck;

/**
 * Represents a storage for {@link seedu.address.model.Deck}.
 */
public interface DeckStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getDeckFilePath();

    /**
     * Returns AddressBook data as a {@link ReadOnlyAddressBook}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyDeck> readDeck() throws DataLoadingException;

    /**
     * @see #getDeckFilePath()
     */
    Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyAddressBook} to the storage.
     * @param deck cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveDeck(ReadOnlyDeck deck) throws IOException;

    /**
     * @see #saveDeck(ReadOnlyDeck)
     */
    void saveDeck(ReadOnlyDeck deck, Path filePath) throws IOException;

}
