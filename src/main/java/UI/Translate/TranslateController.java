package UI.Translate;

import UI.Main.Speech;
import UI.Main.Translate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class TranslateController {
    @FXML
    TextArea textSearch;
    @FXML
    TextArea textMean;
    @FXML
    Button btnSearch;

    public void searchWord(ActionEvent event) throws Exception {
        textMean.setText(Translate.call_me(textSearch.getText()).replace("\\n", "\n"));
    }

    @FXML
    Button btnSpeech;
    public void speechWord(ActionEvent event) {
        Speech speechWord = new Speech("kevin16");
        speechWord.say(textSearch.getText());
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
}
