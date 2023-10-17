package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCards.CS1101S;
import static seedu.address.testutil.TypicalCards.CS1231S;
import static seedu.address.testutil.TypicalCards.CS2100;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CardBuilder;

public class CardTest {
    @Test
    public void isSameCard() {
        // same object -> returns true
        assertTrue(CS2100.isSameCard(CS2100));

        // null -> returns false
        assertFalse(CS2100.isSameCard(null));

        // same question, different answer -> returns true
        // to update with CommandTestUtil
        Card editedCS2100 = new CardBuilder(CS2100).withAnswer("000000").build();
        assertTrue(CS2100.isSameCard(editedCS2100));

        // different question, same answer -> returns false
        editedCS2100 = new CardBuilder(CS2100).withQuestion("What is the opcode for R-format instructions").build();
        assertFalse(CS2100.isSameCard(editedCS2100));

        // name differs in case, all other attributes same -> returns false
        Card editedCS1231S = new CardBuilder(CS1231S).withQuestion(
                CS1231S.getQuestion().toString().toLowerCase()).build();
        assertFalse(CS1231S.isSameCard(editedCS1231S));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Card cs1101sCopy = new CardBuilder(CS1101S).build();
        assertTrue(CS1101S.equals(cs1101sCopy));

        // same object -> returns true
        assertTrue(CS1101S.equals(CS1101S));

        // null -> returns false
        assertFalse(CS1101S.equals(null));

        // different type -> returns false
        assertFalse(CS1101S.equals(5));

        // different card -> returns false
        assertFalse(CS1101S.equals(CS2100));

        // different question -> returns false
        Card editedAlice = new CardBuilder(CS1101S).withQuestion(CS2100.getQuestion().toString()).build();
        assertFalse(CS1101S.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Card.class.getCanonicalName() + "{question=" + CS1101S.getQuestion()
                + ", answer=" + CS1101S.getAnswer() + "}";
        assertEquals(expected, CS1101S.toString());
    }
}
