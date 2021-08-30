package budaev.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
    }

    public int getSize() {
        return count;
    }

    public T getFirstItem() {
        return head.getData();
    }

    public T getItemByIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Нет такого индекса");
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item.getData();
    }

    public void setDataByIndex(T data, int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Нет такого индекса");
        }

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        item.setData(data);
    }

    public T removeItemByIndex(int index) {
        if (index < 0 || index >= getSize()) {
            throw new IndexOutOfBoundsException("Нет такого индекса");
        }

        if (index == 0) {
            return removeFirstItem();
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

    public void addFirstItem(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void addItemByIndex(T data, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным");
        }

        if (head == null) {
            head = new ListItem<>(data);
        } else {
            if (index == 0) {
                addFirstItem(data);
                return;
            }

            ListItem<T> newItem = new ListItem<>(data, head);
            ListItem<T> currentItem = head;

            for (int i = 1; i < index; i++) {
                currentItem = currentItem.getNext();
            }

            newItem.setNext(currentItem.getNext());
            currentItem.setNext(newItem);
        }

        count++;
    }

    public T removeFirstItem() {
        if (count == 0) {
            throw new IndexOutOfBoundsException("Пустой лист");
        }

        T headData = head.getData();
        head = head.getNext();

        count--;

        return headData;
    }

    public boolean removeItemByData(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;
        boolean isDeleted = false;

        while (currentItem != null) {
            if (currentItem.getData() == data) {
                if (currentItem == head) {
                    head = head.getNext();
                } else {
                    assert previousItem != null;
                    previousItem.setNext(currentItem.getNext());
                }
                isDeleted = true;

                count--;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }
        return isDeleted;
    }

    public ListItem<T> reverse() {
        ListItem<T> previousItem = null;
        ListItem<T> currentItem = head;

        while (currentItem != null) {
            ListItem<T> nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        return previousItem;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();

        for (ListItem<T> p = head; p != null; p = p.getNext()) {
            newList.addItemByIndex(p.getData(), newList.count);
        }

        return newList;
    }

    public String toString() {
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