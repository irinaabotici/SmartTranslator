package com.company;

import java.util.Comparator;

class SortDefinitions implements Comparator<Definition> {
    @Override
    public int compare(Definition o1, Definition o2) {
        return o1.year - o2.year;
    }
}
