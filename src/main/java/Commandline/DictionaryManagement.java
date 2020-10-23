package Commandline;

import java.io.*;
import java.util.ArrayList;

public class DictionaryManagement {
    public void insertFromFile(ArrayList<Word> listWord) {
        try {
            FileReader file = new FileReader("edict.txt");
            BufferedReader input = new BufferedReader(file);
            String line;
            while ((line = input.readLine()) != null) {
                String[] words = line.split("\t");
                words[1] = words[1].replace("<br/>", "\n");
                words[1] = words[1].replace("@", "");
                listWord.add(new Word(words[0], words[1]));
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryExportToFile(ArrayList<Word> listWord) {
        try {
            FileWriter file = new FileWriter("edict.txt");
            BufferedWriter output = new BufferedWriter(file);
            for (Word word : listWord) {
                output.write(word.getWordTarget() + '\t');
                output.write("@" + word.getWordExplain().replace("\n", "<br/>") + '\n');
            }

            output.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
