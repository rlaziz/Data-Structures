package Queue;

import java.util.Objects;

public class Queue<T> {
    private class Node {
        private Node next;
        private T data;
        public Node(T data){
            this.data = data;
            this.next = null;
        }
    }

    private Node root;
    private int length;

    public Queue(){
        this.root = null;
        this.length = 0;
    }
    public Queue(T[] items){
        this();
        if (items == null){
            throw new NullPointerException("Input array can't be null");
        }
        for (T item : items) {
            this.add(item);
        }
    }
    public void add(T item){
        Node newNode = new Node(item);
        if (this.root == null){
            this.root = newNode;
        }else {
            Node current = this.root;
            while(current.next != null){
                current = current.next;
            }
            current.next = newNode;
        }
        this.length();
    }
    public void add(T item, int index){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        Node newNode = new Node(item);
        if (index == 0) { // Edge case
            newNode.next = this.root;
            this.root = newNode;
        } else {
            Node current = this.root;
            for (int i = 0; i < index - 1; i++) { // Iterate to the input index
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        this.length++;
    }
    public T remove(){
        if (this.root == null){
            return null;
        }
        T removed = this.root.data;
        this.root = this.root.next;
        this.length--;
        return removed;
    }
    public boolean remove(T data){
        int index = this.indexOf(data);
        if (index == -1){
            return false;
        }
        this.remove(index);
        return true;
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
            removed = this.root;
            this.root = this.root.next;
        }else {
            Node current = this.root;
            for (int i = 0; i < index - 1; i++){ // Iterate to parent of the element we want to remove
                current = current.next;
            }
            removed = current.next;
            current.next = removed.next;
        }
        this.length--;
        return removed.data;
    }
    public T peek(){
        if (this.root == null){ // Edge case
            return null;
        }
        return this.root.data;
    }
    public int indexOf(T data){ // Get index of the element containing given data

        Node current = this.root;
        int index = 0;
        while (current != null){
            if (Objects.equals(current.data,data)){ // Using this approach since data could be equal to null
                return index;
            }else {
                current = current.next;
                index ++;
            }
        }
        return -1; // If index is equal to the length of the list, then the item is not in the list, hence return -1, otw. return the index
    }
    public boolean contains(T data){ // Returns true if data in the list, false otw.
        return this.indexOf(data) != -1;
    }
    public int length(){
        return this.length;
    }
    public void set(int index, T value){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        Node required = this.getNode(index);
        required.data = value;
    }
    private Node getNode(int index){
        Node temp = this.root;
        while(index > 0){
            temp = temp.next;
            index--;
        }
        return temp;
    }
    @Override
    public String toString(){
        if(this.length == 0){
            return "[]";
        }
        String result = "[";
        Node current = this.root;
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
