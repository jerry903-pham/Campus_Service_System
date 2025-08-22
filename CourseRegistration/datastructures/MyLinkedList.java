package src.datastructures;

public class MyLinkedList<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head;
    private int size;

    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) head = node;
        else {
            Node<T> cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = node;
        }
        size++;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.data;
    }

    public void remove(int index) {
        checkIndex(index);
        if (index == 0) head = head.next;
        else {
            Node<T> cur = head;
            for (int i = 0; i < index - 1; i++) cur = cur.next;
            cur.next = cur.next.next;
        }
        size--;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
    }
}
