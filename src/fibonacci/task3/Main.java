package fibonacci.task3;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Даны целые числа 1<=n<=10^18 и 2<=m<=10^5, необходимо найти остаток от деления n-го числа Фибоначчи на m.
 */
public class Main {

    private static List<BigInteger> mods;

    private static BigInteger mod(BigInteger n, BigInteger m) {
        if (n.compareTo(BigInteger.ONE) != 1) {
            return n;
        }

        mods = new ArrayList<>();
        BigInteger f0 =  BigInteger.ZERO;
        BigInteger f1 = BigInteger.ONE;
        BigInteger t;
        mods.add(BigInteger.ZERO);
        mods.add(BigInteger.ONE);
        for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) != 1; i = i.add(BigInteger.ONE)) {
            t = f0;
            f0 = f1;
            f1 = f1.add(t).mod(m);
            mods.add(f1);

            if (f0.equals(BigInteger.ZERO) && f1.equals(BigInteger.ONE)) {
                return mods.get(n.mod(BigInteger.valueOf(mods.size() - 2)).intValue());
            }
        }
        return f1;
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println(mod(scanner.nextBigInteger(), scanner.nextBigInteger()));
    }
}
