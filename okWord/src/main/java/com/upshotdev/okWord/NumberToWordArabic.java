package com.upshotdev.okWord;

import java.util.Locale;

public class NumberToWordArabic extends NumberToWord {

    private static final String[] PLURAL_GROUPS = {"", "آلاف", "ملايين", "مليارات"};
    private static final String[] APPENDED_GROUPS = {"", "ألفاً", "مليوناً", "ملياراً"};
    private static final String[] GROUP = {"مائة", "ألف", "مليون", "مليار"};

    //private static final String[] APPENDED_TWOS = {"مئتا", "ألفا", "مليونا", "مليارا"};
    private static final String[] TWOS = {"مئتان", "ألفان", "مليونان", "ملياران"};
    //
    private static final String[] HUNDREDS = {"", "مائة", "مئتان", "ثلاثمائة", "أربعمائة", "خمسمائة", "ستمائة", "سبعمائة", "ثمانمائة", "تسعمائة"};
    private static final String[] MULTIPLES_OF_10 = {"", "", "عشرون", "ثلاثون", "أربعون", "خمسون", "ستون", "سبعون", "ثمانون", "تسعون"};
    //
    //private static final String[] FEMININE_ONES = {"إحدى", "اثنتان", "ثلاث", "أربع","خمس","ست","سبع","ثمان","تسع","عشر","إحدى عشرة","اثنتا عشرة","ثلاث عشرة","أربع عشرة","خمس عشرة","ست عشرة","سبع عشرة","ثماني عشرة","تسع عشرة"};
    private static final String[] NUMBERS_UP_TO_19 = {"صفر", "واحد", "اثنان", "ثلاثة", "أربعة", "خمسة", "ستة", "سبعة", "ثمانية", "تسعة", "عشرة", "أحد عشر", "اثنا عشر", "ثلاثة عشر", "أربعة عشر", "خمسة عشر", "ستة عشر", "سبعة عشر", "ثمانية عشر", "تسعة عشر"};

    private static final String AND = "و";
    private static final String CENTS = "سنتيم";

    NumberToWordArabic(double number) {
        super(number);
    }

    @Override
    protected void initialize() {
        NumberToWord.NUMBERS_UP_TO_19 = NUMBERS_UP_TO_19;
        NumberToWord.MULTIPLES_OF_10 = MULTIPLES_OF_10;
        NumberToWord.GROUP = GROUP;
        //CURRENCY_NAME
        NumberToWord.AND = AND;
        NumberToWord.CENTS = CENTS;
    }

    @Override
    void appendWord(int value, int group) {
        if (value > 0) {
            String words;
            if (result.length() > 0) appendWithSeparator(AND);

            if (group == 0) words = HUNDREDS[value];
            else {
                if (value == 1) words = GROUP[group];
                else if (value == 2) words = TWOS[group];
                else if (value < 11)
                    words = new NumberToWordArabic(value).toWord() + " " + PLURAL_GROUPS[group];
                else
                    words = new NumberToWordArabic(value).toWord() + " " + GROUP[group];//APPENDED_GROUPS
            }

            // add space between this word and the previous result
            appendWithSeparator(words);
        }
    }

    @Override
    String convertTensAndUnits(int number) {
        if (result.length() > 0) appendWithSeparator(AND);
        final int tens = number / 10;
        final int units = number - tens * 10;

        if (number < 20) {
            return NUMBERS_UP_TO_19[number];
        } else if (units == 0) {
            return MULTIPLES_OF_10[tens];
        } else {
            return String.format(Locale.ROOT, "%s %s %s", NUMBERS_UP_TO_19[units], AND, MULTIPLES_OF_10[tens]);
        }
    }
}