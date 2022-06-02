package com.company;

import java.util.Comparator;

class SortWord implements Comparator<Word> {

    @Override
    public int compare(Word o1, Word o2) {
        return o1.word.compareTo(o2.word);
    }
}

