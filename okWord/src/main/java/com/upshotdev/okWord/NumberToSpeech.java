package com.upshotdev.okWord;

import com.upshotdev.okWord.utils.Utils;

import java.util.ArrayList;

class NumberToSpeech {

    private static final int[] NUMBERS_UP_TO_19 = {R.raw.en_0, R.raw.en_1, R.raw.en_2,
            R.raw.en_3, R.raw.en_4, R.raw.en_5, R.raw.en_6, R.raw.en_7, R.raw.en_8, R.raw.en_9, R.raw.en_10,
            R.raw.en_11, R.raw.en_12, R.raw.en_13, R.raw.en_14, R.raw.en_15, R.raw.en_16,
            R.raw.en_17, R.raw.en_18, R.raw.en_19};
    private static final int[] MULTIPLES_OF_10 = {-1, -1, R.raw.en_20, R.raw.en_30, R.raw.en_40, R.raw.en_50, R.raw.en_60, R.raw.en_70, R.raw.en_80, R.raw.en_90};
    private static final int[] GROUP = {R.raw.en_100, R.raw.en_1000, R.raw.en_1000000, R.raw.en_1000000000};

    //
    private static final int AND = R.raw.en_and;
    private static final int POINT = R.raw.en_point;
    //

    private double number;
    private ArrayList<Integer> result = new ArrayList<>();

    NumberToSpeech(final double number) {
        this.number = number;
    }

    ArrayList<Integer> convert() {
        ArrayList<Integer> result =
                new ArrayList<>(toWord());//integral part

        result.addAll(cents());//fractional part

        return result;

    }

    private ArrayList<Integer> toWord() {

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

        return result;
    }

    private void appendWord(int value, int group) {
        if (value > 0) {

            //for the number 100 we write it "cent" not "un cent" the same thing with 1000
//            if (language.equalsIgnoreCase(Constant.FRENCH) && value == 1 && group < 2)
//                words = GROUP[group];
//            else
            result.addAll(new NumberToSpeech(value).toWord());
            result.add(GROUP[group]);
        }
    }

    private void appendTensAndUnits(final int tensAndUnits) {
        result.addAll(convertTensAndUnits(tensAndUnits));
    }


    private ArrayList<Integer> convertTensAndUnits(final int number) {
        ArrayList<Integer> tensAndUnits = new ArrayList<>();
        final int tens = number / 10;
        final int units = number - tens * 10;

        if (number < 20) {
            tensAndUnits.add(NUMBERS_UP_TO_19[number]);
        } else if (units == 0) {
            tensAndUnits.add(MULTIPLES_OF_10[tens]);
        } else {
            tensAndUnits.add(MULTIPLES_OF_10[tens]);
            tensAndUnits.add(NUMBERS_UP_TO_19[units]);
        }
        return tensAndUnits;
    }

    //
    private ArrayList<Integer> cents() {
        int tensAndUnits = Utils.digitAfterComma(number);
        ArrayList<Integer> cents = new ArrayList<>();
        if (tensAndUnits > 0) {
            cents.add(POINT);//point(.)
            cents.addAll(convertTensAndUnits(tensAndUnits));
        }

        return cents;
    }

}