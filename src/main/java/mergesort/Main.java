package mergesort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Число инверсий.
 * Первая строка содержит число 1<=n<=10^5, вторая — массив A[1…n]A[1…n], содержащий натуральные числа, не
 * превосходящие 10^9. Необходимо посчитать число пар индексов 1<=i<=j<=n, для которых A[i]>A[j]. (Такая пара элементов
 * называется инверсией массива. Количество инверсий в массиве является в некотором смысле его мерой неупорядоченности:
 * например, в упорядоченном по неубыванию массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
 * инверсию образуют каждые два элемента.)
 * Sample Input:
 * 5
 * 2 3 9 2 9
 * Sample Output:
 * 2
 */
public class Main {

    private int[] array;
    private static long count;

    private Main() {
        Scanner scanner = new Scanner(System.in);
        array = new int[scanner.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
    }

    private void run() {
        mergeSort(array);
        System.out.println(count);
    }

    public static void main(String[] args) {
        new Main().run();
    }

    private static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }

        return merge(mergeSort(Arrays.copyOfRange(array, 0, array.length / 2)),
                mergeSort(Arrays.copyOfRange(array, array.length / 2, array.length)));
    }

    private static int[] merge(int[] array1, int[] array2) {
        int[] resultArray = new int[array1.length + array2.length];
        int i1 = 0; // счетчик в первом массиве
        int i2 = 0; // счетчик во втором массиве

        for (int i = 0; i < array1.length + array2.length; i++) {
            if ((i1 < array1.length) && (i2 < array2.length)) {
                if (array2[i2] < array1[i1]) {
                    count += array1.length - i1;
                    resultArray[i] = array2[i2++];
                } else {
                    resultArray[i] = array1[i1++];
                }
            } else if (i1 < array1.length) {
                resultArray[i] = array1[i1++];
            } else {
                resultArray[i] = array2[i2++];
            }
        }

        return resultArray;
    }
}
