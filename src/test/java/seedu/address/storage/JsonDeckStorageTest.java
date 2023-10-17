package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.Deck;
import seedu.address.model.ReadOnlyDeck;

public class JsonDeckStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonDeckStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readDeck(null));
    }

    private java.util.Optional<ReadOnlyDeck> readDeck(String filePath) throws Exception {
        return new JsonDeckStorage(Paths.get(filePath)).readDeck(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readDeck("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readDeck("notJsonFormatDeck.json"));
    }

    @Test
    public void readDeck_invalidCardDeck_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidCardDeck.json"));
    }

    @Test
    public void readDeck_invalidAndValidCardDeck_throwDataLoadingException() {
        assertThrows(DataLoadingException.class, () -> readDeck("invalidAndValidCardDeck.json"));
    }

    @Test
    public void readAndSaveDeck_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempDeck.json");
        Deck original = getTypicalDeck();
        JsonDeckStorage jsonDeckStorage = new JsonDeckStorage(filePath);

        // Save in new file and read back
        jsonDeckStorage.saveDeck(original, filePath);
        ReadOnlyDeck readBack = jsonDeckStorage.readDeck(filePath).get();
        assertEquals(original, new Deck(readBack));

    }

    @Test
    public void saveDeck_nullDeck_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeck(null, "SomeFile.json"));
    }

    /**
     * Saves {@code Deck} at the specified {@code filePath}.
     */
    private void saveDeck(ReadOnlyDeck deck, String filePath) {
        try {
            new JsonDeckStorage(Paths.get(filePath))
                    .saveDeck(deck, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveDeck_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveDeck(new Deck(), null));
    }
}
