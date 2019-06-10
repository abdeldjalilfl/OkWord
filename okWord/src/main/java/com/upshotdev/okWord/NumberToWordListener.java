package com.upshotdev.okWord;

import androidx.annotation.NonNull;

public interface NumberToWordListener {

    void setLanguage(@NonNull String language);

    void setCurrency(@NonNull String currency);

    String toText(double number);
}
