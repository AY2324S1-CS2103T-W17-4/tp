package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.model.Deck;
import seedu.address.model.ReadOnlyDeck;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonDeckStorage deckStorage = new JsonDeckStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(deckStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }
    @Test
    public void deckReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonDeckStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonDeckStorageTest} class.
         */
        Deck original = getTypicalDeck();
        storageManager.saveDeck(original);
        ReadOnlyDeck retrieved = storageManager.readDeck().get();
        assertEquals(original, new Deck(retrieved));
    }

    @Test
    public void getDeckFilePath() {
        assertNotNull(storageManager.getDeckFilePath());
    }

}
