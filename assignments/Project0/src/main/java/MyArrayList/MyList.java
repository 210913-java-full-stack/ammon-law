package MyArrayList;

//interface for if I also wanted to use this for a linked list
public interface MyList<T> {
    int size();
    void add(T t);
    void add(T t, int i);
    T get(int i);
    void remove(int index);
    void clear();
    boolean contains(T t);
}
