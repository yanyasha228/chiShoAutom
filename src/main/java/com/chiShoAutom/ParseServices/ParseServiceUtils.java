package com.chiShoAutom.ParseServices;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ParseServiceUtils {

//    public boolean checkIfCssQueryForPriceIsValid(String url, String cssQuery){
//
//        String strToVal = null;
//        try {
//            strToVal = removeTrashCharsFromPriceString(cssQueryParser.
//                    getText(url, cssQuery));
//        } catch (IOException e) {
//            return false;
//        }
//
//        try {
//            Float.parseFloat(strToVal);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//
//    }

//    public Optional<Float> getValidPriceByCssQuery(String url, String cssQuery){
//
//
//        String strToVal = null;
//        try {
//            strToVal = removeTrashCharsFromPriceString(cssQueryParser.
//                    getText(url, cssQuery));
//
//        } catch (IOException e) {
//            return Optional.empty();
//        }
//
//        try {
//            return Optional.of(Float.parseFloat(strToVal));
//
//        } catch (NumberFormatException e) {
//
//            return Optional.empty();
//        }
//
//
//    }

    public static List<String> generateKeyWordsFromName(String name, String externalId) {

        //Exc
        //",
        String[] badEndTiedChars = {"." , ",", ";" , ":" , "\""};
        String[] badUntiedChars = {"(", ")", "", "" , "\""};
        String[] badChars = {"-", "–", "!", ":", "(", ")", "|", ";", "\"" , ","};

        String[] badWords = {"с", "для", "из", "на", "под", "по", "от", "в", "и", "типа", "к"};


        String[] wordsSet = name.split(" ");

        List<String> keyWords = Arrays.asList(wordsSet);

        for (int i = 0; i < wordsSet.length - 1; i++) {
            keyWords.add(wordsSet[i]);
        }
        ;

        return null;

    }

    public static String removeTrashCharsFromPriceString(String strForCl) {

        String retString = strForCl.replaceAll("[^\\d.,]", "");

        retString = retString.replaceAll(",", ".");

        retString = retString.replaceAll(" ", "");

        if (!retString.isEmpty()) {
            if (String.valueOf(retString.charAt(retString.length() - 1)).equalsIgnoreCase(".")) {
                char[] chArr = retString.toCharArray();
                for (int i = retString.length() - 1; i >= 0; i--) {
                    if (chArr[i] == '.') {
                        retString = retString.substring(0, retString.length() - 1);
                    } else break;
                }
            }
        }
        return retString;
    }
}
