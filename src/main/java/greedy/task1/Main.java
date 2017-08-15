package greedy.task1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Задача на программирование: покрыть отрезки точками
 * По данным n отрезкам необходимо найти множество точек минимального размера,
 * для которого каждый из отрезков содержитхотя бы одну из точек.
 * В первой строке дано число 1<=n<=100 отрезков. Каждая из последующих n строк содержит по два числа 0<=l<=r<=10^9,
 * задающих начало и конец отрезка. Выведите оптимальное число m точек и сами m точек. Если таких множеств точек
 * несколько, выведите любое из них.
 */
public class Main {

    private List<Segment> segments = new ArrayList<>();

    private Main() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            Segment segment = new Segment(scanner.nextInt(), scanner.nextInt());
            segments.add(segment);
        }
    }

    private List<Integer> getCoveringDots() {
        segments.sort(Comparator.comparingInt(Segment::getRight));

        List<Integer> dots = new ArrayList<>();
        for (Segment segment : segments) {
            for (Integer dot : dots) {
                if (segment.contains(dot)) {
                    segment.mark();
                    break;
                }
            }
            if (segment.isMarked()) continue;

            dots.add(segment.getRight());
        }

        return dots;
    }

    public static void main(String[] args) {
        List<Integer> dots = new Main().getCoveringDots();
        System.out.println(dots.size());
        dots.forEach(dot -> System.out.print(dot + " "));
    }

    private class Segment {

        private int left;
        private int right;
        private boolean marked;

        public Segment(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public boolean isMarked() {
            return marked;
        }

        public void mark() {
            this.marked = true;
        }

        public boolean contains(int x) {
            return (left <= x) && (x <= right);
        }
    }

}
