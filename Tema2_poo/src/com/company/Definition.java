package com.company;

import java.util.ArrayList;

public class Definition {
    String dict;
    String dictType;
    int year;
    ArrayList<String> text;

    public Definition() {}

    public Definition(String dict, String dictType, int year, ArrayList<String> text) {
        this.dict = dict;
        this.dictType = dictType;
        this.year = year;
        this.text = text;
    }
}
