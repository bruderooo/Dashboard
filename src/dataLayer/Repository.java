package dataLayer;

public interface Repository<T> {

    void add(T item);

    void remove(T item);

    T get(int index);


}