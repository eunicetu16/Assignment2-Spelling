public class Node {
    Node[] children = new Node[26];
    long count = 0;

    Node() {
        count = 0;
        for (int i = 0; i < 26; i++ ) {
            children[i] = null;
        }
    }
}
