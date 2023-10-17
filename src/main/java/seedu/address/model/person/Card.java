package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Card in lesSON.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Card {
    private final Question question;
    private final Answer answer;
    private String difficulty;

    /**
     * Every field must be present and not null.
     */
    public Card(Question question, Answer answer, String difficulty) {
        requireAllNonNull(question, answer);
        this.question = question;
        this.answer = answer;
        this.difficulty = difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return this.difficulty;
    }

    public Question getQuestion() {
        return question;
    }

    public Answer getAnswer() {
        return answer;
    }

    /**
     * Returns true if both cards have the same question.
     * This defines a weaker notion of equality between two cards.
     */
    public boolean isSameCard(Card otherCard) {
        if (otherCard == this) {
            return true;
        }

        return otherCard != null
                && otherCard.getQuestion().equals(getQuestion());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Card)) {
            return false;
        }

        Card otherCard = (Card) other;
        return question.equals(otherCard.question)
                && answer.equals(otherCard.answer);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(question, answer);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("question", question)
                .add("answer", answer)
                .toString();
    }

    public String questiontoString() {
        return "Question: " + this.getQuestion().toString();
    }


    public String answertoString() {
        return "Answer: " + this.getAnswer().toString();
    }
}
