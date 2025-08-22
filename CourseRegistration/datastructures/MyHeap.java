package src.datastructures;

public class MyHeap<T> {
    private Object[] data;
    private int size;
    private final MyComparator<T> comparator;

    public MyHeap(int capacity, MyComparator<T> comparator) {
        data = new Object[capacity];
        this.comparator = comparator;
        this.size = 0;
    }

    public void insert(T value) {
        ensureCapacity(size + 1);
        data[size] = value;
        heapifyUp(size);
        size++;
    }

    public T extract() {
        if (size == 0) return null;
        T root = (T) data[0];
        data[0] = data[--size];
        data[size] = null;
        heapifyDown(0);
        return root;
    }

    public T peek() { return size == 0 ? null : (T) data[0]; }
    public int size() { return size; }

    private void ensureCapacity(int required) {
        if (required > data.length) {
            Object[] newData = new Object[data.length * 2];
            for (int i = 0; i < size; i++) newData[i] = data[i];
            data = newData;
        }
    }

    private void heapifyUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (comparator.compare((T) data[index], (T) data[parent]) < 0) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void heapifyDown(int index) {
        while (true) {
            int left = index * 2 + 1, right = index * 2 + 2, smallest = index;
            if (left < size && comparator.compare((T) data[left], (T) data[smallest]) < 0) smallest = left;
            if (right < size && comparator.compare((T) data[right], (T) data[smallest]) < 0) smallest = right;
            if (smallest != index) {
                swap(index, smallest);
                index = smallest;
            } else break;
        }
    }

    private void swap(int i, int j) {
        Object tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
