package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Card;
import seedu.address.testutil.CardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalDeck(), new UserPrefs());
    }

    @Test
    public void execute_newCard_success() {
        Card validCard = new CardBuilder().build();

        Model expectedModel = new ModelManager(model.getDeck(), new UserPrefs());
        expectedModel.addCard(validCard);

        assertCommandSuccess(new AddCommand(validCard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validCard)),
                expectedModel);
    }

    @Test
    public void execute_duplicateCard_throwsCommandException() {
        Card cardInList = model.getDeck().getCardList().get(0);
        assertCommandFailure(new AddCommand(cardInList), model,
                AddCommand.MESSAGE_DUPLICATE_CARD);
    }

}
