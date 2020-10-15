package DictionaryCommandline;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public void insertFromCommandline(ArrayList<Word> listWord) {
        Scanner sc = new Scanner(System.in);
        int amount;
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
            String line;
            String target = null;
            StringBuilder explain = new StringBuilder();
            while ((line = input.readLine()) != null) {
                if (line.startsWith("@")) {
                    target = line.substring(1);
                }
                if ((!line.startsWith("@")) && (line.length() > 1)) {
                    explain.append(line).append('\n');
                }
                if (line.length() <= 1 && target != null) {
                    Word word = new Word(target, explain.toString());
                    listWord.add(word);
                    explain = new StringBuilder();
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

    public void dictionaryLookup(ArrayList<Word> listWord) {
        boolean noSuchWord = true;
        System.out.print("Nhap tu can tra: ");
        String searchWord = new Scanner(System.in).nextLine();
        for (Word word : listWord) {
            if(searchWord.equals(word.getWordTarget())) {
                System.out.println("Nghia cua tu \"" + searchWord + "\" la: " + '\n' + word.getWordExplain());
                noSuchWord = false;
                break;
            }
        }
        if(noSuchWord) {
            System.out.println("Tu nay khong ton tai!!");
        }
    }

    public void dictionaryAddWord(ArrayList<Word> listWord) {
        System.out.print("Nhap tu can them: ");
        String target = new Scanner(System.in).nextLine();
        System.out.print("Nhap nghia: ");
        String explain = new Scanner(System.in).nextLine();
        explain = explain + '\n';
        for (Word word : listWord) {
            if(target.equals(word.getWordTarget())) {
                System.out.println("Tu can them da ton tai!!");
            }
        }
        Word newWord = new Word(target, explain);
        listWord.add(newWord);
    }

    public void dictionaryReplaceWord(ArrayList<Word> listWord) {
        boolean noSuchWord = true;
        System.out.print("Nhap tu can sua: ");
        String target = new Scanner(System.in).nextLine();
        for (Word word : listWord) {
            if(target.equals(word.getWordTarget())) {
                System.out.print("Nhap nghia: ");
                String explain = new Scanner(System.in).nextLine();
                explain = explain + '\n';
                word.setWordExplain(explain);
                noSuchWord = false;
                break;
            }
        }
        if(noSuchWord) {
            System.out.println("!!Tu nay khong ton tai");
        }
    }

    public void dictionaryDeleteWord(ArrayList<Word> listWord) {
        System.out.print("Nhap tu can xoa: ");
        String searchWord = new Scanner(System.in).nextLine();
        int i = 0;
        for (Word word : listWord) {
            if(searchWord.equals(word.getWordTarget())) {
                listWord.remove(i);
            }
            i += 1;
        }
    }

    public void dictionaryExportToFile(ArrayList<Word> listWord) {
        try {
            FileWriter file = new FileWriter("anhviet.txt");
            BufferedWriter output = new BufferedWriter(file);
            for (Word word : listWord) {
                output.write('@' + word.getWordTarget() + '\n');
                output.write(word.getWordExplain() + '\n');
            }

            output.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

