package budaev.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 10;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection MoveFieldAssignmentToInitializer,unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0. Введённое значение: " + capacity);
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.floorMod(o.hashCode(), lists.length);
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
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    private class MyHashTableIterator implements Iterator<E> {
        private int arrayIndex;
        private int listIndex = -1;
        private int count;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public E next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("В коллекции изменились элементы");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            while (lists[arrayIndex] == null || lists[arrayIndex].size() - 1 == listIndex) {
                arrayIndex++;
                listIndex = -1;
            }

            listIndex++;
            count++;

            return lists[arrayIndex].get(listIndex);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyHashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (Object e : this) {
            array[i] = e;

            i++;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E element) {
        int index = getIndex(element);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(element);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
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
    public boolean addAll(Collection<? extends E> c) {
        if (c.size() == 0) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        boolean isRemoved = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int listInitialSize = list.size();

                if (list.removeAll(c)) {
                    isRemoved = true;

                    size -= listInitialSize - list.size();
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRemoved = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int listInitialSize = list.size();

                if (list.retainAll(c)) {
                    isRemoved = true;

                    size -= listInitialSize - list.size();
                }
            }
        }

        if (isRemoved) {
            modCount++;
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();

        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list == null) {
                i++;
                continue;
            }

            sb.append("{").append(i).append(" = ").append(list).append("}").append(System.lineSeparator());

            i++;
        }

        return sb.toString();
    }
}