package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Card's answer in lesSON.
 */
public class Answer {
    public static final String MESSAGE_CONSTRAINTS =
            "Answers should only contain alphanumeric characters, some special characters "
                    + "and spaces, and it should not be blank";
    public static final String VALIDATION_REGEX = "^(?=.*[\\p{Alnum}.,?'():-])\\s*[\\p{Alnum}.,?'(): -]+$";

    public final String answer;

    /**
     * Constructs a {@code Answer}.
     *
     * @param answer A valid answer.
     */
    public Answer(String answer) {
        requireNonNull(answer);
        checkArgument(isValidAnswer(answer), MESSAGE_CONSTRAINTS);
        this.answer = answer;
    }

    /**
     * Returns true if a given string is a valid answer.
     */
    public static boolean isValidAnswer(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return answer;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Answer)) {
            return false;
        }

        Answer otherAnswer = (Answer) other;
        return answer.equals(otherAnswer.answer);
    }

    @Override
    public int hashCode() {
        return answer.hashCode();
    }

}
