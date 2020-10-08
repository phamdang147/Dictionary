import java.util.ArrayList;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords(ArrayList<Word> listWord) {
        System.out.println("------------ SHOW-ALL-WORDS ------------" + '\n');
        ArrayList<String> wordExplain = new ArrayList<>();
        for (Word allWords : listWord) {
            System.out.println(allWords.getWordTarget());
            System.out.println(allWords.getWordExplain());
        }
    }

    public void dictionaryBasic(ArrayList<Word> listWord) {
        insertFromFile(listWord);
        showAllWords(listWord);
    }
}
