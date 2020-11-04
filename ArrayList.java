import java.util.*;

public class ArrayList<E> implements List<E> {
    Object[] arr;
    int size;
    int capacity;
    //constructor
    public ArrayList() {
        capacity = 10;
        arr = new Object[capacity];
        size = 0;
    }
    //size function
    public int size() {
        return size;
    }
    //get function
    public E get(int position) {
        if (position < 0 || position >= arr.length) {
            return null;
        }
        return (E)arr[position];
    }
    //helper function that grows array length if necessary
    private void growArray() {
        E[] new_arr = (E[]) new Object[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                new_arr[i] = (E)arr[i];
            }
        arr = new_arr;
    }
    //add item to the end of list
    public boolean add (E item) {
        if (size == arr.length) {
            growArray();
        }
        arr[size++] = item;
        return true;
    }
    //add item to specific position in list
    public void add (int position, E item) {
        if (position < 0 || position >= arr.length) {
            return;
        }
        if (size == arr.length) {
            growArray();
        }
        for (int i = size; i > position; i--) {
            arr[i] = arr[i - 1];
        }
        arr[position] = item;
        ++size;
    }
    //remove item in list
    public E remove(int pos) {
        if (pos < 0 || pos >= arr.length) {
            return null;
        }
        E item = (E)arr[pos];
        --size;
        return item;
    }

    @Override
    public String toString() {
        return "ArrayList{" + Arrays.toString(arr) + '}';
    }
}
