package ngordnet.main;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TestWordNet {
    @Test
    public void testHyponymsSimple() {
        WordNet wn = new WordNet("./data/wordnet/synsets11.txt", "./data/wordnet/hyponyms11.txt");
        assertEquals(List.of("actifed", "antihistamine"), wn.hyponyms("antihistamine"));
        System.out.println(wn.hyponyms("antihistamine"));
    }

    @Test
    public void testHyponymsComplex() {
        WordNet wn = new WordNet("./data/wordnet/synsets16.txt", "./data/wordnet/hyponyms16.txt");
        ArrayList<String> occurenceHyponyms = new ArrayList<>();
        occurenceHyponyms = wn.hyponyms("occurrence");
        ArrayList<String> changeHyponyms = new ArrayList<>();
        changeHyponyms = (wn.hyponyms("change"));
        System.out.println(occurenceHyponyms);
        System.out.println(changeHyponyms);
//        assertEquals(List.of("alteration, change, demotion, increase, jump, leap, modification, saltation, transition, variation"), wn.hyponyms("change"));
//        ArrayList<String> retainedList = new ArrayList<>();
//        retainedList = wn.hyponyms("occurrence");
//        ArrayList<String> changeHyponyms = new ArrayList<>();
//        changeHyponyms = (wn.hyponyms("change"));
//        retainedList.retainAll(changeHyponyms);
//        System.out.println(changeHyponyms);
    }
}
