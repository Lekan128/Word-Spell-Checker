package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

class WordPredictorTest {
    HashMap<String, Integer> actualHashMap = new HashMap<>();
    String word;
    ArrayList<ArrayList<String>> arrayListArrayList;

    @BeforeEach
    void setUp(){
        word = "big";
        actualHashMap.put("big", 1);
        actualHashMap.put("fat", 2);
        actualHashMap.put("blag", 3);
        actualHashMap.put("dig", 4);
        actualHashMap.put("bag", 5);

        ArrayList<String> arrayList0 = new ArrayList<>(Arrays.asList("", "big"));
        ArrayList<String> arrayList1 = new ArrayList<>(Arrays.asList("b", "ig"));
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList("bi", "g"));
        ArrayList<String> arrayList3 = new ArrayList<>(Arrays.asList("big", ""));

        arrayListArrayList = new ArrayList<>(Arrays.asList(arrayList0, arrayList1, arrayList2, arrayList3));

    }

    @Test
    void addToHashMap() {
        HashMap<String, Integer> expectedHashMap = new HashMap<>();
        expectedHashMap.put("big", 2);
        expectedHashMap.put("fat", 2);
        expectedHashMap.put("blag", 3);
        expectedHashMap.put("dig", 4);
        expectedHashMap.put("bag", 5);

        WordPredictor.addToHashMap(word, actualHashMap);
        Assertions.assertEquals(expectedHashMap, actualHashMap,
                "simple addition of 1 to the value an element already present");

//        Assertions.assertAll("Adding to hashMap",
//                ()-> Assertions.assertEquals(expectedHashMap, actualHashMap,
//                        "simple addition of 1 to the value an element already present"),
//                ()-> Assertions.assertEquals(expectedHashMap, actualHashMap,
//                        "adding an element not present yet to the Hashmap")
//        );

        WordPredictor.addToHashMap("notIn", actualHashMap);
        expectedHashMap.put("notIn", 1);

        Assertions.assertEquals(expectedHashMap, actualHashMap,
                        "adding an element not present yet to the Hashmap");

        WordPredictor.addToHashMap("", actualHashMap);

        Assertions.assertEquals(expectedHashMap, actualHashMap, "adding an empty string");



    }

    @Test
    void sum() {
        Set<Integer> actualSet = Set.of(4,5,10, 1);
        Assertions.assertEquals(20.0, WordPredictor.sum(actualSet), "simple sum of elements in a set");
        Assertions.assertEquals(0, WordPredictor.sum(Set.of()), "sum with no element in the set");
    }

    @Test
    void p() {
        double expectedValue = 1.0/(1+2+3+4+5);

        Assertions.assertEquals(expectedValue, WordPredictor.P(word, actualHashMap, null),
                "sum without summation");

        Assertions.assertEquals(expectedValue, WordPredictor.P(word, actualHashMap,
                WordPredictor.sum(actualHashMap.values())), "sum with summation");

        Assertions.assertEquals(0, WordPredictor.P("", actualHashMap,
                WordPredictor.sum(actualHashMap.values())), "empty word");

        Assertions.assertEquals(0, WordPredictor.P(word, new HashMap<>(),
                WordPredictor.sum(actualHashMap.values())), "hashmap does not contain the word / new hash map");

    }

    @Test
    void correction() {
        word = "biag";

        Assertions.assertEquals("bag", WordPredictor.correction(word, actualHashMap));
    }

    @Test
    void candidates() {
        word = "biag";

        Assertions.assertEquals(Set.of("bag", "big", "blag"), WordPredictor.candidates(word, actualHashMap));
        Assertions.assertEquals(Set.of(""), WordPredictor.candidates("", actualHashMap));


    }

    @Test
    void known() {
        Assertions.assertEquals(Set.of("big", "bag"), WordPredictor.known(Set.of("big", "black", "bag"), actualHashMap));
        Assertions.assertEquals(Set.of(), WordPredictor.known(Set.of(), actualHashMap));
        Assertions.assertTrue(WordPredictor.known(Set.of(), actualHashMap).isEmpty());

    }

    @Test
    void removeFirstNChars() {
        Assertions.assertEquals("g", WordPredictor.removeFirstNChars(word, 2));
        Assertions.assertEquals(word, WordPredictor.removeFirstNChars(word, 0));
        Assertions.assertEquals("", WordPredictor.removeFirstNChars(word, 3));
        Assertions.assertEquals("", WordPredictor.removeFirstNChars(word, 4));
//        Class<? extends Throwable> IndexOutOfBoundsException = new  java.lang.IndexOutOfBoundsException;
//        Assertions.assertThrows(IndexOutOfBoundsException , WordPredictor.removeFirstNChars(word, -1));




    }

    @Test
    void showFirstNChars() {
        Assertions.assertEquals("bi", WordPredictor.showFirstNChars(word, 2));
        Assertions.assertEquals("", WordPredictor.showFirstNChars(word, 0));
        Assertions.assertEquals(word, WordPredictor.showFirstNChars(word, 3));
        Assertions.assertEquals(word, WordPredictor.showFirstNChars(word, 4));
//
    }

    @Test
    void splitTheWordIntoItsCharacters() {

        Assertions.assertEquals(arrayListArrayList, WordPredictor.splitTheWordIntoItsCharacters(word));
    }

    @Test
    void deletes() {
        Assertions.assertEquals(new ArrayList<String>(Arrays.asList("ig", "bg", "bi")), WordPredictor.deletes(arrayListArrayList));
    }

    @Test
    void transposes() {
        Assertions.assertEquals(new ArrayList<String>(Arrays.asList("ibg", "bgi")), WordPredictor.transposes(arrayListArrayList));
    }

    @Test
    void replaces() {
//        Assertions.assertEquals(new ArrayList<String>(Arrays.asList("aig", "big", "bag", "bbg", "bia", "bib")), WordPredictor.replaces(arrayListArrayList));
    }

    @Test
    void inserts() {
    }

    @Test
    void edits1() {
    }

    @Test
    void edits2() {
    }
}