package com.upshotdev.okWord.Data;

import com.upshotdev.okWord.model.Word;

public class Constant {

    public static final String ENGLISH = "en";
    public static final String FRENCH = "fr";
    public static final String ARABIC = "ar";

    public static final Word NUMBERS_EN = new Word(
            new String[]{
                    "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
                    "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
            },
            new String[]{
                    "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
            },
            new String[]{
                    "hundred", "thousand", "million", "billion"
            },
            null,
            "and",
            "cents"

    );

    public static final Word NUMBERS_FR = new Word(

            new String[]{
                    "zéro", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
                    "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"
            },
            new String[]{
                    "", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante-dix", "quatre-vingts", "quatre-vingt-dix"
            },
            new String[]{
                    "cent", "mille", "millions", "milliards"
            },
            null,
            "et",
            "centimes"

    );

    public static final Word NUMBERS_AR = new Word();
}