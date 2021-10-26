package budaev.hash_table_main;

import budaev.hash_table.HashTable;

public class HashTableMain {
    public static void main(String[] args) {
        HashTable<Integer> table1 = new HashTable<>();
        table1.add(123);
        table1.add(222);
        table1.add(333);
        table1.add(1);
        table1.add(2);
        table1.add(3);
        table1.add(5);
        table1.add(6);
        table1.add(7);
        table1.add(500);
        table1.add(1000);
        table1.add(7777);

        System.out.println(table1);
        System.out.println("Размер таблицы = " + table1.size());
        System.out.println("Таблица пуста: " + table1.isEmpty());
        System.out.println("Список содержит число 300: " + table1.contains(300));

        for (Object obj : table1) {
            System.out.print(obj + " ");
        }

        System.out.println();

        Object number = 123;
        System.out.println("Удален элемент " + number + ": " + table1.remove(number));

        Object[] array = table1.toArray();

        for (Object obj : array) {
            System.out.print(obj + " ");
        }

        System.out.println();

        HashTable<Integer> table2 = new HashTable<>(20);

        for (int i = 1; i < 4; i++) {
            table2.add(i);
        }

        System.out.println(table2);

        System.out.println("Таблица содержит другую таблицу: " + table1.containsAll(table2));
        System.out.println("Список из общих элементов коллекций");
        table1.retainAll(table2);
        System.out.println(table1);
        System.out.println("Размер таблицы = " + table1.size());

        System.out.println("Добавление таблицы к другой таблице");
        table1.addAll(table2);
        System.out.println(table1);

        HashTable<Integer> table3 = new HashTable<>(5);
        table3.add(1);

        System.out.println("Удаление таблицы из другой таблицы");
        table1.removeAll(table3);
        System.out.println(table1);

        System.out.println("Очистка таблицы");
        table1.clear();
        System.out.println(table1);
    }
}