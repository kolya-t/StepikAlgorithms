package greedy.task2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Задача на программирование: непрерывный рюкзак
 * Первая строка содержит количество предметов 1<=n<=10^3 и вместимость рюкзака 0<=W<=2*10^6. Каждая из следующих
 * n строк задаёт стоимость 0<=c[i]<=2*10^6 и объём 0<=w[i]<=2*10^6 предмета (n, W, c[i], w[i] — целые числа).
 * Выведите максимальную стоимость частей предметов (от каждого предмета можно отделить любую часть, стоимость и объём
 * при этом пропорционально уменьшатся), помещающихся в данный рюкзак, с точностью не менее трёх знаков после запятой.
 */
public class Main {

    private List<Item> items;
    private int weightLeft; // сколько еще может поместиться в рюкзак

    private Main() {
        Scanner scanner = new Scanner(System.in);
        int itemsCount = scanner.nextInt();
        items = new ArrayList<>(itemsCount);
        weightLeft = scanner.nextInt();
        for (int i = 0; i < itemsCount; i++) {
            items.add(new Item(scanner.nextInt(), scanner.nextInt()));
        }
    }

    public static void main(String[] args) {
        System.out.printf("%.3f", new Main().getMaxCost());
    }

    private double getMaxCost() {
        // сортировка по убыванию цены за кг
        items.sort(Comparator.comparingDouble(item -> 1 / item.getPrice()));
        double cost = 0;
        for (Item item : items) {
            if (weightLeft <= 0) break;

            cost += getMaxCostOfItem(item);
            weightLeft -= item.getWeight();
        }
        return cost;
    }

    private double getMaxCostOfItem(Item item) {
        return (item.getWeight() < weightLeft)
                ? item.getPrice() * item.getWeight()
                : item.getPrice() * weightLeft;
    }

    private class Item {

        private double price; // цена за килограмм
        private int weight; // кол-во килограммов

        public Item(int cost, int weight) {
            this.price = 1. * cost / weight;
            this.weight = weight;
        }

        public double getPrice() {
            return price;
        }

        public int getWeight() {
            return weight;
        }
    }

}