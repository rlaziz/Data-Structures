package LinkedList;

import java.util.Objects;
public class LinkedList<T> {
    private class Node { // Structure which contains data and reference to the next item of the linked list
        private final T data; // Data itself
        private Node next; // Reference to the next item
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node head;
    private int length;

    public LinkedList() { // Create an empty linked list
        this.head = null;
        this.length = 0;
    }
    public LinkedList(T[] items){ // Create a linked list from given array of T objects
        if (items == null){
            throw new NullPointerException("Input array can't be null");
        }
        this.head = null;
        this.length = 0;
        for (T item : items) {
            this.add(item);
        }
    }

    public void add(T item){ // Add T item at the end
        Node newNode = new Node(item);
        if (this.head == null) { // Edge case
            this.head = newNode;
        } else {
            Node current = this.head;
            while (current.next != null) { // Iterate to the end
                current = current.next;
            }
            current.next = newNode;
        }
        this.length++;
    }
    public void add(T item, int index){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        Node newNode = new Node(item);
        if (index == 0) { // Edge case
            newNode.next = this.head;
            this.head = newNode;
        } else {
            Node current = this.head;
            for (int i = 0; i < index - 1; i++) { // Iterate to the input index
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        this.length++;
    }
    public T remove(){ // Remove the last item
        if (this.length == 0){ // Edge case
            return null;
        }
        Node current = this.head;
        while(current.next.next != null){ // Iterate to the parent of the last element
            current = current.next;
        }
        Node removed = current.next;
        current.next = null;
        this.length--;
        return removed.data;
    }
    public T remove(int index){ // Remove an item at given index
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        if (this.length == 0){ // Edge case
            return null;
        }
        Node removed;
        if(index == 0){
            removed = this.head;
            this.head = this.head.next;
        }else {
            Node current = this.head;
            for (int i = 0; i < index - 1; i++){ // Iterate to parent of the element we want to remove
                current = current.next;
            }
            removed = current.next;
            current.next = removed.next;
        }
        this.length--;
        return removed.data;
    }
    public T peek(){ // Get the data of the last item
        if (this.length == 0){ // Edge case
            return null;
        }
        Node current = this.head;
        while(current.next != null){ // Iterate to the item at the end
            current = current.next;
        }
        return current.data;
    }
    public T get(int index){ // Get the data of the item at given index
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        if (this.length == 0){ // Edge case
            return null;
        }
        Node current = this.head;
        for (int i = 0; i < index; i++){ // Iterate to the item at given index
            current = current.next;
        }
        return current.data;
    }
    public int indexOf(T data){ // Get index of the element containing given data
        if (this.length == 0){ // Edge case
            return -1;
        }
        Node current = this.head;
        int index = 0;
        while (current != null){
            if (Objects.equals(current.data,data)){ // Using this approach since data could be equal to null
                return index;
            }else {
                current = current.next;
                index ++;
            }
        }
        return index == this.length ? -1 : index; // If index is equal to the length of the list, then the item is not in the list, hence return -1, otw. return the index
    }
    public boolean contains(T data){ // Returns true if data in the list, false otw.
        return this.indexOf(data) != -1;
    }
    public int length(){
        return this.length;
    }

    @Override
    public String toString(){
        if(this.length == 0){
            return "[]";
        }
        String result = "[";
        Node current = this.head;
        int commasLeft = this.length - 1;
        while(current != null){
            result += current.data;
            result = (commasLeft > 0) ? result + ", " : result + "]";
            commasLeft--;
            current = current.next;
        }
        return result;
    }
}
