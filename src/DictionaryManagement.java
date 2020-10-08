import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {

    public void insertFromCommandline(ArrayList<Word> listWord) {
        Scanner sc = new Scanner(System.in);
        int amount = 0;
        System.out.print("Nhap so luong tu vung: ");
        amount = sc.nextInt();
        sc.nextLine();

        String wordTarget, wordExplain;
        for (int i = 0; i < amount; i++) {
            System.out.print('\n' + "Nhap tu tieng Anh: ");
            wordTarget = sc.nextLine();
            System.out.print('\n' + "Nhap nghia tieng Viet: ");
            wordExplain = sc.nextLine();
            Word newWord = new Word(wordTarget, wordExplain);
            listWord.add(newWord);
        }
    }

    public void insertFromFile(ArrayList<Word> listWord) {
        try {
            FileReader file = new FileReader("anhviet.txt");
            BufferedReader input = new BufferedReader(file);

            String line, target = null, explain = "";

            while ((line = input.readLine()) != null) {
                if (line.startsWith("@")) {
                    target = line;
                }
                if ((!line.startsWith("@")) && (line.length() > 1)) {
                    explain = explain + '\n' + line;
                }
                if (line.length() <= 1) {
                    Word word = new Word(target, explain);
                    listWord.add(word);
                    explain = "";
                }
            }

            input.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
