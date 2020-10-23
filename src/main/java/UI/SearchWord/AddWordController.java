package UI.SearchWord;

import Commandline.Dictionary;
import Commandline.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {
    @FXML
    TextField textTarget;
    @FXML
    TextArea textExplain;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    @FXML
    Button btnConfirmAddWord;
    public void confirmAddWord(ActionEvent event) {

        boolean suchWord = true;
        for (Word word : listWord) {
            if (word.getWordTarget().equals(textTarget.getText())) {
                suchWord = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Add Word");
                alert.setHeaderText("Can not add Word");
                alert.setContentText("This word is already in the dictionary!");
                alert.showAndWait();
                break;
            }
        }
        if (textTarget.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Add Word");
            alert.setHeaderText(null);
            alert.setContentText("Please insert the word you want to add!");
            alert.showAndWait();
        }  else if (suchWord) {
            int i = 0;
            for (Word word : listWord) {
                if (word.getWordTarget().compareTo(textTarget.getText()) > 0) {
                    listWord.add(i, new Word(textTarget.getText(), textExplain.getText()));
                    dictionary.dictionaryExportToFile(listWord);
                    textTarget.clear();
                    textExplain.clear();
                    break;
                }
                i += 1;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Word");
            alert.setHeaderText(null);
            alert.setContentText("Added words successfully!");
            alert.showAndWait();
        }
    }

    @FXML
    Button btnBackSearchWord;
    public void backSearchWord(ActionEvent event) throws IOException {
        Stage stageSearchWord = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SearchWord.fxml"));
        Parent searchWordParent = loader.load();
        Scene scene = new Scene(searchWordParent);
        stageSearchWord.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.insertFromFile(listWord);
    }
}
