package fibonacci.task1;

import java.io.IOException;
import java.util.Scanner;

/**
 * Дано целое число 1<=n<=40, необходимо вычислить n-е число Фибоначчи
 */
public class Main {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        int t;
        for (int i = 2; i <= n; i++) {
            t = f0;
            f0 = f1;
            f1 = t + f1;
        }
        return f1;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(fib(n));
    }
}
