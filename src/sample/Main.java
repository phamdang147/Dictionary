package sample;

import DictionaryCommandline.Dictionary;
import DictionaryCommandline.Word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    ArrayList<Word> listWord = new ArrayList<Word>();
    Dictionary dictionary = new Dictionary();

    @Override
    public void start(Stage primaryStage) throws Exception {
        dictionary.insertFromFile(listWord);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
