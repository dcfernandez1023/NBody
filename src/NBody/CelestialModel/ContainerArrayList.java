package NBody.CelestialModel;

/*
    This Class implements the Container interface defined in Nbody.CelestialModel. This class maintains an ArrayList
    data structure and has methods to manipulate its data.
*/
public class ContainerArrayList<T> implements Container<T> {
    private int size;
    private T[] arr;

    /*
        * PARAMS: int capacity - the initial length of the array
        * DESCRIPTION: constructs a ContainerArrayList object and initializes its data members
        * RETURN: none
    */
    public ContainerArrayList(int capacity) {
        this.size = 0;
        this.arr = (T[]) new Object[capacity];
    }
    /*
        * PARAMS: T obj - the item to add to the array
        * DESCRIPTION: adds an item at the end of the array; grows the length of the array if necessary
        * RETURN: none
    */
    @Override
    public void add(T obj) {
        //if number of items in the array exceeds the length, grow the array
        if(this.size >= this.arr.length) {
            this.growArray();
            this.arr[this.size] = obj; //add at last index
        }
        else {
            //add at last index
            this.arr[this.size] = obj;
        }
        this.size++;
    }
    /*
        * PARAMS:
            * T obj - the item to add to the array
            * int position - the index at which the item should be added
        * DESCRIPTION: adds an item at the specified position; grows the length of the array if necessary;
                       throws exception if position is invalid
        * RETURN: none
    */
    @Override
    public void add(T obj, int position) throws Exception {
        //throw exception if position is invalid
        if(position >= this.arr.length || position < 0) {
            throw new Exception("Position: " + position + " out of bounds for length " + this.arr.length);
        }
        //grow array if the number of items in the array exceeds the length;
        if(this.size >= this.arr.length) {
            this.growArray();
        }
        //initialize temp variables to perform swaps as the algorithm loops and shifts positions of the array
        T temp1 = obj;
        T temp2;
        //loop through the array and shift positions over to insert the item at the specified position
        for(int i = position-1; i < this.size; i++) {
            temp2 = this.arr[i+1]; //store position at current position+1
            this.arr[i+1] = temp1; //set item at current position+1 to the stored variable
            temp1 = temp2; //re-assign temp1 variable to temp2 (which was the previous item at current position+1)
        }
        this.size++;
    }
    /*
        * PARAMS: int position - the position of the item to be returned
        * DESCRIPTION: returns the item at the specified position; returns null if position is invalid
        * RETURN: T - the item at the specified position
    */
    @Override
    public T get(int position) {
        //if position is invalid throw exception
        if(position >= arr.length || position < 0) {
            return null;
        }
        return this.arr[position];
    }
    /*
        * PARAMS: int position - the position of the item to be removed
        * DESCRIPTION: removes the item at the specified position and returns it; throws exception if invalid position
        * RETURN: T - the item that was just removed
    */
    @Override
    public T remove(int position) throws Exception {
        //if position is invalid, throw exception
        if(position >= this.arr.length || position < 0) {
            throw new Exception("Position: " + position + " out of bounds for length " + this.arr.length);
        }
        //store the value at the specified position
        T val = this.arr[position];
        //loop through the array starting at the specified position
        //replace the item at the current position with 1 position ahead of it
        for(int i = position; i < this.arr.length-1; i++) {
            this.arr[i] = this.arr[i+1];
            this.arr[i+1] = null;
        }
        this.size--;
        return val;
    }
    /*
        * PARAMS: none
        * DESCRIPTION: returns the number of items in the array
        * RETURN: int - the number of items in the array
    */
    @Override
    public int size() {
        return this.size;
    }
    //toString() method to get a string representation of the data structure
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

    /*
        * PARAMS: none
        * DESCRIPTION: grows the capacity of the array by 1; used when adding items and need to expand the array
        * RETURN: none
    */
    private void growArray() {
        T[] newArr = (T[]) new Object[this.arr.length+1];
        for(int i = 0; i < this.arr.length; i++) {
            newArr[i] = this.arr[i];
        }
        this.arr = newArr;
    }
}
