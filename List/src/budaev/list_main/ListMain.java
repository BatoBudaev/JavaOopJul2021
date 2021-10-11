package budaev.list_main;

import budaev.list.SinglyLinkedList;

public class ListMain {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList1 = new SinglyLinkedList<>();

        singlyLinkedList1.addFirst(40);
        singlyLinkedList1.addFirst(50);
        singlyLinkedList1.addFirst(60);
        singlyLinkedList1.addFirst(70);
        singlyLinkedList1.addFirst(80);
        singlyLinkedList1.addByIndex(3, 100);
        System.out.println("Заменён элемент: " + singlyLinkedList1.setByIndex(4, 10));

        System.out.println("Первый элемент: " + singlyLinkedList1.getFirst());
        System.out.println(singlyLinkedList1);
        System.out.println("Удалён элемент: " + singlyLinkedList1.removeByIndex(3));
        System.out.println(singlyLinkedList1);

        int index = 2;
        System.out.println("Элемент по индексу " + index + " = " + singlyLinkedList1.getByIndex(index));

        int number = 70;
        System.out.println("Удалено число " + number + " - " + singlyLinkedList1.removeByData(number));
        System.out.println(singlyLinkedList1);
        SinglyLinkedList<Integer> singlyLinkedList2 = singlyLinkedList1.copy();
        singlyLinkedList2.reverse();

        System.out.println("Развернутый список: " + singlyLinkedList2);
        System.out.println("Неразвернутый список: " + singlyLinkedList1);

        SinglyLinkedList<Integer> singlyLinkedList3 = new SinglyLinkedList<>();
        singlyLinkedList3.addFirst(50);
        singlyLinkedList3.addFirst(null);
        singlyLinkedList3.addFirst(51);
        singlyLinkedList3.addFirst(52);

        System.out.println(singlyLinkedList3);
        singlyLinkedList3.removeByData(null);
        System.out.println(singlyLinkedList3);
    }
}