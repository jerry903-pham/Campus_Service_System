package src.datastructures;

@SuppressWarnings("unchecked")
public class MyArray<T> {
    private T[] data;
    private int size;

    public MyArray() {
        this(10); // default capacity
    }

    public MyArray(int capacity) {
        if (capacity <= 0) capacity = 10;
        data = (T[]) new Object[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }

    public void add(T value) {
        ensureCapacity(size + 1);
        data[size++] = value;
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        data[index] = value;
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return removed;
    }

    private void ensureCapacity(int capacity) {
        if (capacity <= data.length) return;
        int newCap = Math.max(capacity, data.length * 2);
        T[] newData = (T[]) new Object[newCap];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
