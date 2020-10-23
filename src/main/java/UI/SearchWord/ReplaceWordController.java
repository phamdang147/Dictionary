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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class ReplaceWordController implements Initializable {
    @FXML
    TextField textTarget;
    @FXML
    TextArea textExplain;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    public void setWord(Word word) {
        textTarget.setText(word.getWordTarget());
        textExplain.setText(word.getWordExplain());
        for (int i = 0; i < listWord.size(); i++) {
            if(textTarget.getText().equals(listWord.get(i).getWordTarget())) {
                listWord.remove(i);
                dictionary.dictionaryExportToFile(listWord);
                break;
            }
        }
    }

    @FXML
    Button btnConfirmReplace;
    public void confirmReplace(ActionEvent event) {
        Alert alertConfirm = new Alert(Alert.AlertType.CONFIRMATION);
        alertConfirm.setTitle("Replace Word");
        alertConfirm.setHeaderText("Are you sure you want to replace this word?");
        alertConfirm.setContentText(textTarget.getText());

        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            for (int i = 0; i < listWord.size(); i++) {
                if (listWord.get(i).getWordTarget().compareTo(textTarget.getText()) > 0) {
                    listWord.add(i, new Word(textTarget.getText(), textExplain.getText()));
                    dictionary.dictionaryExportToFile(listWord);
                    break;
                }
            }

            Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Replace Word");
            alertInfo.setHeaderText(null);
            alertInfo.setContentText("Replaced words successfully!");
            alertInfo.showAndWait();
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
