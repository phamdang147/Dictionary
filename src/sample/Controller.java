package sample;

import DictionaryCommandline.Dictionary;
import DictionaryCommandline.Word;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TextArea textMeaning;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private Button buttonVoice;
    @FXML
    private ListView<String> listView;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    /**
     * button tra tu.
     */
    public void searchWord() {
        boolean noSuchWord = true;
        String target = textSearch.getText();
        for (Word word : listWord) {
            if(target.equals(word.getWordTarget())) {
                textMeaning.setText(word.getWordExplain());
                noSuchWord = false;
                break;
            }
        }
        if (noSuchWord) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Search Word");
            alert.setHeaderText(null);
            alert.setContentText("Not found word!");
            alert.showAndWait();
        }
    }

    /**
     * button phat am thanh.
     */
    public void voiceWord() {
        if (textSearch.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Speech Word");
            alert.setHeaderText(null);
            alert.setContentText("Not found Word!");
            alert.showAndWait();
        }
        Voice voiceWord = new Voice("kevin16");
        voiceWord.say(textSearch.getText());
    }

    /**
     * button them tu.
     */
    public void addWord(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addWord.fxml"));
        Parent addWordParent = loader.load();
        Scene scene = new Scene(addWordParent);
        stage.setScene(scene);
    }

    /**
     * button sua tu.
     */
    public void replaceWord(ActionEvent event) throws IOException {
        if (textSearch.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Replace Word");
            alert.setHeaderText(null);
            alert.setContentText("Please select the word you want to replace!");
            alert.showAndWait();
        } else {
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("replaceWord.fxml"));
            Parent replaceWordParent = loader.load();
            Scene scene = new Scene(replaceWordParent);
            replaceWordController controller = loader.getController();
            Word selected = new Word(textSearch.getText(), textMeaning.getText());
            controller.setWord(selected);
            stage.setScene(scene);
        }
    }

    /**
     * button xoa tu.
     */
    public void deleteWord() {
        if (textSearch.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Word");
            alert.setHeaderText(null);
            alert.setContentText("Please select the word you want to delete!");
            alert.showAndWait();
        }
        int i = 0;
        for (Word word : listWord) {
            if(textSearch.getText().equals(word.getWordTarget())) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Delete Word");
                alert.setHeaderText("Are you sure you want to delete this word from the dictionary?");
                alert.setContentText(textSearch.getText());

                Optional<ButtonType> option = alert.showAndWait();
                if (option.get() == ButtonType.OK) {
                    listWord.remove(i);
                    listView.getItems().remove(textSearch.getText());
                    textSearch.clear();
                    textMeaning.clear();
                    break;
                } else if (option.get() == ButtonType.CANCEL) {
                    break;
                } else {
                    break;
                }
            }
            i++;
        }
        dictionary.dictionaryExportToFile(listWord);
    }

    /**
     * listView.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.insertFromFile(listWord);
        ArrayList<String> listWordTarget = new ArrayList<>();
        for (Word allWords : listWord) {
            listWordTarget.add(allWords.getWordTarget());
        }
        listView.getItems().clear();
        listView.getItems().addAll(listWordTarget);

        //Loc tu trong listView
        textSearch.setOnKeyReleased(event -> {
            String item = textSearch.getText();
            ArrayList<String> match = new ArrayList<>(listWordTarget);

            match.removeIf(s -> !s.startsWith(item));
            listView.getItems().clear();
            listView.getItems().addAll(match);
        });

        //Bat su kien trong textField
        listView.setOnMouseClicked(event -> {
            String item = listView.getSelectionModel().getSelectedItem();
            for (Word word : listWord) {
                if(item.equals(word.getWordTarget())) {
                    textSearch.setText(item);
                    textMeaning.setText(word.getWordExplain());
                    break;
                }
            }
        });

        //Bat su kien khi nhap vao ky tu Enter
        textSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                String target = textSearch.getText();
                for (Word word : listWord) {
                    if(target.equals(word.getWordTarget())) {
                        textMeaning.setText(word.getWordExplain());
                        break;
                    }
                }
            }
        });
    }
}
