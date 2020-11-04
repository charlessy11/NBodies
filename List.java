public interface List<E> {
    int size();
    E get(int position);
    boolean add(E item);
    void add(int position, E item);
    E remove(int position);
}
