package com.company;

import com.google.gson.JsonElement;

import java.util.ArrayList;

public class Word implements Cloneable {
    String word;
    String word_en;
    String type;
    ArrayList<String> singular;
    ArrayList<String> plural;
    ArrayList<Definition> definitions;

    public Word() {}

    public Word(String word, String word_en, String type, ArrayList<String> singular,
                ArrayList<String> plural, ArrayList<Definition> definitions) {
            this.word = word;
            this.word_en = word_en;
            this.type = type;
            this.singular = singular;
            this.plural = plural;
            this.definitions = definitions;
        }

        public String getWord() {
            return word;
        }

        public String getWord_en() {
            return word_en;
        }

        public String getType() {
            return type;
        }

        public ArrayList<String> getSingular() {
            return singular;
        }

        public ArrayList<String> getPlural() {
            return plural;
        }

        public ArrayList<Definition> getDefinitions() {
            return definitions;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setWord_en(String word_en) {
            this.word_en = word_en;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setSingular(ArrayList<String> singular) {
            this.singular = singular;
        }

        public void setPlural(ArrayList<String> plural) {
            this.plural = plural;
        }

        public void setDefinitions(ArrayList<Definition> definitions) {
            this.definitions = definitions;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
}
