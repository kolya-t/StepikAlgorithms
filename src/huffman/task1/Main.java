package huffman.task1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * По данной непустой строке s длины не более 10^4, состоящей из строчных букв латинского алфавита,
 * постройте оптимальный беспрефиксный код. В первой строке выведите количество различных букв k, встречающихся в
 * строке, и размер получившейся закодированной строки. В следующих k строках запишите коды букв в формате
 * "letter: code". В последней строке выведите закодированную строку.
 * <p>
 * Sample Input 1:
 * a
 * <p>
 * Sample Output 1:
 * 1 1
 * a: 0
 * 0
 * Sample Input 2:
 * abacabad
 * <p>
 * Sample Output 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 */
public class Main {

    private String word;
    private Queue queue = new Queue();
    private Map<Character, String> codes = new HashMap<>();

    private Main() {
        word = new Scanner(System.in).nextLine();
        word.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .forEach((c, i) -> queue.insert(new TreeNode(c, Math.toIntExact(i))));
    }

    private void huffman() {
        int n = queue.getSize();

        for (int i = n + 1; i < 2 * n; i++) {
            TreeNode first = queue.extractMin();
            TreeNode second = queue.extractMin();
            queue.insert(new TreeNode(first, second));
        }
        traversing(queue.extractMin());
        String encodedWord = encode(word);

        System.out.println(n + " " + encodedWord.length());
        codes.forEach((character, code) -> System.out.println(character + ": " + code));
        System.out.println(encodedWord);
    }

    private String encode(String word) {
        return word.chars()
                .mapToObj(value -> codes.get((char) value))
                .reduce((s1, s2) -> s1 + s2)
                .orElse("");
    }

    private void traversing(TreeNode node) {
        if ((node.getLeftChild() == null) && (node.getRightChild() == null)) {
            traversing(node, "0");
        } else {
            traversing(node, "");
        }
    }

    private void traversing(TreeNode node, String prefix) {
        if ((node.getLeftChild() == null) && (node.getRightChild() == null)) {
            codes.put(node.getCharacter(), prefix);
        } else {
            traversing(node.getLeftChild(), prefix + "0");
            traversing(node.getRightChild(), prefix + "1");
        }
    }

    public static void main(String[] args) {
        new Main().huffman();
    }

    public class Queue {

        private List<TreeNode> priorities = new ArrayList<>();

        public void insert(TreeNode node) {
            priorities.add(node);
        }

        public TreeNode extractMin() {
            TreeNode minimumPriority = priorities.stream()
                    .min(Comparator.comparingInt(TreeNode::getFrequency))
                    .orElse(null);

            if (minimumPriority != null) {
                priorities.remove(minimumPriority);
            }

            return minimumPriority;
        }

        public int getSize() {
            return priorities.size();
        }
    }

    public class TreeNode {

        private Character character;
        private Integer frequency;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(Character character, Integer frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public TreeNode(TreeNode leftChild, TreeNode rightChild) {
            this.character = (char) (leftChild.getCharacter() + rightChild.getCharacter());
            this.frequency = leftChild.getFrequency() + rightChild.frequency;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Character getCharacter() {
            return character;
        }

        public Integer getFrequency() {
            return frequency;
        }

        public TreeNode getLeftChild() {
            return leftChild;
        }

        public TreeNode getRightChild() {
            return rightChild;
        }
    }
}
