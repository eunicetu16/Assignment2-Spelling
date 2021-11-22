import java.util.ArrayList;
import java.util.HashMap;

public class Trie {
    Node root = new Node();

    //add string and count into the trie
    public void add (String word, long count) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int offset = ch - 'a';
            Node child = node.children[offset];
            if (child == null) {
                node.children[offset] = new Node();
                child = node.children[offset];
            }
            node = child;
        }
        node.count = count;
    }

    //use the string to find the count
    public long search (String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int offset = ch - 'a';
            Node child = node.children[offset];
            if (child == null) {
                return 0;
            }
            node = child;
        }
        return node.count;
    }

    public Node searchNodeWithPrefix (String word) {
        Node node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int offset = ch - 'a';
            Node child = node.children[offset];
            if (child == null) {
                return null;
            }
            node = child;
        }
        return node;
    }
    public void addFrequentWord(String word, ArrayList<String> frequentWords, HashMap<String, Long> suggestWordsMap, int frequentCount) {
        for (int j = 0; j < frequentWords.size(); j++) {
            if (suggestWordsMap.get(frequentWords.get(j)) < suggestWordsMap.get(word)) {
                frequentWords.add(j, word);
                if (frequentWords.size() > frequentCount) {
                    frequentWords.remove(frequentWords.size() - 1);
                }
                return;
            }
        }
        if (frequentWords.size() < frequentCount) {
            frequentWords.add(word);
        }
    }
    public HashMap<String, Long> suggest(String prefix, int frequentCount) {
        // First find the node with prefix
        Node prefixNode = searchNodeWithPrefix(prefix);

        // Search all words with the prefix and store <word, count> into the map
        HashMap<String, Long> suggestWordsMap = new HashMap<String, Long>();
        suggestFromNode(prefixNode, prefix, suggestWordsMap);

        // Store most frequent count words
        ArrayList<String> frequentWords = new ArrayList<>();
        for (String word: suggestWordsMap.keySet()) {
            addFrequentWord(word, frequentWords, suggestWordsMap, frequentCount);
        }

        HashMap<String, Long> frequentWordsMap = new HashMap<>();
        for (String word: frequentWords) {
            frequentWordsMap.put(word, suggestWordsMap.get(word));
        }
        return frequentWordsMap;
    }

    public void suggestFromNode(Node node, String word, HashMap<String, Long> suggestWordsMap) {
        if ((node == null) || (node.count == 0 && node.children.length == 0)) {
            return;
        }

        if (node.count > 0) {
            suggestWordsMap.put(word, node.count);
        }

        for (int i = 0; i < 26; i++) {
            Node child = node.children[i];
            if (child == null) {
                continue;
            }
            char ch = (char) ('a' + i);
            suggestFromNode(child, word+ch, suggestWordsMap);
        }
    }

}
