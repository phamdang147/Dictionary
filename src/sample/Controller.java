package sample;



import DictionaryCommandline.Dictionary;
import DictionaryCommandline.Word;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class Controller implements Initializable {
    @FXML
    private TextArea textMeaning;
    @FXML
    private TextField textSearch;
    @FXML
    private Button buttonSearch;
    @FXML
    private ListView<String> listView;

    /**
     * Xu ly su kien button.
     */
    public void hamclick() {
        ArrayList<Word> listWord = new ArrayList<Word>();
        Dictionary dictionary = new Dictionary();
        dictionary.insertFromFile(listWord);
        String target = textSearch.getText();
        for (Word word : listWord) {
            if(target.equals(word.getWordTarget())) {
                textMeaning.setText(word.getWordExplain());
                break;
            }
        }
    }

    /**
     * listView.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Word> listWord = new ArrayList<Word>();
        Dictionary dictionary = new Dictionary();
        dictionary.insertFromFile(listWord);
        ArrayList<String> listWordTarget = new ArrayList<String>();
        for (Word allWords : listWord) {
            listWordTarget.add(allWords.getWordTarget());
        }
        for (String show : listWordTarget) {
            System.out.println(show);
        }
        listView.getItems().addAll(listWordTarget);

        /**
         * Loc tu.
         */
        textSearch.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String item = textSearch.getText();
                ArrayList<String> match = new ArrayList<String>(listWordTarget);

                match.removeIf(new Predicate<String>() {
                    @Override
                    public boolean test(String s) {
                        return !s.startsWith(item);
                    }
                });
                listView.getItems().clear();
                listView.getItems().addAll(match);
            }
        });

        /**
         * Bat su kien trong textField.
         */
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String item = listView.getSelectionModel().getSelectedItem();
                for (Word word : listWord) {
                    if(item.equals(word.getWordTarget())) {
                        textMeaning.setText(word.getWordExplain());
                        break;
                    }
                }
            }
        });

        /**
         * Bat su kien khi nhap vao ky tu Enter
         */
        textSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    String target = textSearch.getText();
                    for (Word word : listWord) {
                        if(target.equals(word.getWordTarget())) {
                            textMeaning.setText(word.getWordExplain());
                            break;
                        }
                    }
                }
            }
        });
    }
}
