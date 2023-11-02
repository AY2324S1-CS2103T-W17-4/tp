package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exceptions.RandomIndexNotInitialisedException;

public class RandomCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_command_setsRandomIndexCorrectly() {
        RandomCommand randomCommand = new RandomCommand();
        try {
            randomCommand.execute(model);
        } catch (CommandException c) {
            fail();
        }

        try {
            model.getRandomIndex();
        } catch (RandomIndexNotInitialisedException r) {
            fail();
        }
    }

    @Test
    public void equals() {
        final RandomCommand standardCommand = new RandomCommand();

        // same values -> returns false because every RandomCommand has a random outcome
        assertFalse(standardCommand.equals(new RandomCommand()));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));
    }

}
