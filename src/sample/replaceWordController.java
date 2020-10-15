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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

public class replaceWordController implements Initializable {
    @FXML
    Label replaceWoldLabel;
    @FXML
    Label replaceWoldTargetLabel;
    @FXML
    Label replaceWoldExplainLabel;
    @FXML
    TextField replaceWordTarget;
    @FXML
    TextArea replaceWordExplain;
    @FXML
    Button buttonConfirmReplace;
    @FXML
    Button buttonGoBack;

    ArrayList<Word> listWord = new ArrayList<>();
    Dictionary dictionary = new Dictionary();

    public void setWord(Word word) {
        replaceWordTarget.setText(word.getWordTarget());
        replaceWordExplain.setText(word.getWordExplain());
    }

    public void replaceWordConfirm() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Replace Word");
        alert.setHeaderText("Are you sure you want to replace this word?");
        alert.setContentText(replaceWordTarget.getText());

        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK) {
            int i = 0;
            for (Word word : listWord) {
                if(replaceWordTarget.getText().equals(word.getWordTarget())) {
                    listWord.get(i).setWordExplain(replaceWordExplain.getText());
                    break;
                }
                i++;
            }
            dictionary.dictionaryExportToFile(listWord);
        }
    }

    public void goBack(ActionEvent event) throws  IOException {
        Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        dictionary.insertFromFile(listWord);
    }
}
