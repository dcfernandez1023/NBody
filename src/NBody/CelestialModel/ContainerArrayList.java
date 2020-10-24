package NBody.CelestialModel;

public class ContainerArrayList<E> implements Container<E> {
    private int size;
    private E[] arr;

    public ContainerArrayList(int capacity) {
        this.size = 0;
        this.arr = (E[]) new Object[capacity];
    }
    @Override
    public void add(E obj) {
        if(this.size >= this.arr.length) {
            this.growArray();
            this.arr[this.size] = obj;
        }
        else {
            this.arr[this.size] = obj;
        }
        size++;
    }
    @Override
    public void add(E obj, int position) throws Exception {
        if(position >= this.arr.length || position < 0) {
            throw new Exception("Position: " + position + " out of bounds for length " + this.arr.length);
        }
        if(this.size >= this.arr.length) {
            this.growArray();
        }
        E temp1 = obj;
        E temp2;
        for(int i = position-1; i < this.size; i++) {
            temp2 = this.arr[i+1];
            this.arr[i+1] = temp1;
            temp1 = temp2;
        }
        size++;
    }
    @Override
    public E get(int position) throws Exception {
        if(this.arr.length == 0) {
            throw new Exception("Cannot get elements from empty list");
        }
        if(position >= arr.length) {
            return null;
        }
        return this.arr[position];
    }
    @Override
    public E remove(int position) throws Exception {
        if(position >= this.arr.length || position < 0) {
            throw new Exception("Position: " + position + " out of bounds for length " + this.arr.length);
        }
        E val = this.arr[position];
        for(int i = position; i < this.arr.length-1; i++) {
            this.arr[i] = this.arr[i+1];
        }
        this.arr[this.arr.length-1] = null;
        this.size--;
        return val;
    }
    @Override
    public int size() {
        return this.size;
    }
    @Override
    public String toString() {
        String s = "[";
        for(int i = 0; i < this.arr.length; i++) {
            if(i == this.arr.length-1) {
                s = s + this.arr[i] + "]";
            }
            else {
                s = s + this.arr[i] + ", ";
            }
        }
        return s;
    }

    private void growArray() {
        E[] newArr = (E[]) new Object[this.arr.length+1];
        for(int i = 0; i < this.arr.length; i++) {
            newArr[i] = this.arr[i];
        }
        this.arr = newArr;
    }
}
