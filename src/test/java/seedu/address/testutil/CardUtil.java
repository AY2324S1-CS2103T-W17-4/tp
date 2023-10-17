package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.model.person.Card;

/**
 * A utility class for Card.
 */
public class CardUtil {

    /**
     * Returns an add command string for adding the {@code Card}.
     */
    public static String getAddCommand(Card card) {
        return AddCommand.COMMAND_WORD + " " + getCardDetails(card);
    }

    /**
     * Returns the part of command string for the given {@code Card}'s details.
     */
    public static String getCardDetails(Card card) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + card.getQuestion().question + " ");
        sb.append(PREFIX_ANSWER + card.getAnswer().answer + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditCardDescriptor}'s details.
     */
    public static String getEditCardDescriptorDetails(EditCardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getQuestion().ifPresent(name -> sb.append(PREFIX_QUESTION).append(name.question).append(" "));
        descriptor.getAnswer().ifPresent(name -> sb.append(PREFIX_ANSWER).append(name.answer).append(" "));
        return sb.toString();
    }
}
