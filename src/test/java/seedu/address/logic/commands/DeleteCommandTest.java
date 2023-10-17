package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Card;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommand}.
 */
public class DeleteCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Card cardToDelete = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_CARD);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CARD_SUCCESS,
                Messages.format(cardToDelete));

        ModelManager expectedModel = new ModelManager(model.getDeck(), new UserPrefs());
        expectedModel.deleteCard(cardToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        DeleteCommand deleteCommand = new DeleteCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteCommand(INDEX_FIRST_CARD);
        DeleteCommand deleteSecondCommand = new DeleteCommand(INDEX_SECOND_CARD);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteCommand(INDEX_FIRST_CARD);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Card -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
        String expected = DeleteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoCard(Model model) {
        model.updateFilteredCardList(p -> false);

        assertTrue(model.getFilteredCardList().isEmpty());
    }
}
