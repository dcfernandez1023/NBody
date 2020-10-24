package NBody.model;

public interface Container<E> {
    public void add(E obj);
    public void add(E obj, int position) throws Exception;
    public E get(int position) throws Exception;
    public E remove(int position) throws Exception;
    public int size();
}
