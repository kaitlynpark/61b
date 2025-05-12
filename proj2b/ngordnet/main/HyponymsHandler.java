package ngordnet.main;

import ngordnet.hugbrowsermagic.NgordnetQuery;
import ngordnet.hugbrowsermagic.NgordnetQueryHandler;
import ngordnet.ngrams.NGramMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class HyponymsHandler extends NgordnetQueryHandler {
    private WordNet wn;
    private NGramMap ngm;

    public HyponymsHandler(WordNet wn, NGramMap ngm) {
        this.wn = wn;
        this.ngm = ngm;
    }

    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();
        // return if there are no words in list
        if (words.isEmpty()) {
            return null;
        }
        ArrayList<String> hyponymsList = wn.hyponyms(words.get(0));
        // iterate through every word in words and find their hyponyms to retain by
        for (String word : words) {
            ArrayList<String> wordHyponyms = wn.hyponyms(word);
            hyponymsList.retainAll(wordHyponyms);
        }
        if (k == 0) {
            return hyponymsList.toString();
        } else {
            HashMap<Double, String> hm = new HashMap<>();
            ArrayList<Double> dataSumList = new ArrayList<>();
            ArrayList<Double> kList = new ArrayList<>();
            ArrayList<String> returnList = new ArrayList<>();

            for (int i = 0; i < hyponymsList.size(); i++) {
                List<Double> data = ngm.countHistory(hyponymsList.get(i), startYear, endYear).data();
                // @Source: https://www.baeldung.com/java-stream-sum
                Double dataSum = data.stream().mapToDouble(Double::doubleValue).sum();
                if (dataSum != 0) {
                    dataSumList.add(dataSum);
                    hm.put(dataSum, hyponymsList.get(i));
                }
            }

            // sorts by popularity; descending order
            Collections.sort(dataSumList, Collections.reverseOrder());
            for (int i = 0; i < k; i++) {
                if (dataSumList.size() <= i) {
                    continue;
                }
                kList.add(dataSumList.get(i));
            }
            // converts doubles to strings
            for (double d : kList) {
                returnList.add(hm.get(d));
            }
            Collections.sort(returnList);
            return returnList.toString();
        }
    }
}
