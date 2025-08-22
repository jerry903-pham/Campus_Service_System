package src.datastructures;

@SuppressWarnings("unchecked")
public class MyHashTable<K, V> {

    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Entry<K, V>[] buckets;
    private int capacity;
    private int size;

    public MyHashTable() {
        this(16); // default capacity
    }

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        this.buckets = (Entry<K, V>[]) new Entry[capacity];
        this.size = 0;
    }

    private int hash(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode())) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> current = buckets[index];

        // Update existing key
        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                current.value = value;
                return;
            }
            current = current.next;
        }

        // Insert new entry at head
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        size++;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if ((key == null && current.key == null) || (key != null && key.equals(current.key))) {
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        return get(key) != null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
