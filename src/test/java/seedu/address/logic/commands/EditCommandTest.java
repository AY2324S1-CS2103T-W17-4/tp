package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.model.Deck;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Card;
import seedu.address.testutil.CardBuilder;
import seedu.address.testutil.EditCardDescriptorBuilder;


/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Card editedCard = new CardBuilder().build();
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(editedCard).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CARD, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CARD_SUCCESS, Messages.format(editedCard));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.setCard(model.getFilteredCardList().get(0), editedCard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCard = Index.fromOneBased(model.getFilteredCardList().size());
        Card lastCard = model.getFilteredCardList().get(indexLastCard.getZeroBased());

        CardBuilder cardInList = new CardBuilder(lastCard);
        Card editedCard = cardInList.withQuestion(VALID_QUESTION_CS2100)
                .withAnswer(VALID_ANSWER_CS2100).build();

        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS2100)
                .withAnswer(VALID_ANSWER_CS2100).build();
        EditCommand editCommand = new EditCommand(indexLastCard, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CARD_SUCCESS, Messages.format(editedCard));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());
        expectedModel.setCard(lastCard, editedCard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCommand editCommand = new EditCommand(INDEX_FIRST_CARD, new EditCardDescriptor());
        Card editedCard = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_CARD_SUCCESS, Messages.format(editedCard));

        Model expectedModel = new ModelManager(new Deck(model.getDeck()), new UserPrefs());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCardUnfilteredList_failure() {
        Card firstCard = model.getFilteredCardList().get(INDEX_FIRST_CARD.getZeroBased());
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder(firstCard).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_CARD, descriptor);

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_CARD);
    }

    @Test
    public void execute_invalidCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCardList().size() + 1);
        EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS2100).build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void equals() {
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_CARD, DESC_CS2100);

        // same values -> returns true
        EditCardDescriptor copyDescriptor = new EditCardDescriptor(DESC_CS1101S);
        System.out.println(copyDescriptor);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));


        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_CARD, DESC_CS2100)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_CARD, DESC_CS1101S)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        EditCommand editCommand = new EditCommand(index, editCardDescriptor);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", editCardDescriptor="
                + editCardDescriptor + "}";
        assertEquals(expected, editCommand.toString());
    }

}
