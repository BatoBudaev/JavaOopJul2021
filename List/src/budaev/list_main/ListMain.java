package budaev.list_main;

import budaev.list.SinglyLinkedList;

public class ListMain {

    public static void main(String[] args) {
        SinglyLinkedList<Integer> singlyLinkedList1 = new SinglyLinkedList<>();

        singlyLinkedList1.addFirstItem(50);
        singlyLinkedList1.addFirstItem(60);
        singlyLinkedList1.addFirstItem(70);
        singlyLinkedList1.addFirstItem(80);
        singlyLinkedList1.addItemByIndex(100, 3);
        singlyLinkedList1.setDataByIndex(10, 0);

        System.out.println("Первый элемент: " + singlyLinkedList1.getFirstItem());
        System.out.println(singlyLinkedList1);
        System.out.println("Удалён элемент: " + singlyLinkedList1.removeItemByIndex(4));
        System.out.println(singlyLinkedList1);

        int index = 3;
        System.out.println("Элемент по индексу " + index + " = " + singlyLinkedList1.getItemByIndex(index));

        int number = 70;
        System.out.println("Удалено число " + number + " - " + singlyLinkedList1.removeItemByData(number));
        System.out.println(singlyLinkedList1);

        SinglyLinkedList<Integer> singlyLinkedList2 = singlyLinkedList1.copy();
        singlyLinkedList2.setHead(singlyLinkedList2.reverse());

        System.out.println("Развернутый список: " + singlyLinkedList2);
    }
}