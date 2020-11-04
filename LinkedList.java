import java.util.*;

public class LinkedList<E> implements List<E>{
    Node<E> head;
    int size;
    private class Node<E> {
        E data;
        Node<E> next;

        public Node(E value) {
            data = value;
            next = null;
        }
    }
    //constructor
    public LinkedList() {
        head = null;
        size = 0;
    }

    //size function
    public int size() {
        return size;
    }
    //get function
    public E get(int position) {
        //check if position is valid
        if (position < 0 || position >= size) {
            return null;
        }
        Node<E> curr = head;
        for (int i = 0; i < position; i++) {
            curr = curr.next;
        }
        return curr.data;
    }
    //add item to the end of list
    public boolean add(E item) {
        Node<E> node = new Node(item);
        //special case: if item being added is in head
        if (head == null) {
            head = new Node(item);
            ++size;
        } else {
            Node<E> prev = head;
            //loop through every element in list
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = node;
            ++size;
        }
        return true;
    }
    //add item to specific position in list
    public void add(int position, E item) {
        //check if position is valid
        if (position < 0 || position >= size) {
            return;
        }
        //special case: if item being added is in head
        else if (position == 0) {
            Node<E> node = new Node(item);
            node.next = head;
            head = node;
            ++size;
        }
        else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next;
            }
            Node<E> node = new Node(item);
            node.next = prev.next;
            prev.next = node;
            ++size;
        }

    }
    //remove item in list
    public E remove(int position) {
        //check if position is valid
        if (position < 0 || position >= size) {
            return null;
        }
        //special case: if item being removed is in head
        if (position == 0) {
            Node<E> node = head;
            head = head.next;
            --size;
            return node.data;
        }
        else {
            Node<E> prev = head;
            for (int i = 0; i < position - 1; i++) {
                prev = prev.next;
            }
            Node<E> node = prev.next;
            prev.next = node.next;
            --size;
            return node.data;
        }
    }
    //reverse items in linked list
    public Node<E> reverse(Node<E> head) {
        Node<E> prev = null;
        Node<E> curr = head;
        Node<E> next = null;
        while (curr != null)
        {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }
    //function prints contents of linked list starting from the given node
    public void printList()
    {
        Node<E> node = head;
        while (node != null)
        {
            System.out.print(node.data+" ");
            node = node.next;
        }
    }

    @Override
    public String toString() {
        return "LinkedList{" + head + '}';
    }
}