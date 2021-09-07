package budaev.list;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("Длина списка равна 0");
        }

        return head.getData();
    }

    public T getByIndex(int index) {
        checkIndex(index);

        return getItemByIndex(index).getData();
    }

    public void setByIndex(int index, T data) {
        checkIndex(index);

        getItemByIndex(index).setData(data);
    }

    public T removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> currentItem = head.getNext();
        ListItem<T> previousItem = head;

        for (int i = 1; i < index; i++) {
            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        previousItem.setNext(currentItem.getNext());

        count--;

        return currentItem.getData();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void addByIndex(int index, T data) {
        if (index < 0 || index > getCount()) {
            throw new IndexOutOfBoundsException("Невозможно вставить по индексу " + index + ". Допустимые значения [0-" + (count) + "]");
        }

        if ((head == null) || (index == 0)) {
            addFirst(data);
            return;
        }

        ListItem<T> currentItem = head;

        for (int i = 1; i < index; i++) {
            currentItem = currentItem.getNext();
        }

        ListItem<T> newItem = new ListItem<>(data, currentItem.getNext());
        currentItem.setNext(newItem);

        count++;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Длина списка равна 0");
        }

        T deletedData = head.getData();
        head = head.getNext();

        count--;

        return deletedData;
    }

    public boolean removeByData(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        boolean isRemoved = false;

        while (currentItem != null) {
            if (currentItem.getData() == data) {
                if (currentItem.equals(head)) {
                    head = head.getNext();
                } else {
                    assert previousItem != null;
                    previousItem.setNext(currentItem.getNext());
                }

                isRemoved = true;
                count--;

                break;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return isRemoved;
    }

    public void reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        newList.count = count;

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            ListItem<T> newItem = new ListItem<>(p.getData(), p.getNext());

            if (newList.head == null) {
                newList.head = newItem;
            }
        }

        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы списка [0-" + (count - 1) + "]");
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            sb.append(p.getData());

            if (p.getNext() != null) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}