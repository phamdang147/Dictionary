import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryCommandline extends DictionaryManagement {

    public void showAllWords(ArrayList<Word> listWord) {
        System.out.println("------------ SHOW-ALL-WORDS ------------" + '\n');
        ArrayList<String> wordExplain = new ArrayList<>();
        for (Word allWords : listWord) {
            System.out.println(allWords.getWordTarget());
            System.out.println(allWords.getWordExplain());
        }
    }

    public ArrayList<String> dictionarySearcher(ArrayList<Word> listWord) {
        ArrayList<String> result = new ArrayList<String>();
        System.out.print("Nhap tu can tra: ");
        String searchWord = new Scanner(System.in).nextLine();
        for (Word word : listWord) {
            if(word.getWordTarget().startsWith(searchWord)) {
                result.add(word.getWordTarget());
            }
        }
        return result;
    }

    public void dictionaryBasic(ArrayList<Word> listWord) {
        insertFromFile(listWord);
        showAllWords(listWord);
    }

    public void dictionaryAdvanced(ArrayList<Word> listWord) {
        insertFromFile(listWord);
        showAllWords(listWord);
        dictionaryLookup(listWord);
    }
}
