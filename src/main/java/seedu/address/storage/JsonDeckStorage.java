package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyDeck;

/**
 * A class to access Deck data stored as a json file on the hard disk.
 */
public class JsonDeckStorage implements DeckStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonDeckStorage.class);

    private Path filePath;

    public JsonDeckStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getDeckFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyDeck> readDeck() throws DataLoadingException {
        return readDeck(filePath);
    }

    /**
     * Similar to {@link #readDeck()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyDeck> readDeck(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        if (!Files.exists(filePath)) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
                Files.write(filePath, "{}".getBytes()); // Create the 'deck.json' file with empty JSON content
            } catch (IOException e) {
                throw new DataLoadingException(e);
            }
        }

        // Check if the file is empty or contains only whitespace
        try {
            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            if (content.trim().isEmpty()) {
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new DataLoadingException(e);
        }

        Optional<JsonSerializableDeck> jsonDeck = JsonUtil.readJsonFile(
                filePath, JsonSerializableDeck.class);
        if (!jsonDeck.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonDeck.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }


    @Override
    public void saveDeck(ReadOnlyDeck deck) throws IOException {
        saveDeck(deck, filePath);
    }

    /**
     * Similar to {@link #saveDeck(ReadOnlyDeck)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveDeck(ReadOnlyDeck deck, Path filePath) throws IOException {
        requireNonNull(deck);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableDeck(deck), filePath);
    }

}
