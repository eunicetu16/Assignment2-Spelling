import java.io.*;
import java.util.*;

public class Spelling {
    Trie trie = new Trie();
    Spelling(String filePath) {
        ArrayList<String[]> lineSegmentList = null;
        try {
            lineSegmentList = importInput(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 1; i < lineSegmentList.size(); i++) {
            trie.add(lineSegmentList.get(i)[0], Long.parseLong(lineSegmentList.get(i)[1]));
        }
    }

    public ArrayList<List<String>> suggest(String token, int count) {

        ArrayList<List<String>> resultList = new ArrayList<List<String>>();
        String prefix = "";
        for(int i = 0; i < token.length(); i++) {
                char ch = token.charAt(i);
                String currentPrefix = prefix;
                prefix += ch;
                HashMap<String, Long> resultMap = trie.suggest(prefix, count);
                int remaining = count - resultMap.size();
                while (remaining > 0) {
                    ArrayList<String> prefixList = findMostFrequentPrefixList(currentPrefix, remaining, ch);
                    int index = 0;
                    while (remaining > 0 && index < prefixList.size()) {
                        HashMap<String, Long> remainingList = trie.suggest(prefixList.get(index), count);
                        for (String word : remainingList.keySet()) {
                            if (!resultMap.containsKey(word)) {
                                resultMap.put(word, remainingList.get(word));
                            }
                        }

                        remaining = count - resultMap.size();
                        index++;
                    }
                    currentPrefix = currentPrefix.substring(0, currentPrefix.length()-1);
                    ch = ' ';
                }
                ArrayList<String> sortedList = getSortedKeyFromMap(resultMap);
                resultList.add(sortedList.subList(0, count));
        }
//        for (int i = 0; i < resultList.size(); i++) {
//            System.out.print(resultList.get(i));
//            System.out.print('\n');
//        }
        System.out.print(resultList);
        return resultList;
    }

    public ArrayList<String> findMostFrequentPrefixList(String token, int requireCount, char excludeChar) {
        HashMap<String, Long> suggestWordsMap = new HashMap<String, Long>();
        for (char i = 0; i < 26; i++) {
            char ch = (char) (i + 'a');
            if (ch == excludeChar) {
                continue;
            }
            String prefix = token+ch;
            long prefixCount  = this.trie.search(prefix);
            suggestWordsMap.put(prefix, prefixCount);
        }

        return getSortedKeyFromMap(suggestWordsMap);
    }

    public ArrayList<String> getSortedKeyFromMap(HashMap<String, Long> suggestWordsMap) {
        Set<Map.Entry<String, Long>> set = suggestWordsMap.entrySet();
        ArrayList<Map.Entry<String, Long>> resultsEntry = new ArrayList<Map.Entry<String, Long>>(set);
        Collections.sort(resultsEntry, new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        ArrayList<String> results = new ArrayList<String>();
        for (int i = 0; i < resultsEntry.size(); i++) {
            results.add(resultsEntry.get(i).getKey());
        }

        return results;
    }

    public ArrayList<String[]> importInput(String fileName) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(fileName));
        String line;
        String[] lineSegment;
        ArrayList<String[]> lineSegmentList = new ArrayList<>();

        while(scan.hasNextLine())
        {
            line = scan.nextLine();
            lineSegment = line.split(","); // stores all input in array of Strings
            lineSegmentList.add(lineSegment);
        }
        return lineSegmentList;
    }
}