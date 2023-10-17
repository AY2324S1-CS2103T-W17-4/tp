package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand2;
import seedu.address.model.person.Answer;
import seedu.address.model.person.Card;
import seedu.address.model.person.Question;

/**
 * A utility class to help with building EditCardDescriptor objects.
 */
public class EditCardDescriptorBuilder {

    private EditCommand2.EditCardDescriptor descriptor;

    public EditCardDescriptorBuilder() {
        descriptor = new EditCommand2.EditCardDescriptor();
    }

    public EditCardDescriptorBuilder(EditCommand2.EditCardDescriptor descriptor) {
        this.descriptor = new EditCommand2.EditCardDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditCardDescriptor} with fields containing {@code Card}'s details
     */
    public EditCardDescriptorBuilder(Card card) {
        descriptor = new EditCommand2.EditCardDescriptor();
        descriptor.setQuestion(card.getQuestion());
        descriptor.setAnswer(card.getAnswer());
    }

    /**
     * Sets the {@code Name} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withQuestion(String question) {
        descriptor.setQuestion(new Question(question));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditCardDescriptor} that we are building.
     */
    public EditCardDescriptorBuilder withAnswer(String answer) {
        descriptor.setAnswer(new Answer(answer));
        return this;
    }
    public EditCommand2.EditCardDescriptor build() {
        return descriptor;
    }
}