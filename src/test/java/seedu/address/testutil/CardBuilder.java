package seedu.address.testutil;

import seedu.address.model.person.Answer;
import seedu.address.model.person.Card;
import seedu.address.model.person.Question;

/**
 * A utility class to help with building Person objects.
 */
public class CardBuilder {

    public static final String DEFAULT_QUESTION = "What is the opcode for R-format instructions?";
    public static final String DEFAULT_ANSWER = "0";

    private Question question;
    private Answer answer;

    /**
     * Creates a {@code CardBuilder} with the default details.
     */
    public CardBuilder() {
        question = new Question(DEFAULT_QUESTION);
        answer = new Answer(DEFAULT_ANSWER);
    }

    /**
     * Initializes the CardBuilder with the data of {@code cardToCopy}.
     */
    public CardBuilder(Card cardToCopy) {
        question = cardToCopy.getQuestion();
        answer = cardToCopy.getAnswer();
    }

    /**
     * Sets the {@code Question} of the {@code Card} that we are building.
     */
    public CardBuilder withQuestion(String question) {
        this.question = new Question(question);
        return this;
    }

    /**
     * Sets the {@code Answer} of the {@code Card} that we are building.
     */
    public CardBuilder withAnswer(String answer) {
        this.answer = new Answer(answer);
        return this;
    }

    public Card build() {
        return new Card(question, answer, "new");
    }

}
