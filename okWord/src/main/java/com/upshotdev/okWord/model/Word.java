package com.upshotdev.okWord.model;

public class Word {

    private String[] numbersUpTo19;
    private String[] multiplesOf10;
    private String[] group;
    //
    private String currencyName;
    private String and;
    private String cents;

    public Word() {
    }

    public Word(String[] numbersUpTo19, String[] multiplesOf10, String[] group, String currencyName, String and, String cents) {
        this.numbersUpTo19 = numbersUpTo19;
        this.multiplesOf10 = multiplesOf10;
        this.group = group;
        this.currencyName = currencyName;
        this.and = and;
        this.cents = cents;
    }

    public String[] getNumbersUpTo19() {
        return numbersUpTo19;
    }

    public void setNumbersUpTo19(String[] numbersUpTo19) {
        this.numbersUpTo19 = numbersUpTo19;
    }

    public String[] getMultiplesOf10() {
        return multiplesOf10;
    }

    public void setMultiplesOf10(String[] multiplesOf10) {
        this.multiplesOf10 = multiplesOf10;
    }

    public String[] getGroup() {
        return group;
    }

    public void setGroup(String[] group) {
        this.group = group;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getAnd() {
        return and;
    }

    public void setAnd(String and) {
        this.and = and;
    }

    public String getCents() {
        return cents;
    }

    public void setCents(String cents) {
        this.cents = cents;
    }
}
