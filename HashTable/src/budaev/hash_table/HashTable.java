package budaev.hash_table;

import java.util.*;

public class HashTable<V> implements Collection<V> {
    private static final int DEFAULT_CAPACITY = 10;

    private final ArrayList<V>[] table;
    int size = 0;
    int modCount = 0;

    public HashTable() {
        //noinspection MoveFieldAssignmentToInitializer,unchecked
        table = (ArrayList<V>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость должна быть больше 0 : " + capacity);
        }

        //noinspection unchecked
        table = (ArrayList<V>[]) new ArrayList[capacity];
    }

    private int getIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.floorMod(o.hashCode(), table.length);
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

        return table[index] != null && table[index].contains(o);
    }

    private class MyHashTableIterator implements Iterator<V> {
        private int tableIndex = 0;
        private int currentIndex = -1;
        private int count = 0;
        private final int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public V next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException("В коллекции изменились элементы");
            }

            if (!hasNext()) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            while (table[tableIndex] == null || table[tableIndex].size() - 1 == currentIndex) {
                tableIndex++;
                currentIndex = -1;
            }

            currentIndex++;
            count++;

            return table[tableIndex].get(currentIndex);
        }
    }

    @Override
    public Iterator<V> iterator() {
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
    public boolean add(V v) {
        int index = getIndex(v);

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        table[index].add(v);

        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = getIndex(o);

        if (table[index] != null) {
            table[index].remove(o);

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
    public boolean addAll(Collection<? extends V> c) {
        if (c.size() == 0) {
            return false;
        }

        for (V e : c) {
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

        for (ArrayList<V> e : table) {
            if (e != null) {
                int elementSize = e.size();

                if (e.removeAll(c)) {
                    isRemoved = true;

                    size -= elementSize - e.size();
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

        for (ArrayList<V> e : table) {
            if (e != null) {
                int elementSize = e.size();

                if (e.retainAll(c)) {
                    isRemoved = true;

                    size -= elementSize - e.size();
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

        for (ArrayList<V> e : table) {
            if (e != null) {
                e.clear();
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

        for (ArrayList<V> e : table) {
            if (e == null) {
                i++;
                continue;
            }

            sb.append("{").append(i).append(" = ").append(e).append("}").append(System.lineSeparator());

            i++;
        }

        return sb.toString();
    }
}