// GUI for the SpellChecker program

package spellchecker;
import java.io.File;
import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class GUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public Button selectDictButton = new Button("Select");
    public Button selectFileButton = new Button("Select");
    public Button checkFileButton = new Button("Check");
    public Button enterWordButton = new Button("Enter a Word");
    public Button chooseFileButton = new Button("Choose a File");
    public Button exitButton = new Button("Exit");
    public Button checkWordButton = new Button("Check");
    public TextField dictTextField = new TextField();
    public TextField userFileTextField = new TextField();
    public TextField userWordTextField = new TextField();
    public Label dictLabel = new Label("That file does not exist.");
    public Label userFileLabel = new Label("That file does not exist.");
    public Label userWordLabel = new Label();
    public Alert fatalAlert = new Alert(AlertType.INFORMATION);
    public Alert exitAlert = new Alert(AlertType.INFORMATION);
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane mainGrid = new GridPane();
        HBox hbox = new HBox();
        HBox hbox2 = new HBox();
        hbox.setPadding(new Insets(0, 0, 0, 0));
        hbox.setSpacing(10);
        hbox.getChildren().addAll(enterWordButton, chooseFileButton);
        hbox2.setPadding(new Insets(0, 0, 0, 0));
        hbox2.setSpacing(10);
        hbox2.getChildren().addAll(selectFileButton, checkFileButton);
        mainGrid.setHgap(7);
        mainGrid.setVgap(7);
        mainGrid.setPadding(new Insets(10, 10, 10, 10));
        mainGrid.add(new Label("Welcome! This program will check any "
                             + "text file for spelling errors.\n"
                             + "You can also check any word "
                             + "individually.\nPlease enter the dictionary "
                             + "file name and select an option "
                             + "below.\nNOTE: Roman numerals, proper names "
                             + "and unique words\nnot found in the "
                             + "dictionary will be treated as misspellings."
                             + ""), 0, 0);
        mainGrid.add(hbox, 0, 2);
        mainGrid.add(dictLabel, 1, 2);
        mainGrid.add(new Label("Type the name of the dictionary file (include"
                             + ".txt):"), 0, 3);
        mainGrid.add(dictTextField, 1, 3);
        mainGrid.add(selectDictButton, 0, 4);
        mainGrid.add(userFileLabel, 1, 4);
        mainGrid.add(new Label("Type the name of the file to spell check "
                             + "(include .txt)\nand press \"check\":"), 0, 5);
        mainGrid.add(userFileTextField, 1, 5);
        mainGrid.add(hbox2, 0, 6);
        mainGrid.add(userWordLabel, 1, 6);
        mainGrid.add(new Label("Type a single word to spell check: "), 0, 7);
        mainGrid.add(userWordTextField, 1, 7);
        mainGrid.add(checkWordButton, 0, 8);
        mainGrid.add(exitButton, 0, 9);
        
        fatalAlert.setTitle("Fatal Error!");
        fatalAlert.setHeaderText("A fatal system error has occurred!\nPress"
                               + "\"OK\" to terminate the program.");
        exitAlert.setTitle("Exit Program");
        exitAlert.setHeaderText("Thank you for using the spell checker. "
                              + "Goodbye!");
        
        dictLabel.setVisible(false);
        userFileLabel.setVisible(false);
        userWordLabel.setVisible(false);
        selectDictButton.setDisable(false);
        selectFileButton.setDisable(true);
        checkWordButton.setDisable(true);
        checkFileButton.setDisable(true);
        dictTextField.setDisable(false);
        userFileTextField.setDisable(true);
        userWordTextField.setDisable(true);
        chooseFileButton.setDisable(true);
        enterWordButton.setDisable(true);
        
        Scene mainScene = new Scene(mainGrid, 550, 390);
        primaryStage.setTitle("Spell Checker Using a HashTable");
        primaryStage.setScene(mainScene);
        primaryStage.setResizable(true);
        primaryStage.show();
        
        loadDictionaryFile();
        
        enterWordButton.setOnAction((ActionEvent event) -> {
            chooseFileButton.setDisable(true);
            enterWordButton.setDisable(true);
            checkWordButton.setDisable(false);
            userWordTextField.setDisable(false);
            checkUserWord();
        });
        chooseFileButton.setOnAction((ActionEvent event) -> {
            enterWordButton.setDisable(true);
            chooseFileButton.setDisable(true);
            userFileTextField.setDisable(false);
            selectFileButton.setDisable(false);
            checkUserFileLocation();
        });
        exitButton.setOnAction((ActionEvent event) -> {
            exitAlert.showAndWait();
            System.exit(0);
        });
    }
    public void loadDictionaryFile() {
        selectDictButton.setOnAction((ActionEvent event) -> {
            if (!new File(dictTextField.getText()).exists()) {
                loadDictionaryFile();
                dictLabel.setVisible(true);
                dictLabel.setStyle("-fx-text-fill: red");
            } else {
                try {
                    SpellChecker.loadDictionary(dictTextField.getText());
                    dictLabel.setVisible(true);
                    dictLabel.setText("Dictionary loaded successfully.");
                    dictLabel.setStyle("-fx-text-fill: blue");
                    dictTextField.setDisable(true);
                    selectDictButton.setDisable(true);
                    chooseFileButton.setDisable(false);
                    enterWordButton.setDisable(false);
                } catch (FileNotFoundException ex) {
                    fatalAlert.showAndWait();
                    System.exit(0);
                }
            }
        });
    }
    public void checkUserFileLocation() {
        selectFileButton.setOnAction((ActionEvent event) -> {
            if (!new File(userFileTextField.getText()).exists()) {
                checkUserFileLocation();
                userFileLabel.setVisible(true);
                userFileLabel.setStyle("-fx-text-fill: red");
            } else {
                userFileLabel.setVisible(true);
                userFileLabel.setText("File successfully selected.");
                userFileLabel.setStyle("-fx-text-fill: blue");
                userFileTextField.setDisable(true);
                selectFileButton.setDisable(true);
                checkFileButton.setDisable(false);
                scanUserFile(userFileTextField.getText());
            }
        });
    }
    public void checkUserWord() {
        checkWordButton.setOnAction((ActionEvent event) -> {
            userWordLabel.setVisible(true);
            if (!HashTable.Contains(userWordTextField.getText())) {
                userWordLabel.setText("That is an incorrect spelling.");
                userWordLabel.setStyle("-fx-text-fill: red");
            } else {
                userWordLabel.setText("That is a correct spelling.");
                userWordLabel.setStyle("-fx-text-fill: blue");
            }
            checkUserWord();
        });
    }
    public void scanUserFile(String userFile) {
        checkFileButton.setOnAction((ActionEvent event) -> {
            try {
                checkFileButton.setDisable(true);
                SpellChecker.scanFile(userFile);
            } catch (FileNotFoundException ex) {
                fatalAlert.showAndWait();
                System.exit(0);
            }
        });
    }
}
