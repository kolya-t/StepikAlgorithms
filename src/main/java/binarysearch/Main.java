package binarysearch;

import java.util.Scanner;

public class Main {

    private void run() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Integer[] array = new Integer[n];
        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextInt();
        }

        int k = scanner.nextInt();
        for (int i = 0; i < k; i++) {
            System.out.print(getIndexOf(array, scanner.nextInt()) + " ");
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

    public static <T extends Comparable<T>> int getIndexOf(T[] array, T element) {
        int result = -1;

        if (array.length != 0) {
//            Arrays.sort(array);
            int l = 0;
            int r = array.length - 1;

            while (l <= r) {
                int m = l + (r - l) / 2;

                if (array[m].equals(element)) {
                    result = m + 1; // на самом деле просто m, но по условию задачи нумерация идет с 1, а не с 0
                    break;
                } else if (array[m].compareTo(element) == 1) {
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        return result;
    }
}
