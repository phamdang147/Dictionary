package UI.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    Button btnSearchWord;
    @FXML
    Button btnTranslate;

    public void searchWord(ActionEvent event) throws IOException {
        Stage stageSearchWord = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SearchWord.fxml"));
        Parent searchWordParent = loader.load();
        Scene scene = new Scene(searchWordParent);
        stageSearchWord.setScene(scene);
    }

    public void translate(ActionEvent event) throws IOException {
        Stage stageTranslate = (Stage)((Node) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/Translate.fxml"));
        Parent translateParent = loader.load();
        Scene scene = new Scene(translateParent);
        stageTranslate.setScene(scene);
    }


}
