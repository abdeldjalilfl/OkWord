package com.upshotdev.okWord;

public interface NumberToSpeechListener {

    void setLanguage(String language);

    void toSpeech(double number);

}
