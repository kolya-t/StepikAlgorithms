package fibonacci.task2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Дано число 1<=n<=10^7, необходимо найти последнюю цифру n-го числа Фибоначчи.
 * Как мы помним, числа Фибоначчи растут очень быстро, поэтому при их вычислении нужно быть аккуратным с переполнением.
 * В данной задаче, впрочем, этой проблемы можно избежать, поскольку нас интересует только последняя цифра числа
 * Фибоначчи: если 0<=a,b<=9 — последние цифры чисел F[i] и F[i+1] соответственно, то (a+b) mod 10 — последняя цифра
 * числа F[i+2].
 */
public class Main {

    public static int last(int n) {
        if (n <= 1) {
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        int t;
        for (int i = 2; i <= n; i++) {
            t = f0;
            f0 = f1;
            f1 = (t + f1) % 10;
        }
        return f1;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println(last(n));
    }
}
