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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class DeleteWordController implements Initializable {
    @FXML
    TextField textTarget;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    public void setWord(String wordWantDel) {
        textTarget.setText(wordWantDel);
    }

    @FXML
    Button btnconfirmDeleteWord;
    public void confirmDeleteWord(ActionEvent event) {
        if (textTarget.getText().length() == 0) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Delete Word");
            alert.setHeaderText(null);
            alert.setContentText("Please select the word you want to delete!");
            alert.showAndWait();
        } else {
            boolean noSuchWord = true;
            int i = 0;
            for (Word word : listWord) {
                if(textTarget.getText().equals(word.getWordTarget())) {
                    Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
                    alertConfirm.setTitle("Delete Word");
                    alertConfirm.setHeaderText("Are you sure you want to delete this word?");
                    alertConfirm.setContentText(textTarget.getText());

                    Optional<ButtonType> option = alertConfirm.showAndWait();
                    if (option.get() == ButtonType.OK) {
                        listWord.remove(i);
                        textTarget.clear();
                        dictionary.dictionaryExportToFile(listWord);
                        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
                        alertInfo.setTitle("Delete Word");
                        alertInfo.setHeaderText(null);
                        alertInfo.setContentText("Deleted words successfully!");
                        alertInfo.showAndWait();
                    }
                    noSuchWord = false;
                    break;
                }
                i++;
            }
            if (noSuchWord) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Delete Word");
                alert.setHeaderText("Can not delete Word");
                alert.setContentText("This word is not in the dictionary!");
                alert.showAndWait();
            }
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
