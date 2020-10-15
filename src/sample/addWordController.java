package sample;

import DictionaryCommandline.Word;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class addWordController {
    @FXML
    Label addWoldLabel;
    @FXML
    Label addWoldTargetLabel;
    @FXML
    Label addWoldExplainLabel;
    @FXML
    TextField addWordTarget;
    @FXML
    TextArea addWordExplain;
    @FXML
    Button buttonConfirmAdd;
    @FXML
    Button buttonGoBack;

    ArrayList<Word> listWord = new ArrayList<>();

    public void addWordConfirm() throws IOException {
        boolean noSuchWord = true;
        for (Word word : listWord) {
            if (word.getWordTarget().equals(addWordTarget.getText())) {
                noSuchWord = false;
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Add Word");
                alert.setHeaderText("Can not add Word");
                alert.setContentText("This word is already in the dictionary!");
                alert.showAndWait();
                break;
            }
        }
        if (noSuchWord) {
            File file = new File("anhviet.txt");
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write('@' + addWordTarget.getText() + '\n');
            bw.write(addWordExplain.getText() + '\n' + '\n');
            addWordTarget.clear();
            addWordExplain.clear();

            bw.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Thêm từ");
            alert.setHeaderText(null);
            alert.setContentText("Thêm từ thành công!");
            alert.showAndWait();
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
}
