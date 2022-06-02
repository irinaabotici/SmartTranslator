package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class AllDictionaries {
    Map<String, Dictionary> dictionaries = new HashMap<>();

    public AllDictionaries() {
    }

    public AllDictionaries(String language, Dictionary dictionary) {
        this.dictionaries.put(language, dictionary);
    }

    // adaug un  nou dictionar in colectia de dictionare
    boolean addDictionary(String language, Dictionary dictionary) {
        if (!dictionaries.containsKey(language)) {
            dictionaries.put(language, dictionary);
            return true;
        } else {
            return false;
        }
    }

    // Metodă pentru adăugarea unui cuvânt în dicționar
    boolean addWord(Word word, String language) {
        int contor = 0;
        // verific daca exista cuvinte in dictionar si daca exista,
        // adaug noul cuvant daca nu exista deja unul
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                if (!dictionaries.get(language).words.isEmpty()) {
                    for (Word w : dictionaries.get(language).words) {
                        if (w.word.equals(word.word)) {
                            contor++;
                        }
                    }
                } else {
                    dictionaries.get(language).words.add(word);
                    System.out.println("Cuvantul " + word.word +
                            " a fost adaugat in dictionarul " + language + "!");
                    return true;
                }
            } else if (!dictionaries.containsKey(language)) {
                System.out.println("Nu exista dictionarul pentru limba " + language + "!");
                return false;
            }
        }
        if (contor == 0) {
            dictionaries.get(language).words.add(word);
            System.out.println("Cuvantul " + word.word +
                    " a fost adaugat in dictionarul " + language + "!");
            return true;
        }
        System.out.println("Cuvantul " + word.word +
                " exista deja in dictionatul " + language + "!");
        return false;
    }

    // Metodă pentru ștergerea unui cuvânt din dicționar
    boolean removeWord(String word, String language) {
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                ArrayList<Word> wordsCopy = dictionaries.get(dictLanguage).words;
                for (Word w : wordsCopy) {
                    // verific si cuvintele in engleza
                    if (w.word.equals(word) || w.word_en.equals(word)) { // cazul in care este cuvantul
                        dictionaries.get(dictLanguage).words.remove(w); // il elimin
                        System.out.println("Cuvantul " + word +
                                " a fost sters din dictionarul " + language + "!");
                        return true;
                    }
                }
            }
        }
        System.out.println("Cuvantul " + word + " nu exista in dictionarul " + language + "!");
        return false;
    }

    // Metodă pentru adăugarea unei noi definiții pentru un cuvânt
    boolean addDefinitionForWord(String word, String language, Definition definition) {
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                for (Word w : dictionaries.get(dictLanguage).words) {
                    if (w.word.equals(word) || w.word_en.equals(word)) { // am gasit cuvantul in dictionar
                        if (!w.definitions.isEmpty()) {
                            for (Definition d : w.definitions) { // ne uitam prin definitiile cuvantului
                                if (d.dict.equals(definition.dict) && !d.text.equals(definition.text)) {
                                    w.definitions.add(definition);
                                    return true;
                                } else if (!d.dict.equals(definition.dict)) { // in dictionar diferit
                                    w.definitions.add(definition);
                                    return true;
                                }
                            }
                        } else { // daca nu exista nici o definitie
                            w.definitions.add(definition);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // Metodă pentru ștergerea unei definiții a unui cuvânt
    boolean removeDefinition(String word, String language, String dictionary) {
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                for (Word w : dictionaries.get(dictLanguage).words) {
                    if (w.word.equals(word)) { // am gasit cuvantul
                        ArrayList<Definition> copyDefinitions = w.definitions;
                        for (Definition d : copyDefinitions) {
                            if (d.dict.equals(dictionary)) { // am gasit numele dictionarului cautat
                                w.definitions.remove(d);
                                System.out.println("Definitia din dictionarul " + '"'
                                        + dictionary + '"' + " a fost stearsa!");
                                return true;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Definitia din dictionarul " + '"' + dictionary + '"' + " nu exista!");
        return false;
    }

    // Metodă pentru traducerea unui cuvânt
    String translateWord(String word, String fromLanguage, String toLanguage) {
        String englishWord = null;
        if (fromLanguage.equals("en")) {
            for (String dictLanguage : dictionaries.keySet()) {
                if (dictLanguage.equals(toLanguage)) { // am gasit dictionarul cu cuvantul pe care trebuie sa-l traducem
                    for (Word w : dictionaries.get(toLanguage).words) {
                        if (w.word_en.equals(word)) {
                            return "Traducerea pentru cuvantul " + word + " din " + fromLanguage
                                    + " in " + toLanguage + " este " + w.word + "!";
                        }
                    }
                }
            }
        } else {
            for (String dictLanguage : dictionaries.keySet()) {
                if (dictLanguage.equals(fromLanguage)) { // am gasit dictionarul cu cuvantul pe care trebuie sa-l traducem
                    for (Word w : dictionaries.get(fromLanguage).words) {
                        if (w.word.equals(word)) {
                            if (toLanguage.equals("en")) {
                                return "Traducerea pentru cuvantul " + word + " din " + fromLanguage
                                        + " in " + toLanguage + " este " + w.word_en + "!";
                            } else {
                                englishWord = w.word_en; // salvez cuvantul in engleza
                            }
                        }
                    }
                }
            }
            for (String dictLanguage1 : dictionaries.keySet()) {
                if (dictLanguage1.equals(toLanguage)) { // am gasit dictionarul cu cuvantul pe care trebuie sa-l traducem
                    for (Word w1 : dictionaries.get(toLanguage).words) {
                        if (w1.word_en.equals(englishWord)) { // ma folosesc de cuvantul in engleza ca sa il pot gasi in limba cautata
                            return "Traducerea pentru cuvantul " + word + " din " + fromLanguage
                                    + " in " + toLanguage + " este " + w1.word + "!";
                        }
                    }
                }
            }
        }
        return "Traducerea pentru cuvantul " + word + " din limba " +
                fromLanguage + " nu exista in limba " + toLanguage + "!";
    }

    // Metodă pentru traducerea unei propoziții
    String translateSentence(String sentence, String fromLanguage, String toLanguage) {
        int check = 0;
        String englishSentence = "";
        StringTokenizer wordsSentence = new StringTokenizer(sentence, " ");
        ArrayList<String> languageSentence = new ArrayList<>();
        String translatedSentence = "";
        while (wordsSentence.hasMoreTokens()) {
            languageSentence.add(wordsSentence.nextToken());
        }
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(fromLanguage)) {
                for (String wordFromSentence : languageSentence) {
                    // ma uit prin toate cuvintele, prin forma de singular si prin forma de plural
                    for (Word w : dictionaries.get(fromLanguage).words) {
                        if (w.word.equals(wordFromSentence)) {
                            englishSentence = englishSentence + w.word_en + " ";
                            check++;
                            break;
                        }
                        for (String s : w.singular) {
                            if (s.equals(wordFromSentence)) {
                                englishSentence = englishSentence + w.word_en + " ";
                                check++;
                                break;
                            }
                        }
                        for (String p : w.plural) {
                            if (p.equals(wordFromSentence)) {
                                englishSentence = englishSentence + w.word_en + " ";
                                check++;
                                break;
                            }
                        }
                    }
                    if (check == 0)
                        // salvez forma propozitiei in engleza pentru a o putea traduce
                        englishSentence = englishSentence + wordFromSentence + " ";
                    else
                        check = 0;
                }
            }
        }
        // impart propozitia in engleza pe care am creat-o in cuvinte
        StringTokenizer wordsSentence1 = new StringTokenizer(englishSentence, " ");
        ArrayList<String> languageSentence1 = new ArrayList<>();
        while (wordsSentence1.hasMoreTokens()) {
            languageSentence1.add(wordsSentence1.nextToken());
        }
        // caut in dictionar dupa cuvantul in engleza
        for (String dictLanguage1 : dictionaries.keySet()) {
            if (dictLanguage1.equals(toLanguage)) {
                for (String englishWord : languageSentence1) {
                    for (Word w : dictionaries.get(toLanguage).words) {
                        if (w.word_en.equals(englishWord)) {
                            translatedSentence = translatedSentence + w.word + " ";
                            check++;
                            break;
                        }
                    }
                    if (check == 0)
                        // creez propozitia tradusa
                        translatedSentence = translatedSentence + englishWord + " ";
                    else
                        check = 0;
                }
                return "Traducerea propozitiei " + '"' + sentence + '"' + " din " + fromLanguage +
                        " in " + toLanguage + " este: " + translatedSentence;
            }
        }
        return "Nu exista traducere!";
    }

    // Metodă pentru traducerea unei propoziții și furnizarea a 3 variante de traducere folosind
    // sinonimele cuvintelor
    ArrayList<String> translateSentences(String sentence, String fromLanguage, String toLanguage) {
        int check = 0;
        String englishSentence = "";
        StringTokenizer wordsSentence = new StringTokenizer(sentence, " ");
        ArrayList<String> languageSentence = new ArrayList<>();
        // cele 3 variante
        String translatedSentence = "";
        String translatedSentence2 = "";
        String translatedSentence3 = "";
        while (wordsSentence.hasMoreTokens()) {
            languageSentence.add(wordsSentence.nextToken());
        }
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(fromLanguage)) {
                for (String wordFromSentence : languageSentence) {
                    for (Word w : dictionaries.get(fromLanguage).words) {
                        // creez propozitia in engleza
                        if (w.word.equals(wordFromSentence)) {
                            englishSentence = englishSentence + w.word_en + " ";
                            check++;
                            break;
                        }
                        for (String s : w.singular) {
                            if (s.equals(wordFromSentence)) {
                                englishSentence = englishSentence + w.word_en + " ";
                                check++;
                                break;
                            }
                        }
                        for (String p : w.plural) {
                            if (p.equals(wordFromSentence)) {
                                englishSentence = englishSentence + w.word_en + " ";
                                check++;
                                break;
                            }
                        }
                    }
                    if (check == 0)
                        englishSentence = englishSentence + wordFromSentence + " ";
                    else
                        check = 0;
                }
            }
        }
        StringTokenizer wordsSentence1 = new StringTokenizer(englishSentence, " ");
        ArrayList<String> languageSentence1 = new ArrayList<>();
        ArrayList<String> allTranslations = new ArrayList<>();
        while (wordsSentence1.hasMoreTokens()) {
            languageSentence1.add(wordsSentence1.nextToken());
        }
        // cu ajutorul cuvintelor rezultate din traducerea in engleza,
        // caut sinonimele fiecarui cuvant pe care il pot gasi in dictionar
        for (String dictLanguage1 : dictionaries.keySet()) {
            if (dictLanguage1.equals(toLanguage)) {
                for (String englishWord : languageSentence1) {
                    for (Word w : dictionaries.get(toLanguage).words) {
                        if (w.word_en.equals(englishWord)) {
                            for (Definition def : w.definitions) {
                                if (def.dictType.equals("synonyms")) {
                                    // daca am mai mult de 3 sinonime
                                    if (def.text.size() >= 3) {
                                        translatedSentence = translatedSentence + def.text.get(0) + " ";
                                        translatedSentence2 = translatedSentence2 + def.text.get(1) + " ";
                                        translatedSentence3 = translatedSentence3 + def.text.get(2) + " ";
                                        check++;
                                        break;
                                    } else if (def.text.size() == 2) { // daca am 2 sinonime
                                        translatedSentence = translatedSentence + def.text.get(0) + " ";
                                        translatedSentence2 = translatedSentence2 + def.text.get(1) + " ";
                                        check++;
                                        break;
                                    } else if (def.text.size() == 1) { // daca am 1 sinonim
                                        translatedSentence = translatedSentence + def.text.get(0) + " ";
                                        check++;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (check == 0) {
                        translatedSentence = translatedSentence + englishWord + " ";
                        translatedSentence2 = translatedSentence2 + englishWord + " ";
                        translatedSentence3 = translatedSentence3 + englishWord + " ";
                    } else {
                        check = 0;
                    }
                }
                // elimin spatiile de la sfarsitul propozitiilor traduse
                allTranslations.add(translatedSentence.substring(0, translatedSentence.length() - 1));
                allTranslations.add(translatedSentence2.substring(0, translatedSentence2.length() - 1));
                allTranslations.add(translatedSentence3.substring(0, translatedSentence3.length() - 1));
                return allTranslations;
            }
        }
        return null;
    }

    // Metodă pentru întoarcerea definițiilor și sinonimelor unui cuvânt
    ArrayList<Definition> getDefinitionsForWord(String word, String language) {
        // gasesc dictionarul cu limba potrivita
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                // gasesc cuvantul potrivit din dictionar
                for (Word w : dictionaries.get(dictLanguage).words) {
                    if (w.word.equals(word) || w.word_en.equals(word)) { // am gasit cuvantul in dictionar
                        // cazul in care cuvantul are definiti
                        if (!w.definitions.isEmpty()) {
                            // sortez definitiile dupa anul de aparitie
                            Collections.sort(w.definitions, new SortDefinitions());
                            System.out.println("Definitiile cuvantului " + word + " sunt:");
                            for (Definition d : w.definitions) {
                                if (d.dictType.equals("definitions")) {
                                    System.out.println("\t- " + d.text);
                                }
                            }
                            System.out.println("Sinonimele cuvantului " + word + " sunt:");
                            for (Definition d : w.definitions) {
                                if (d.dictType.equals("synonyms")) {
                                    System.out.println("\t- " + d.text);
                                }
                            }
                            return w.definitions;
                        } else { // cazul in care nu are nici o definitie
                            System.out.println("Cuvantul " + word + " nu are definitii sau sinonime!");
                            return null;
                        }
                    }
                }
            }
        }
        System.out.println("Cuvantul " + word + " nu se afla in dictionarul " + language + "!");
        return null;
    }

    // Metodă pentru exportarea unui dicționar în format JSON
    void exportDictionary(String language) {
        Gson gson = new Gson();
        for (String dictLanguage : dictionaries.keySet()) {
            if (dictLanguage.equals(language)) {
                for (Word word : dictionaries.get(dictLanguage).words) {
                    // oordonez dictionarele dupa ani
                    Collections.sort(word.definitions, new SortDefinitions());
                }
                String json = "";
                // oordonez cuvintele in dictionar alfabetic
                Collections.sort(dictionaries.get(dictLanguage).words, new SortWord());
                for (Word w : dictionaries.get(dictLanguage).words)
                    json = json + gson.toJson(w) + '\n';

                // creez un  nou fisier pentru fiecare nou dictionar
                try (FileWriter file = new FileWriter("../files_out/" + language + "_dict.json")) {
                    file.write(json);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}