package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS1101S;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditCardDescriptor;
import seedu.address.testutil.EditCardDescriptorBuilder;

public class EditCardDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCardDescriptor descriptorWithSameValues = new EditCardDescriptor(DESC_CS2100);
        assertTrue(DESC_CS2100.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_CS2100.equals(DESC_CS2100));

        // null -> returns false
        assertFalse(DESC_CS2100.equals(null));

        // different types -> returns false
        assertFalse(DESC_CS2100.equals(5));

        // different values -> returns false
        assertFalse(DESC_CS2100.equals(DESC_CS1101S));

        // different question -> returns false
        EditCardDescriptor editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100)
                .withQuestion(VALID_QUESTION_CS1101S).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));
        // different phone -> returns false
        editedCS2100 = new EditCardDescriptorBuilder(DESC_CS2100).withAnswer(VALID_ANSWER_CS1101S).build();
        assertFalse(DESC_CS2100.equals(editedCS2100));

    }

    @Test
    public void toStringMethod() {
        EditCardDescriptor editCardDescriptor = new EditCardDescriptor();
        String expected = EditCardDescriptor.class.getCanonicalName() + "{question="
                + editCardDescriptor.getQuestion().orElse(null) + ", answer="
                + editCardDescriptor.getAnswer().orElse(null) + "}";
        assertEquals(expected, editCardDescriptor.toString());
    }
}
