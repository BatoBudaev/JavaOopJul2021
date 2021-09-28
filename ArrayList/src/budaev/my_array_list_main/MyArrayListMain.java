package budaev.my_array_list_main;

import budaev.my_array_list.MyArrayList;

public class MyArrayListMain {
    public static void main(String[] args) {
        MyArrayList<Integer> myList1 = new MyArrayList<>();
        myList1.ensureCapacity(25);

        for (int i = 0; i < 25; i++) {
            myList1.add(i);
        }

        System.out.println(myList1);
        System.out.println("Размер списка = " + myList1.size());

        MyArrayList<Integer> myList2 = new MyArrayList<>(5);
        myList2.add(0, 100);
        myList2.add(1, 200);
        myList2.add(2, 300);
        myList2.add(3, 200);
        myList2.add(4, 400);
        myList2.trimToSize();
        int index = 1;

        System.out.println(myList2);
        System.out.println("Число по индексу " + index + " = " + myList2.get(index));
        System.out.println("Замена числа по индексу " + index + " = " + myList2.set(index, 99));
        System.out.println("Список пуст: " + myList2.isEmpty());
        System.out.println("Список содержит число 300: " + myList2.contains(300));

        Object[] array = myList2.toArray();

        for (Object obj : array) {
            System.out.print(obj + " ");
        }

        Integer[] integerArray = new Integer[myList2.size()];
        integerArray = myList2.toArray(integerArray);

        for (Integer number : integerArray) {
            System.out.print(number + " ");
        }

        System.out.println();

        Object number = 20;
        System.out.println("Удален элемент " + number + ": " + myList1.remove(number));
        System.out.println("Удален элемент по индексу 15 = " + myList1.remove(15));

        MyArrayList<Integer> myList3 = new MyArrayList<>(10);

        for (int i = 0; i < 10; i++) {
            myList3.add(i + 15);
        }

        System.out.println(myList3);
        System.out.println("Список содержит другой список: " + myList3.containsAll(myList1));
        System.out.println("Список из общих элементов коллекций");
        myList3.retainAll(myList1);
        System.out.println(myList3);

        System.out.println("Добавление списка к другому списку");
        myList2.addAll(myList3);
        System.out.println(myList2);

        System.out.println("Добавление списка к другому списку по индексу");
        myList2.addAll(2, myList3);
        System.out.println(myList2);

        System.out.println("Удаление элементов списка из другого списка");
        myList2.add(20);
        myList2.add(3, 20);
        System.out.println("myList2 " + myList2);
        System.out.println("myList3 " + myList3);
        myList2.removeAll(myList3);
        System.out.println("myList2 " + myList2);

        System.out.println("Очистка списка");
        myList1.clear();

        Integer asd = null;
        MyArrayList<Integer> myList4 = new MyArrayList<>(-0);
        myList4.add(0, 100);
        myList4.add(asd);
        myList4.add(1, 102);
        myList4.add(2, 103);

        System.out.println("Индекс элемента " + asd + " = " + myList4.indexOf(asd));
        System.out.println(myList4);
    }
}