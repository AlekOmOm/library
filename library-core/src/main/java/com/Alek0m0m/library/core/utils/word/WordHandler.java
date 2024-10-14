package com.Alek0m0m.library.core.utils.word;

import java.util.*;

public class WordHandler {

    private static List<Character> specialCharacters = new ArrayList<>(List.of('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '+', '=', '{', '}', '[', ']', '|', '\\', ':', ';', '"', '\'', '<', '>', ',', '.', '?', '/', '`', '~'));

    public static String cleanWord(String word) {
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
}
