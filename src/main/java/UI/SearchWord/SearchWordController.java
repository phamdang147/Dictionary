package UI.SearchWord;

import Commandline.Dictionary;
import Commandline.Word;
import UI.Main.Speech;
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

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchWordController implements Initializable {
    @FXML
    TextField textSearch;
    @FXML
    TextArea textMean;
    @FXML
    ListView<String> listView;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    @FXML
    Button btnSearch;
    public void searchWord(ActionEvent event) {
        boolean noSuchWord = true;
        for (Word word : listWord) {
            if(textSearch.getText().equals(word.getWordTarget())) {
                textMean.setText(word.getWordExplain());
                noSuchWord = false;
                break;
            }
        }
        if (noSuchWord) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Search Word");
            alert.setHeaderText(null);
            alert.setContentText("The word was not found in the dictionary!");
            alert.showAndWait();
        }
    }

    @FXML
    Button btnSpeech;
    public void speechWord(ActionEvent event) {
        Speech speechWord = new Speech("kevin16");
        speechWord.say(textSearch.getText());
    }

    @FXML
    Button btnAddWord;
    public void addWord(ActionEvent event) throws IOException {
        Stage stageAddWord = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddWord.fxml"));
        Parent addWordParent = loader.load();
        Scene scene = new Scene(addWordParent);
        stageAddWord.setScene(scene);
    }

    @FXML
    Button btnReplaceWord;
    public void replaceWord(ActionEvent event) throws IOException {
        if (textSearch.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Replace Word");
            alert.setHeaderText(null);
            alert.setContentText("Please select the word you want to replace!");
            alert.showAndWait();
        } else {
            boolean noSuchWord = true;
            for (Word word : listWord) {
                if (word.getWordTarget().equals(textSearch.getText())) {
                    noSuchWord = false;
                    break;
                }
            }
            if (noSuchWord) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Replace Word");
                alert.setHeaderText(null);
                alert.setContentText("This word is not in the dictionary!");
                alert.showAndWait();
            } else {
                Stage stageReplaceWord = (Stage) ((Node) event.getSource()).getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/ReplaceWord.fxml"));
                Parent replaceWordParent = loader.load();
                Scene scene = new Scene(replaceWordParent);
                ReplaceWordController controller = loader.getController();
                Word selected = new Word(textSearch.getText(), textMean.getText());
                controller.setWord(selected);
                stageReplaceWord.setScene(scene);
            }
        }
    }

    @FXML
    Button btnDeleteWord;
    public void deleteWord(ActionEvent event) throws IOException {
        Stage stageDeleteWord = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/DeleteWord.fxml"));
        Parent deleteWordParent = loader.load();
        Scene scene = new Scene(deleteWordParent);
        DeleteWordController controller = loader.getController();
        controller.setWord(textSearch.getText());
        stageDeleteWord.setScene(scene);
    }

    @FXML
    Button btnBackMain;
    public void backMain(ActionEvent event) throws IOException {
        Stage stageMain = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Main.fxml"));
        Parent mainParent = loader.load();
        Scene scene = new Scene(mainParent);
        stageMain.setScene(scene);
    }


    @Override
    public void initialize(URL location, ResourceBundle resource) {
        try {
            dictionary.insertFromFile(listWord);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                    textMean.setText(word.getWordExplain());
                    break;
                }
            }
        });

        //Bat su kien khi nhap vao ky tu Enter
        textSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                boolean noSuchWord = true;
                for (Word word : listWord) {
                    if(textSearch.getText().equals(word.getWordTarget())) {
                        textMean.setText(word.getWordExplain());
                        noSuchWord = false;
                        break;
                    }
                }
                if (noSuchWord) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Search Word");
                    alert.setHeaderText(null);
                    alert.setContentText("The word was not found in the dictionary!");
                    alert.showAndWait();
                }
            }
        });
    }
}
