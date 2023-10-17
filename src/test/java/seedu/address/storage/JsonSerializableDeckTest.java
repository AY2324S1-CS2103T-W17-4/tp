package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.Deck;
import seedu.address.testutil.TypicalCards;

public class JsonSerializableDeckTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableDeckTest");
    private static final Path TYPICAL_CARD_FILE = TEST_DATA_FOLDER.resolve("typicalCardDeck.json");
    private static final Path INVALID_CARD_FILE = TEST_DATA_FOLDER.resolve("invalidCardDeck.json");
    private static final Path DUPLICATE_CARD_FILE = TEST_DATA_FOLDER.resolve("duplicateCardDeck.json");

    @Test
    public void toModelType_typicalCardsFile_success() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(TYPICAL_CARD_FILE,
                JsonSerializableDeck.class).get();
        Deck deckFromFile = dataFromFile.toModelType();
        Deck typicalCardsDeck = TypicalCards.getTypicalDeck();
        assertEquals(deckFromFile, typicalCardsDeck);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(INVALID_CARD_FILE,
                JsonSerializableDeck.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateCards_throwsIllegalValueException() throws Exception {
        JsonSerializableDeck dataFromFile = JsonUtil.readJsonFile(DUPLICATE_CARD_FILE,
                JsonSerializableDeck.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableDeck.MESSAGE_DUPLICATE_CARD,
                dataFromFile::toModelType);
    }

}
