package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
import seedu.address.model.card.Card;


/**
 * DisplayCard class to showcase the question and the answer
 */
public class DisplayCard extends UiPart<Region> {
    private static final String FXML = "DisplayListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */
    public final Card card;

    @javafx.fxml.FXML
    private HBox cardPane;
    @FXML
    private Label id;

    @FXML
    private TextFlow question;

    @FXML
    private FlowPane tags;

    @FXML
    private Label dueDate;

    @FXML
    private Label solveCount;

    /**
     * Creates a {@code DisplayCard} with the given {@code card} and index to display.
     */

    public DisplayCard(Card card, int displayedIndex) {
        super(FXML);
        this.card = card;
        id.setText(displayedIndex + ". ");
        MarkdownHelper.setTextWithMarkdown(question, card.getQuestion().question, Color.WHITE);

        dueDate.setText("Due: " + card.getNextPracticeDate().getDisplayName());
        card.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        solveCount.setText("Solved: " + card.getSolveCount().toString());
    }
}
