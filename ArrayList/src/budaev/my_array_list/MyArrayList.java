package budaev.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int capacity = 10;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public MyArrayList(T[] items, int capacity) {
        this.items = items;
        this.capacity = capacity;
        size = items.length;
    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        //noinspection unchecked
        items = (T[]) new Object[10];
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }

        modCount++;
    }

    public void ensureCapacity(int minCapacity) {
        if (minCapacity > items.length) {
            capacity = minCapacity;
            @SuppressWarnings("UnnecessaryLocalVariable")
            T[] expandedArray = Arrays.copyOf(items, capacity);
            items = expandedArray;
        }

        modCount++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    private class MyListIterator implements Iterator<T> {
        private int currentIndex = -1;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            checkForConcurrentModificationException();

            if (currentIndex >= size) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            ++currentIndex;

            return items[currentIndex];
        }

        final void checkForConcurrentModificationException() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException("В колекции изменились элементы");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {

        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;

    }


    @Override
    public boolean add(T e) {
        modCount++;

        if (size == items.length) {
            resize(capacity);
        }

        items[size] = e;
        size++;

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndexForAdd(index);
        modCount++;
        size++;

        if (size >= items.length) {
            resize(capacity);
        }

        System.arraycopy(items, index, items, index + 1, size - index);

        items[index] = element;
    }

    public void resize(int capacity) {
        this.capacity = capacity * 2 + 1;
        @SuppressWarnings("UnnecessaryLocalVariable")
        T[] expandedArray = Arrays.copyOf(items, this.capacity);
        items = expandedArray;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        modCount++;
        remove(index);

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] collectionArray = c.toArray();
        int newSize = collectionArray.length;

        if (newSize == 0) {
            return false;
        }

        if (newSize > items.length - size) {
            resize(newSize);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collectionArray, 0, items, size, newSize);
        size += newSize;
        modCount++;

        return true;

    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexForAdd(index);
        Object[] collectionArray = c.toArray();
        int newSize = collectionArray.length;

        if (newSize == 0) {
            return false;
        }

        if (newSize > items.length - size) {
            resize(newSize);
        }

        int numMoved = size - index;

        if (numMoved > 0) {
            System.arraycopy(items, index, items, index + newSize, numMoved);
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(collectionArray, 0, items, index, newSize);

        size += newSize;
        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;

        for (Object e : c) {
            if (remove(e)) {
                isRemoved = true;

            }
        }

        modCount++;
        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (int i = 0; i < size; i++) {
            Object obj = get(i);

            if (!c.contains(obj)) {
                remove(i);
                isRemoved = true;
                i--;
            }
        }

        modCount++;

        return isRemoved;
    }

    @Override
    public void clear() {
        modCount++;

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index + " отсутствует. Границы списка [0-" + (size - 1) + "]");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Невозможно вставить по индексу " + index + ". Допустимые значения [0-" + (size) + "]");
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        T oldValue = items[index];
        items[index] = element;

        return oldValue;
    }


    @Override
    public T remove(int index) {
        checkIndex(index);

        modCount++;
        T oldValue = items[index];
        int newSize = size - 1;
        System.arraycopy(items, index + 1, items, index, newSize - index);
        items[newSize] = null;
        size--;

        return oldValue;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (o.equals(items[i])) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(get(i));

            if (size != i + 1) {
                sb.append(", ");
            }
        }

        sb.append("]");

        return sb.toString();
    }
}