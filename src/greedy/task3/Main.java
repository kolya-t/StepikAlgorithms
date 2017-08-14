package greedy.task3;

import java.util.Scanner;

/**
 * Задача на программирование: различные слагаемые
 * По данному числу 1<=n<=10^9 найдите максимальное число k, для которого n можно представить как сумму k различных
 * натуральных слагаемых. Выведите в первой строке число k, во второй — k слагаемых.
 */
public class Main {

    private int n;

    private Main() {
        n = new Scanner(System.in).nextInt();
    }

    private void run() {
        int i = 1;
        int m = n;
        while (m >= 0) {
            m -= i++;
        }

        System.out.println(i - 2);
        for (int j = 1; j < i; j++) {
            if (j != -m) {
                System.out.print(j + " ");
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}