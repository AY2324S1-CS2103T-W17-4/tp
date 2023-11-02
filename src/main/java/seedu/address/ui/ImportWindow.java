package seedu.address.ui;

import java.io.*;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.MainApp;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.parser.DeckParser;
import seedu.address.storage.Storage;

/**
 * Controller for a help page
 */
public class ImportWindow extends UiPart<Stage> {

    private static String WarningData = "Warning! Importing data will close app for best experience";
    private static final Logger logger = LogsCenter.getLogger(ImportWindow.class);
    private static final String FXML = "ImportWindow.fxml";

    @FXML
    private Button importButton;

    @FXML
    private Label WarningMessage;

    @FXML
    private TextArea inputTextArea;
    @FXML
    private Stage stage;
    @FXML
    private Stage primaryStage;
    /**
     * Creates a new ImportWindow.
     *
     * @param root Stage to use as the root of the ImportWindow.
     */
    public ImportWindow(Stage root, Stage primaryStage) {
        super(FXML, root);
        WarningMessage.setText(WarningData);
        this.stage = root;
        this.primaryStage = primaryStage;
    }

    /**
     * Creates a new ImportWindow.
     */
    public ImportWindow(Stage primaryStage) {
        this(new Stage(), primaryStage);
    }

    /**
     * Shows the help window.
     * @throws IllegalStateException
     *     <ul>
     *         <li>
     *             if this method is called on a thread other than the JavaFX Application Thread.
     *         </li>
     *         <li>
     *             if this method is called during animation or layout processing.
     *         </li>
     *         <li>
     *             if this method is called on the primary stage.
     *         </li>
     *         <li>
     *             if {@code dialogStage} is already showing.
     *         </li>
     *     </ul>
     */
    public void show() {
        logger.fine("Showing import page about the application.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }
    @FXML
    private void importData() {
        String contentToWrite = inputTextArea.getText();

        // Specify the path to the data/deck.json file
        String filePath = "data/deck.json";

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(contentToWrite);
            writer.close();
            stage.close();
            primaryStage.close();
        } catch (IOException e) {
            logger.info("Error writing content to deck.json: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
