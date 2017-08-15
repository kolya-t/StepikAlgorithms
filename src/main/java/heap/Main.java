package heap;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class Main {

    public static final String EXTRACT_MAX = "ExtractMax";
    public static final String INSERT = "Insert";

    private Scanner scanner = new Scanner(System.in);
    private Queue<Integer> queue = new Queue<>();

    public void run() {
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String command = scanner.next();
            switch (command) {
                case EXTRACT_MAX:
                    System.out.println(queue.extractMax());
                    break;
                case INSERT:
                    queue.insert(scanner.nextInt());
                    break;
                default:
                    throw new IllegalArgumentException(format("Illegal command '%s'", command));
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

    /**
     * Очередь на основе max-кучи на основе бинарного дерева
     */
    public class Queue<T extends Comparable<T>> {

        private List<T> array = new ArrayList<>();

        public void insert(T e) {
            array.add(e);
            siftUp(array.size() - 1);
        }

        public T extractMax() {
            if (array.isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }

            T max = array.get(0);
            array.set(0, array.get(array.size() - 1));
            array.remove(array.size() - 1);

            siftDown(0);

            return max;
        }

        private void siftUp(int index) {
            int parentIndex = Math.round(index / 2f) - 1; // индекс родителя элемента
            // если у элемента есть родитель, т.е. он не является корневым
            if (parentIndex >= 0) {
                T element = array.get(index);
                T parent = array.get(parentIndex);
                // если элемент больше родителя, то нужно вытолкнуть его вверх
                if (element.compareTo(parent) == 1) {
                    array.set(index, parent);
                    array.set(parentIndex, element);
                    siftUp(parentIndex);
                }
            }
        }

        private void siftDown(int index) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;

            if (leftChildIndex < array.size()) {
                T element = array.get(index);
                T leftChild = array.get(leftChildIndex);
                T rightChild = null;

                boolean leftIsMore = element.compareTo(leftChild) == -1;
                boolean rightIsMore = false;

                if (rightChildIndex < array.size()) {
                    rightChild = array.get(rightChildIndex);
                    rightIsMore = (element.compareTo(rightChild) == -1) && (leftChild.compareTo(rightChild) != 1);
                }

                if (rightIsMore) {
                    array.set(index, rightChild);
                    array.set(rightChildIndex, element);
                    siftDown(rightChildIndex);
                } else if (leftIsMore) {
                    array.set(index, leftChild);
                    array.set(leftChildIndex, element);
                    siftDown(leftChildIndex);
                }
            }
        }
    }

}
