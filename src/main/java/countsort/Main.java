package countsort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Сортировка подсчётом
 * Первая строка содержит число 1≤<=n<=10^4, вторая — n натуральных чисел, не превышающих 10. Выведите упорядоченную по
 * неубыванию последовательность этих чисел.
 * <p>
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * Sample Output:
 * 2 2 3 9 9
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[scanner.nextInt()];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        for (int i : sort(array)) {
            System.out.print(i + " ");
        }
    }

    public static int[] sort(int[] a) {
        int[] result = new int[a.length];
        int[] b = new int[11];
        Arrays.fill(b, 0);

        for (int j = 0; j < a.length; ++j) {
            ++b[a[j]];
        }

        for (int i = 1; i < b.length; i++) {
            b[i] += b[i - 1];
        }

        for (int j = a.length - 1; j >= 0; j--) {
            result[b[a[j]] - 1] = a[j];
            --b[a[j]];
        }

        return result;
    }
}
