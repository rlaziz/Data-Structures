package Dictionary;

import ArrayList.ArrayList; // custom ArrayList
//import java.util.ArrayList; // uncomment this line and comment out previous if you want to use built in ArrayList

public class Dictionary<E,T>{
    private final ArrayList<E> keys;
    private final ArrayList<T> values;

    public Dictionary(){
        this.keys = new ArrayList<E>();
        this.values = new ArrayList<T>();
    }
    public void add(E key, T value){
        int index = this.indexOf(key);
        if (index != -1){
            ((ArrayList)this.values.get(index)).add(value);
        }else {
            this.keys.add(key);
            ArrayList<T> temp = new ArrayList<T>();
            temp.add(value);
            this.values.add((T) temp);
        }
    }
    public T remove(E key){
        int index = this.indexOf(key);
        if (index == -1){
            return null;
        }
        this.keys.remove(index);
        return this.values.remove(index);
    }
    public T get(E key){
        int index = this.indexOf(key);
        if (index == -1){
            return null;
        }
        return this.values.get(index);
    }
    public boolean set(E key, T newValue){
        int index = this.indexOf(key);
        if (index == -1){
            return false;
        }
        this.values.set(index,newValue);
        return true;
    }
    public Object[] getKeys(){
        return this.keys.asArray();
    }
    public Object[] getValues(){
        return this.values.asArray();
    }
    private int indexOf(E key){
        return this.keys.indexOf(key);
    }
    public boolean contains(E key){
        return this.indexOf(key) != -1;
    }
    public int length(){
        return this.values.length();
    }
    @Override
    public String toString(){
        String result = "{";
        int commasLeft = this.length() - 1;
        for (int i = 0; i < this.length(); i++){
            result += this.keys.get(i) + ": " + this.values.get(i);
            result = (commasLeft > 0) ? result + ", " : result + "}";
            commasLeft--;
        }
        return result;
    }
}
