package ngordnet.main;

import edu.princeton.cs.algs4.In;

import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.HashSet;

public class WordNet {

    // wrapper for graph
    private Graph graph;

    private HashMap<Integer, List<String>> wordIDWord;
    private HashMap<String, List<Integer>> wordWordIDs;
    private HashMap<Integer, List<Integer>> wordIDHypIDs;

    public WordNet(String synsetsFile, String hyponymsFile) {
        graph = new Graph();

        // create a hashmap of word IDs and corresponding words
        wordIDWord = new HashMap<>();
        In synsetsReader = new In(synsetsFile);
        while (!synsetsReader.isEmpty()) {
            // parse through files
            String synsetsLine = synsetsReader.readLine();
            String[] synsetsArray = synsetsLine.split(",");
            int wordID = Integer.parseInt(synsetsArray[0]);
            String[] wordsList = synsetsArray[1].split(" ");
            ArrayList<String> wordsList2 = new ArrayList<>();
            Collections.addAll(wordsList2, wordsList);
            wordIDWord.put(wordID, wordsList2);
        }

        // create a hashmap of words and corresponding word IDs
        wordWordIDs = new HashMap<>();
        for (int ID : wordIDWord.keySet()) {
            List<String> wordList = wordIDWord.get(ID);
            for (String currWord : wordList) {
                List<Integer> IDList = new ArrayList<>();
                if (wordWordIDs.containsKey(currWord)) {
                    List<Integer> tempList = new ArrayList<>();
                    tempList.add(ID);
                    wordWordIDs.get(currWord).addAll(tempList);
                } else {
                    IDList.add(ID);
                    wordWordIDs.put(currWord, IDList);
                }
            }
        }

        // create hashmap with word IDs and corresponding hyponyms
        wordIDHypIDs = new HashMap<>();
        In hyponymsReader = new In(hyponymsFile);
        while (!hyponymsReader.isEmpty()) {
            String hyponymsLine = hyponymsReader.readLine();
            String[] hyponymsIDArray = hyponymsLine.split(",");
            int currWordID = Integer.parseInt(hyponymsIDArray[0]);
            int i = 1;
            List<Integer> currWordHyponyms = new ArrayList<>();
            while (hyponymsIDArray.length > i) {
                int currHyponymID = Integer.parseInt(hyponymsIDArray[i]);
                currWordHyponyms.add(currHyponymID);
                i++;
            }
            // make sure hyponyms are added to word buckets of existing keys
            if (wordIDHypIDs.containsKey(currWordID)) {
                wordIDHypIDs.get(currWordID).addAll(currWordHyponyms);
            } else {
                wordIDHypIDs.put(currWordID, currWordHyponyms);
            }
        }

        // build the graph -> add all the edges
        for (Integer currWordID : wordIDHypIDs.keySet()) {
            for (Integer currHypID : wordIDHypIDs.get(currWordID)) {
                graph.addEdge(currWordID, currHypID);
            }
        }
    }

    public ArrayList<String> hyponyms(String word) {
        List<Integer> allWordIDs = wordWordIDs.get(word);
        if (allWordIDs != null) {
            List<Integer> allHyponymIDs = new ArrayList<>(allWordIDs);
            // traverse through all word IDs for word
            for (Integer currWordID : allWordIDs) {
                allHyponymIDs.addAll(graph.traverseGraph(currWordID));
            }
            ArrayList<String> allHyponyms = new ArrayList<>();
            for (Integer IDtoWord : allHyponymIDs) {
                allHyponyms.addAll(wordIDWord.get(IDtoWord));
            }
            // @Source: https://stackoverflow.com/questions/203984/how-do-i-remove-repeated-elements-from-arraylist
            HashSet<String> allHyponymsHashSet = new HashSet<>(allHyponyms);
            allHyponyms.clear();
            allHyponyms.addAll(allHyponymsHashSet);
            Collections.sort(allHyponyms);
            return allHyponyms;
        }
        return new ArrayList<>();
    }
}

//        List<String> allHyponyms = new ArrayList<>();
//        // add word itself to list of hyponyms
//        allHyponyms.add(word);
//        // find all word IDs for given word
//        List<Integer> currWordIDs = new ArrayList<>();
//        currWordIDs = wordWordIDs.get(word);
//        // find all hyponym IDs for each word ID
//        List<Integer> allHyponymIDs = new ArrayList<>();
//        for (Integer currWordID : currWordIDs) {
//            List<Integer> currWordIDHyponymIDs = new ArrayList<>();
//            currWordIDHyponymIDs.addAll(wordIDHypIDs.get(currWordID));
//            allHyponymIDs.addAll(currWordIDHyponymIDs);
//        }
//        // convert all hyponym IDs to a string list of hyponyms
//        for (Integer hyponymID : allHyponymIDs) {
//            allHyponyms.addAll(wordIDWord.get(hyponymID));
//        }
//        allHyponyms.addAll(graphTraversal);
//        Collections.sort(allHyponyms);
//        return (ArrayList<String>) allHyponyms;
//    }

// create hashmap of words and corresponding hyponyms
//        wordsHyponyms = new HashMap<>();
//        In hyponymsReader = new In(hyponymsFile);
//        while (!hyponymsReader.isEmpty()) {
//            String hyponymsLine = hyponymsReader.readLine();
//            String[] hyponymsIDArray = hyponymsLine.split(",");
//            int currWordID = Integer.parseInt(hyponymsIDArray[0]);
//            String currWord = wordIDWord.get(currWordID);
//            int i = 1;
//            List<String> currWordHyponyms = new ArrayList<>();
//            while (hyponymsIDArray.length > i) {
//                int currHyponymID = Integer.parseInt(hyponymsIDArray[i]);
//                String currHyponym = wordIDWord.get(currHyponymID);
//                currWordHyponyms.add(currHyponym);
//                i++;
//            }
//            // make sure hyponyms are added to word buckets of existing keys
//            if (wordsHyponyms.containsKey(currWord)) {
//                wordsHyponyms.get(currWord).addAll(currWordHyponyms);
//            } else {
//                wordsHyponyms.put(currWord, currWordHyponyms);
//            }
//        }

//public class WordNet {
//
//    // wrapper for graph
//    private Graph graph;
//
//    // build the graph -> add all the edges
//    private HashMap<Integer, String> wordIDWord;
//    private HashMap<Integer, List<Integer>> wordIDHypIDs;
//
//    public WordNet(String synsetsFile, String hyponymsFile) {
//        graph = new Graph();
//
//        // create a hashmap of word IDs and corresponding words
//        wordIDWord = new HashMap<>();
//        In synsetsReader = new In(synsetsFile);
//        while (!synsetsReader.isEmpty()) {
//            String synsetsLine = synsetsReader.readLine();
//            String[] synsetsArray = synsetsLine.split(",");
//            int wordID = Integer.parseInt(synsetsArray[0]);
//            String word = synsetsArray[1];
//            wordIDWord.put(wordID, word);
//        }
//
//        // create hashmap with words and corresponding hyponyms
//        wordIDHypIDs = new HashMap<>();
//        In hyponymsReader = new In(hyponymsFile);
//        while (!hyponymsReader.isEmpty()) {
//            String hyponymsLine = hyponymsReader.readLine();
//            String[] hyponymsIDArray = hyponymsLine.split(",");
//            int currWordID = Integer.parseInt(hyponymsIDArray[0]);
//            int i = 1;
//            List<Integer> currWordHyponyms = new ArrayList<>();
//            while (hyponymsIDArray.length > i) {
//                int currHyponymID = Integer.parseInt(hyponymsIDArray[i]);
//                currWordHyponyms.add(currHyponymID);
//                i++;
//            }
//            // make sure hyponyms are added to word buckets of existing keys
//            if (wordIDHypIDs.containsKey(currWordID)) {
//                wordIDHypIDs.get(currWordID).addAll(currWordHyponyms);
//            } else {
//                wordIDHypIDs.put(currWordID, currWordHyponyms);
//            }
//        }
//        for (Integer key: wordIDHypIDs.keySet()) {
//            for (Integer value: wordIDHypIDs.get(key)) {
//                graph.addEdge(wordIDWord.get(key), wordIDWord.get(value));
//            }
//        }
//    }
//
//    public ArrayList<String> hyponyms(String word) {
//        return graph.traverseGraph(word);
//    }
//}