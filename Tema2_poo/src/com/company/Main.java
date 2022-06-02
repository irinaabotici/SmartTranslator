package com.company;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("../files");
        File[] files = file.listFiles();
        AllDictionaries allDictionaries = new AllDictionaries();
        String language = null;
        Gson gson;
        for (File f : files) {
            gson = new Gson();
            StringTokenizer tokens = new StringTokenizer(f.getName(), "\\_");
            language = tokens.nextToken();
            Dictionary dictionary = new Dictionary();
            allDictionaries.addDictionary(language, dictionary); // adauga dictionarul si limba sa
            String allText = new String(Files.readAllBytes(Paths.get(f.getPath())));
            Word[] allWords = gson.fromJson(allText, Word[].class);
            // adaug cuvintele deja existente in dictionare
            for (int i = 0; i < allWords.length; i++) {
                allDictionaries.addWord(allWords[i], language);
            }
        }

        // seturile de substantive la singular sau plural pentru cuvinte
        ArrayList<String> singular1 = new ArrayList<>();
        singular1.add("frumos");
        ArrayList<String> plural1 = new ArrayList<>();
        plural1.add("frumosi");
        plural1.add("frumoase");

        ArrayList<String> singular2 = new ArrayList<>();
        singular2.add("veux");
        singular2.add("veut");
        ArrayList<String> plural2 = new ArrayList<>();
        plural2.add("voulez");

        ArrayList<String> singular3 = new ArrayList<>();
        singular3.add("inteligente");
        ArrayList<String> plural3 = new ArrayList<>();

        ArrayList<String> singular4 = new ArrayList<>();
        ArrayList<String> plural4 = new ArrayList<>();

        ArrayList<String> singular5 = new ArrayList<>();
        singular5.add("doreste");
        singular5.add("doresti");
        ArrayList<String> plural5 = new ArrayList<>();
        plural5.add("doresc");

        ArrayList<String> singular6 = new ArrayList<>();
        singular6.add("mananca");
        singular6.add("manance");
        ArrayList<String> plural6 = new ArrayList<>();
        plural6.add("mancati");

        ArrayList<String> singular7 = new ArrayList<>();
        singular7.add("es");
        singular7.add("est");
        ArrayList<String> plural7 = new ArrayList<>();
        plural7.add("sommes");

        ArrayList<String> singular8 = new ArrayList<>();
        singular8.add("intelligente");
        ArrayList<String> plural8 = new ArrayList<>();
        plural8.add("intelligents");

        ArrayList<String> singular9 = new ArrayList<>();
        singular9.add("gata");
        ArrayList<String> plural9 = new ArrayList<>();

        // creez campul text pentru definitii
        ArrayList<String> text1 = new ArrayList<>();
        text1.add("Care place pentru armonia liniilor, mișcărilor, culorilor");
        text1.add("care are valoare estetică; estetic");

        ArrayList<String> text2 = new ArrayList<>();
        text2.add("avoir la ferme intention, le souhait, le désir de");
        text2.add(" Avoir de la volonté, faire preuve de volonté");

        ArrayList<String> text3 = new ArrayList<>();
        text3.add("inteligente");
        text3.add("afilado");
        text3.add("listo");
        text3.add("bueno");

        ArrayList<String> text4 = new ArrayList<>();
        text4.add("Categorie fundamentală a esteticii prin care se reflectă însușirea omului de a " +
                "simți emoție în fața operelor de artă, a fenomenelor și a obiectelor naturii");

        ArrayList<String> text5 = new ArrayList<>();
        text5.add("vrea");
        text5.add("aspira");
        text5.add("tinde");
        text5.add("pofteste");

        // adaug definitii pentru cuvinte
        Definition definition1 = new Definition("Dicționarul explicativ al limbii române (ediția a II-a revăzută și adăugită)",
                                            "definitions", 2016, text1);
        Definition definition2 = new Definition("Larousse", "definitions", 1931, text2);
        Definition definition3 = new Definition("Diccionario de sinónimos", "synonyms", 2000, text3);
        Definition definition4 = new Definition("Dicționar universal al limbei române, ediția a VI-a)", "definitions",
                                                1993, text4);
        Definition definition5 = new Definition("Dicționar de sinonime", "synonyms", 1943, text5);

        ArrayList<Definition> definitions1 = new ArrayList<>();
        ArrayList<Definition> definitions2 = new ArrayList<>();
        definitions2.add(definition2);
        ArrayList<Definition> definitions3 = new ArrayList<>();
        ArrayList<Definition> definitions4 = new ArrayList<>();
        ArrayList<Definition> definitions5 = new ArrayList<>();
        definitions5.add(definition5);
        ArrayList<Definition> definitions6 = new ArrayList<>();
        ArrayList<Definition> definitions7 = new ArrayList<>();
        ArrayList<Definition> definitions8 = new ArrayList<>();
        ArrayList<Definition> definitions9 = new ArrayList<>();

        Word word1 = new Word("frumos", "beautiful", "noun", singular1, plural1, definitions1);
        Word word2 = new Word("vouloir", "want", "verb", singular2, plural2, definitions2);
        Word word3 = new Word("inteligente", "smart", "noun", singular3, plural3, definitions3);
        Word word4 = new Word("merge", "walk", "verb", singular4, plural4, definitions4);
        Word word5 = new Word("dori", "want", "verb", singular5, plural5, definitions5);
        Word word6 = new Word("mananca", "eat", "verb", singular6, plural6, definitions6);
        Word word7 = new Word("être", "be", "verb", singular7, plural7, definitions7);
        Word word8 = new Word("intelligente", "smart", "noun", singular8, plural8, definitions8);
        Word word9 = new Word("gato", "cat", "noun", singular9, plural9, definitions9);

        Dictionary dictionary = new Dictionary();
        allDictionaries.addDictionary("esp", dictionary);

        // first word added
        allDictionaries.addWord(word1, "ro");
        // second word added
        allDictionaries.addWord(word2, "fr");
        // third word added
        allDictionaries.addWord(word3, "esp");
        // fourth word added
        allDictionaries.addWord(word4, "ro");
        // fifth word added
        allDictionaries.addWord(word5, "ro");
        // sixth word added
        allDictionaries.addWord(word6, "ro");
        // seventh word added
        allDictionaries.addWord(word7, "fr");
        // eighth word added
        allDictionaries.addWord(word8, "fr");
        // nineth word added
        allDictionaries.addWord(word9, "esp");

        // adaugarea definitiilor pentru cuvinte
        allDictionaries.addDefinitionForWord("frumos", "ro", definition1);
        allDictionaries.addDefinitionForWord("vouloir", "fr", definition2);
        allDictionaries.addDefinitionForWord("inteligente", "esp", definition3);
        allDictionaries.addDefinitionForWord("frumos", "ro", definition4);
        System.out.println();

        // stergerea cuvintelor
        allDictionaries.removeWord("jeu", "fr");
        allDictionaries.removeWord("arbol", "ro");
        System.out.println();

        // stergerea definitiei unui cuvant
        allDictionaries.removeDefinition(word4.word, "ro", "Micul dicționar academic, ediția a II-a");
        allDictionaries.removeDefinition(word2.word, "ro", "Dicționar universal al limbei române, ediția a VI-a)");
        System.out.println();

        // traducerea unui cuvant
        String translatedWord1 = allDictionaries.translateWord("pisică", "ro", "fr");
        System.out.println(translatedWord1);
        String translatedWord2 = allDictionaries.translateWord("merge", "ro", "fr");
        System.out.println(translatedWord2);
        String translatedWord3 = allDictionaries.translateWord("frumos", "ro", "en");
        System.out.println(translatedWord3);
        String translatedWord4 = allDictionaries.translateWord("eat", "en", "fr");
        System.out.println(translatedWord4);
        System.out.println();


        // intoarcerea definitiilor si sinonimelor unui cuvant
        ArrayList<Definition> wordDefinitions1 = allDictionaries.getDefinitionsForWord("câine", "ro");
        System.out.println();
        ArrayList<Definition> wordDefinitions2 = allDictionaries.getDefinitionsForWord("vouloir", "fr");
        System.out.println();
        ArrayList<Definition> wordDefinitions3 = allDictionaries.getDefinitionsForWord("merge", "fr");
        System.out.println();
        // am luat in considerare si cazul in care cuvantul este dat in engleza
        ArrayList<Definition> wordDefinitions4 = allDictionaries.getDefinitionsForWord("eat", "fr");
        System.out.println();

        // traducerea propozitiilor
        String translatedSentence1 = allDictionaries.translateSentence("pisica nu doreste sa manance", "ro", "fr");
        System.out.println(translatedSentence1);
        String translatedSentence2 = allDictionaries.translateSentence("le chat n'est pas inteligent", "fr", "esp");
        System.out.println(translatedSentence2);
        System.out.println();

        // lista de traduceri posibile
        ArrayList<String> t = allDictionaries.translateSentences("frumoase pisici se duc sa manance", "ro", "fr");
        System.out.println(t);
        ArrayList<String> t2 = allDictionaries.translateSentences("bunicului ii este foarte foame si vrea sa manance",
                                                            "ro", "fr");
        System.out.println(t2);
        System.out.println();

        // exportarea unui dictionar in JSON
        allDictionaries.exportDictionary("ro");
        allDictionaries.exportDictionary("fr");
        allDictionaries.exportDictionary("esp");
    }
}
