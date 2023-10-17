package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QuestionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Question(null));
    }

    @Test
    public void constructor_invalidQuestion_throwsIllegalArgumentException() {
        String invalidQuestion = "";
        assertThrows(IllegalArgumentException.class, () -> new Question(invalidQuestion));
    }

    @Test
    public void isValidQuestion() {
        // null question
        assertThrows(NullPointerException.class, () -> Question.isValidQuestion(null));

        // invalid question
        assertFalse(Question.isValidQuestion("")); // empty string
        assertFalse(Question.isValidQuestion(" ")); // spaces only
        assertFalse(Question.isValidQuestion("^")); // only disallowed characters
        assertFalse(Question.isValidQuestion("Name 2 types of UML diagrams~")); // contains disallowed characters

        // valid question
        assertTrue(Question.isValidQuestion("Craft the truth table for A and B")); // alphabets only
        assertTrue(Question.isValidQuestion("12345")); // numbers only
        assertTrue(Question.isValidQuestion(
                "What are the first 6 bits for R-format instructions?")); // long questions
    }

    @Test
    public void equals() {
        Question question = new Question("Valid Question");

        // same values -> returns true
        assertTrue(question.equals(new Question("Valid Question")));

        // same object -> returns true
        assertTrue(question.equals(question));

        // null -> returns false
        assertFalse(question.equals(null));

        // different types -> returns false
        assertFalse(question.equals(5.0f));

        // different values -> returns false
        assertFalse(question.equals(new Question("Other Valid Question")));
    }
}
