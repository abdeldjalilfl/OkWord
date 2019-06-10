package com.upshotdev.okWord;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.upshotdev.okWord.Data.Constant;
import com.upshotdev.okWord.utils.Utils;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;

public class OkWord implements NumberToWordListener, NumberToSpeechListener {

    private static String TAG = "OkWord";
    private Context context;
    private String language = Constant.ENGLISH;
    private String currencyCode;

    public OkWord(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void toSpeech(double number) {
        ArrayList<Integer> list = new NumberToSpeech(number).convert();
        Utils.play(context, list);
    }

    @Override
    public String toText(double number) {

        NumberToWord numberToWord;
        if (language.equals(Constant.ARABIC))
            numberToWord = new NumberToWordArabic(number);
        else numberToWord = new NumberToWord(number);

        numberToWord.setLanguage(language);
        numberToWord.setCurrencyName(getCurrencyName(currencyCode, language));

        return numberToWord.convert();
    }

    /**
     * @param language An ISO 639 alpha-2 language code
     */
    @Override
    public void setLanguage(@NonNull String language) {
        this.language = language;
    }

    /**
     * @param currencyCode the ISO 4217 code of the currency
     */
    @Override
    public void setCurrency(@Nullable String currencyCode) {
        this.currencyCode = currencyCode;
    }

    private String getCurrencyName(String currencyCode, String language) {
        if (currencyCode == null || language == null) return null;
        return Currency.getInstance(currencyCode)
                .getDisplayName(new Locale(language));
    }

}