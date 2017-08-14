package gcd;

import java.util.Scanner;

/**
 * По данным двум числам 1<=a,b<=2*10^9 найдите их наибольший общий делитель.
 */
public class Main {

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        if (b == 0) {
            return a;
        }
        if (a <= b) {
            return gcd(a, b % a);
        }
        return gcd(a % b, b);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(gcd(scanner.nextInt(), scanner.nextInt()));
    }
}
