package Dictionary;

import java.util.Objects;

public class Dictionary<E,T>{
    private Object[] keys;
    private Object[] values;
    private int length;

    public Dictionary(){
        this.keys = new Object[0];
        this.values = new Object[0];
    }
    public void add(E key, T value){
        if (this.length == this.keys.length){
            this.expand();
        }
        this.keys[this.length] = key;
        this.values[this.length] = value;
        this.length++;
    }
    public T remove(E key){
        int index = this.indexOf(key);
        if (index == -1){
            return null;
        }
        T removed = (T) this.values[index];
        this.keys[index] = null;
        for (int i = this.length; i > index; i--){
            this.keys[i-1] = this.keys[i];
            this.values[i-1] = this.values[i];
        }
        this.length--;
        this.shrink();
        return removed;
    }
    public T get(E key){
        int index = this.indexOf(key);
        if (index == -1){
            return null;
        }
        return (T) this.values[index];
    }
    public boolean set(E key, T newValue){
        int index = this.indexOf(key);
        if (index == -1){
            return false;
        }
        this.values[index] = newValue;
        return true;
    }
    public Object[] getKeys(){
        return this.keys;
    }
    public Object[] getValues(){
        return this.values;
    }
    private int indexOf(E key){
        for (int i = 0; i < this.length; i++){
            if (Objects.equals(this.keys[i],key)){
                return i;
            }
        }
        return -1;
    }
    private void expand(){
        Object[] temp = new Object[this.length + 1];
        System.arraycopy(this.keys, 0, temp, 0, this.keys.length);
        this.keys = temp;
        System.arraycopy(this.values, 0, temp, 0, this.values.length);
        this.values = temp;
    }
    private void shrink(){
        Object[] temp = new Object[this.length];
        System.arraycopy(this.keys, 0, temp, 0, this.length);
        this.keys = temp;
        System.arraycopy(this.values, 0, temp, 0, this.length);
        this.values = temp;
    }
}
