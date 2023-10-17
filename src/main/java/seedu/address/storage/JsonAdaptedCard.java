package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Card;
import seedu.address.model.person.Question;

/**
 * Jackson-friendly version of {@link Card}.
 */
class JsonAdaptedCard {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String question;
    private final String answer;
    private final String difficulty;


    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedCard(@JsonProperty("question") String question, @JsonProperty("answer") String answer,
                           @JsonProperty("difficulty") String difficulty) {
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;


    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedCard(Card source) {
        question = source.getQuestion().question;
        answer = source.getAnswer().answer;
        difficulty = source.getDifficulty();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Card toModelType() throws IllegalValueException {

        if (question == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Question.class.getSimpleName()));
        }

        if (!Question.isValidQuestion(question)) {
            throw new IllegalValueException(Question.MESSAGE_CONSTRAINTS);
        }

        final Question modelQuestion = new Question(question);

        if (answer == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Answer.class.getSimpleName()));
        }

        if (!Answer.isValidAnswer(answer)) {
            throw new IllegalValueException(Answer.MESSAGE_CONSTRAINTS);
        }

        final Answer modelAnswer = new Answer(answer);

        return new Card(modelQuestion, modelAnswer, difficulty);
    }
}
