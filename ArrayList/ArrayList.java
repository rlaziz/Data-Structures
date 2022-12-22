package ArrayList;
import java.util.Arrays;
import java.util.Objects;
public class ArrayList<T> {
    private Object[] items;
    private int length;
    public ArrayList(){
        this.items = new Object[0]; // default capacity
    }
    public void add(T item){
        if (this.length == items.length){
            this.expand();
        }
        this.items[this.length] = item;
        this.length++;
    }
    public void add(T item, int index){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        if (this.length == items.length){
            this.expand();
        }
        for (int i = this.length; i > index; i--){
            this.items[i] = this.items[i-1];
        }
        this.items[index] = item;
        this.length++;
    }
    public T remove(){ // removes Item at the end
        if (this.length == 0){ // Edge case
            return null;
        }
        T removed = (T) this.items[this.length - 1];
        this.items[this.length - 1] = null;
        this.length--;
        this.shrink();
        return removed;
    }
    public boolean remove(T item){
        int index = this.indexOf(item);
        if (index == -1){
            return false;
        }
        this.remove(index);
        return true;
    }
    public T remove(int index){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        T removed = (T) this.items[index];
        this.items[index] = null;
        for (int i = this.length; i > index; i--){
            this.items[i-1] = this.items[i];
        }
        this.length--;
        this.shrink();
        return removed;
    }

    public T peek(){
        if (this.length == 0){
            return null;
        }
        return (T) this.items[0];
    }

    public T get(int index){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        if (this.length == 0){
            return null;
        }
        return (T) this.items[index];
    }
    public void set(int index, T value){
        if (index < 0 || index > this.length) { // Handle bad index input
            throw new IndexOutOfBoundsException("Index has to be valid");
        }
        this.items[index] = value;
    }

    public int indexOf(T item) {
        for (int i = 0; i < this.length; i++){
            if (Objects.equals(item,this.items[i])){
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T data){ // Returns true if data in the list, false otw.
        return this.indexOf(data) != -1;
    }
    public int length(){
        return this.length;
    }
    private void expand(){
        Object[] temp = new Object[this.length + 1];
        System.arraycopy(this.items, 0, temp, 0, this.items.length);
        this.items = temp;
    }
    private void shrink(){
        Object[] temp = new Object[this.length];
        System.arraycopy(this.items, 0, temp, 0, this.length);
        this.items = temp;
    }

    public Object[] asArray(){
        return Arrays.copyOf(this.items,this.length);
    }
    @Override
    public String toString(){
        String result = "[";
        int commasLeft = this.length - 1;
        for (Object item : this.items){
            result += item;
            result = (commasLeft > 0) ? result + ", " : result + "]";
            commasLeft--;
        }
        return result;
    }
}
