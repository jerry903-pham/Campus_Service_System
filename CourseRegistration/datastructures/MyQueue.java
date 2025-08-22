package src.datastructures;

public class MyQueue<T> {
    private static class Node<T> {
        T data;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> front, rear;
    private int size;

    public void enqueue(T data) {
        Node<T> node = new Node<>(data);
        if (rear == null) front = rear = node;
        else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    public T dequeue() {
        if (front == null) return null;
        T val = front.data;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return val;
    }

    public T peek() { return front == null ? null : front.data; }
    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }
}
