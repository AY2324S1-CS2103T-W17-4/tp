package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Card's question in lesSON.
 */
public class Question {
    public static final String MESSAGE_CONSTRAINTS =
            "Questions should only contain alphanumeric characters, some special characters "
                    + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "^(?=.*[\\p{Alnum}.,?'():-])\\s*[\\p{Alnum}.,?'(): -]+$";

    public final String question;

    /**
     * Constructs a {@code Question}.
     *
     * @param question A valid question.
     */
    public Question(String question) {
        requireNonNull(question);
        checkArgument(isValidQuestion(question), MESSAGE_CONSTRAINTS);
        this.question = question;
    }

    /**
     * Returns true if a given string is a valid question.
     */
    public static boolean isValidQuestion(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return question;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Question)) {
            return false;
        }

        Question otherQuestion = (Question) other;
        return question.equals(otherQuestion.question);
    }

    @Override
    public int hashCode() {
        return question.hashCode();
    }
}
