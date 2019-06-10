package com.upshotdev.okWord;

import com.upshotdev.okWord.Data.Constant;
import com.upshotdev.okWord.model.Word;
import com.upshotdev.okWord.utils.Utils;

import java.util.Locale;

class NumberToWord {

    static String[] NUMBERS_UP_TO_19;
    static String[] MULTIPLES_OF_10;
    static String[] GROUP;
    static String AND;
    static String CENTS;
    private static String CURRENCY_NAME;
    //English default
    private static String language = Constant.ENGLISH;
    private static Word words;
    StringBuffer result = new StringBuffer();
    private double number;

    NumberToWord(final double number) {
        this.number = number;
    }

    protected void initialize() {
        NUMBERS_UP_TO_19 = words.getNumbersUpTo19();
        MULTIPLES_OF_10 = words.getMultiplesOf10();
        GROUP = words.getGroup();
        CURRENCY_NAME = words.getCurrencyName();
        AND = words.getAnd();
        CENTS = words.getCents();
    }

    String convert() {
        //with currency labels
        withCurrencyLabels();

        String result;

        String integralPart = toWord();//integral part
        String fractionalPart = cents();//fractional part

        if (fractionalPart != null && number >= 1)
            //has integral part and fractional part
            result = String.format(Locale.ROOT, "%s %s %s %s %s", integralPart, CURRENCY_NAME, AND, fractionalPart, CENTS);
        else if (fractionalPart != null && number < 1)
            //has fractional part only (integral part equal 0)
            result = String.format(Locale.ROOT, "%s %s", fractionalPart, CENTS);
        else
            //has integral part only (fractional part equal 0)
            result = String.format(Locale.ROOT, "%s %s", integralPart, CURRENCY_NAME);


        return result;
    }

    String toWord() {

        int remainder;
        if (number > 999999999999.99) {
            throw new IllegalArgumentException("the number up to 999999999999.99");
        }

        final int billions = (int) (number / 1000000000);
        remainder = (int) (number % 1000000000);
        //
        final int millions = (remainder / 1000000);
        remainder = remainder % 1000000;
        //
        final int thousands = remainder / 1000;
        remainder = remainder % 1000;
        //
        final int hundreds = remainder / 100;
        final int tensAndUnits = remainder % 100;

        appendWord(billions, 3);
        appendWord(millions, 2);
        appendWord(thousands, 1);
        appendWord(hundreds, 0);
        appendTensAndUnits(tensAndUnits);

        return result.toString();
    }

    void appendWord(int value, int group) {
        if (value > 0) {
            String words;

            //for the number 100 we write it "cent" not "un cent" the same thing with 1000
            if (language.equalsIgnoreCase(Constant.FRENCH) && value == 1 && group < 2)
                words = GROUP[group];
            else
                words = new NumberToWord(value).toWord() + " " + GROUP[group];

            // add space between this word and the previous result
            appendWithSeparator(words);
        }

    }

    private void appendTensAndUnits(final int tensAndUnits) {

        if (!(result.length() > 0 && tensAndUnits == 0))
            appendWithSeparator(convertTensAndUnits(tensAndUnits));
    }

    void appendWithSeparator(final String words) {
        if (result.length() > 0) {
            result.append(" ");
        }
        result.append(words);
    }

    String convertTensAndUnits(final int number) {
        final int tens = number / 10;
        final int units = number - tens * 10;

        if (number < 20) {
            return NUMBERS_UP_TO_19[number];
        } else if (units == 0) {
            return MULTIPLES_OF_10[tens];
        } else {
            return MULTIPLES_OF_10[tens] + "-" + NUMBERS_UP_TO_19[units];
        }
    }

    //
    private String cents() {
        int tensAndUnits = Utils.digitAfterComma(number);
        String cents = null;
        if (tensAndUnits > 0)
            cents = convertTensAndUnits(tensAndUnits);

        return cents;
    }

    void setLanguage(String language) {
        NumberToWord.language = language;
        if (language.equalsIgnoreCase(Constant.FRENCH)) {
            words = Constant.NUMBERS_FR;
        } else words = Constant.NUMBERS_EN;
        initialize();
    }

    void setCurrencyName(String currencyName) {
        words.setCurrencyName(currencyName);
        CURRENCY_NAME = currencyName;
    }

    private void withCurrencyLabels() {
        if (CURRENCY_NAME == null) {
            CURRENCY_NAME = "";
            CENTS = "";
        }
    }

}