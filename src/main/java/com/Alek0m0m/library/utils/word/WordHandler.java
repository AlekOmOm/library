package main.java.com.Alek0m0m.library.utils.word;

import java.util.*;

// class for
// 1. cleaning words for comparison
// 2. appending before and after words
// 3. appending clean word and adding special characters back after
public class WordHandler {

    private static List<Character> specialCharacters = new ArrayList<>(List.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '{', '}', '[', ']', '|', '\\', ':', ';', '"', '\'', '<', '>', ',', '.', '?', '/', '`', '~'));

    public static String cleanWord(String word) {
        // removing special characters
        StringBuilder cleanWord = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            if (!specialCharacters.contains(word.charAt(i))) {
                cleanWord.append(word.charAt(i));
            }
        }
        return cleanWord.toString();
    }

    public static String appendAroundWord(String roughWord, String appendBefore, String appendAfter) {
        char[] roughWordChars = roughWord.toCharArray();
        String cleanWord = cleanWord(roughWord);
        char firstCharOfCleanWord = cleanWord.charAt(0);
        char lastCharOfCleanWord = cleanWord.charAt(cleanWord.length()-1);
        int indexFirst = roughWord.indexOf(firstCharOfCleanWord);
        int indexLast = roughWord.lastIndexOf(lastCharOfCleanWord);

        String partBeforeAppend = roughWord.substring(0, indexFirst);
        String partAfterAppend = roughWord.substring(indexLast+1);


        return partBeforeAppend + appendBefore + cleanWord + appendAfter + partAfterAppend;
    }

    public static void main(String[] args) {
        // test of 2 methods
        System.out.println("cleanWord test:");
        System.out.println(" word: heyo!");
        System.out.println(" result: "+cleanWord("heyo!"));
        System.out.println();
        System.out.println("appendAroundWord test:");
        System.out.println(" word: heyo!");
        System.out.println(" result: "+appendAroundWord(".heyo!", "[[", "]]"));
    }
}
