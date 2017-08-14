package huffman.task2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Задача на программирование: декодирование Хаффмана
 * Восстановите строку по её коду и беспрефиксному коду символов.
 * В первой строке входного файла заданы два целых числа k и l через пробел — количество различных букв, встречающихся
 * в строке, и размер получившейся закодированной строки, соответственно. В следующих k строках записаны коды букв в
 * формате "letter: code". Ни один код не является префиксом другого. Буквы могут быть перечислены в любом порядке.
 * В качестве букв могут встречаться лишь строчные буквы латинского алфавита; каждая из этих букв встречается в строке
 * хотя бы один раз. Наконец, в последней строке записана закодированная строка. Исходная строка и коды всех букв
 * непусты. Заданный код таков, что закодированная строка имеет минимальный возможный размер.
 * В первой строке выходного файла выведите строку ss. Она должна состоять из строчных букв латинского алфавита.
 * Гарантируется, что длина правильного ответа не превосходит 10^4 символов.
 * <p>
 * Sample Input 1:
 * 1 1
 * a: 0
 * 0
 * Sample Output 1:
 * a
 * Sample Input 2:
 * 4 14
 * a: 0
 * b: 10
 * c: 110
 * d: 111
 * 01001100100111
 * Sample Output 2:
 * abacabad
 */
public class Main {

    private Map<Character, String> codes;
    private String word;

    private Main() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        codes = new HashMap<>(n);
        scanner.nextLine(); // пропуск до конца строки

        for (int i = 0; i < n; i++) {
            String[] split = scanner.nextLine().split(": ");
            codes.put(split[0].charAt(0), split[1]);
        }

        word = scanner.nextLine();
    }

    private void decode() {
        while (!word.isEmpty()) {
            codes.forEach((character, code) -> {
                if (word.startsWith(code)) {
                    System.out.print(character);
                    word = word.substring(code.length());
                }
            });
        }
    }

    public static void main(String[] args) {
        new Main().decode();
    }
}
