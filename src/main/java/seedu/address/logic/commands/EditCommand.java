package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_CARDS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Card;
import seedu.address.model.person.Question;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the card identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_QUESTION + "QUESTION] "
            + "[" + PREFIX_ANSWER + "ANSWER] ";

    public static final String MESSAGE_EDIT_CARD_SUCCESS = "Edited Card: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_CARD = "This card already exists in the address book.";

    private final Index index;
    private final EditCardDescriptor editCardDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editCardDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditCardDescriptor editCardDescriptor) {
        requireNonNull(index);
        requireNonNull(editCardDescriptor);

        this.index = index;
        this.editCardDescriptor = new EditCardDescriptor(editCardDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Card> lastShownList = model.getFilteredCardList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CARD_DISPLAYED_INDEX);
        }

        Card personToEdit = lastShownList.get(index.getZeroBased());
        Card editedCard = createEditedCard(personToEdit, editCardDescriptor);

        if (!personToEdit.isSameCard(editedCard) && model.hasCard(editedCard)) {
            throw new CommandException(MESSAGE_DUPLICATE_CARD);
        }

        model.setCard(personToEdit, editedCard);
        model.updateFilteredCardList(PREDICATE_SHOW_ALL_CARDS);
        return new CommandResult(String.format(MESSAGE_EDIT_CARD_SUCCESS, Messages.format(editedCard)));
    }

    /**
     * Creates and returns a {@code Card} with the details of {@code personToEdit}
     * edited with {@code editCardDescriptor}.
     */
    private static Card createEditedCard(Card cardToEdit, EditCardDescriptor editCardDescriptor) {
        assert cardToEdit != null;

        Question updatedQuestion = editCardDescriptor.getQuestion().orElse(cardToEdit.getQuestion());
        Answer updatedAnswer = editCardDescriptor.getAnswer().orElse(cardToEdit.getAnswer());

        return new Card(updatedQuestion, updatedAnswer, "new");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editCardDescriptor.equals(otherEditCommand.editCardDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editCardDescriptor", editCardDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditCardDescriptor {
        private Question question;
        private Answer answer;

        public EditCardDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditCardDescriptor(EditCardDescriptor toCopy) {
            setQuestion(toCopy.question);
            setAnswer(toCopy.answer);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(question, answer);
        }

        public void setQuestion(Question question) {
            this.question = question;
        }

        public Optional<Question> getQuestion() {
            return Optional.ofNullable(question);
        }

        public void setAnswer(Answer answer) {
            this.answer = answer;
        }

        public Optional<Answer> getAnswer() {
            return Optional.ofNullable(answer);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCardDescriptor)) {
                return false;
            }

            EditCardDescriptor otherEditCardDescriptor = (EditCardDescriptor) other;
            return Objects.equals(question, otherEditCardDescriptor.question)
                    && Objects.equals(answer, otherEditCardDescriptor.answer);

        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("question", question)
                    .add("answer", answer)
                    .toString();
        }
    }

}
