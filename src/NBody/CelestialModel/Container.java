package NBody.CelestialModel;

/*
    This Interface provides the signature for an iterable data structure. Classes that implement this class are intended
    to have methods that manipulate the data structure it represents. The LinkedList and ArrayList data structures can be
    implemented from this Interface.
*/
public interface Container<E> {
    public void add(E obj); //adds an item to the end of the data structure
    public void add(E obj, int position) throws Exception; //adds an item at a specific position within the data structure
    public E get(int position); //returns the item at a specific position within the data structure
    public E remove(int position) throws Exception; //removes and returns an item at a specific position within the data structure
    public int size(); //returns the number of items in the data structure
}
