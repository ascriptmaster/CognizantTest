package com.kevlau;

import javax.naming.OperationNotSupportedException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by Kevin Lau on 3/16/2018.
 */
public class LinkedList<T> implements Collection<T> {
    private class Node<T> {
        public T value;
        public Node<T> next = null;
        public Node(T value) {
            this.value = value;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int length;

    public LinkedList() {
        clear();
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean exists = false;
        Node<T> current = head;
        while (current != null) {
            if (o != null && o.equals(current.value) || o == null && current.value == null) {
                exists = true;
                break;
            }
            current = current.next;
        }
        return exists;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current == null;
            }

            @Override
            public T next() {
                T value = current.value;
                current = current.next;
                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[length];
        Node<T> current = head;
        for (int i=0; i<length; i++) {
            array[i] = current.value;
            current = current.next;
        }
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length < length) {
            array = (T1[]) new Object[length];
        }
        Node<T> current = head;
        for (int i=0; i<length; i++) {
            array[i] = (T1) current.value;
            current = current.next;
        }
        return array;
    }

    @Override
    public boolean add(T e) {
        Node<T> node = new Node(e);
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        length++;
        return true;
    }

    public T remove(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> prev = null;
        Node<T> current = head;

        while (index > 0) {
            prev = current;
            current = current.next;
            index--;
        }

        if (prev == null) {
            head = current.next;
        } else {
            prev.next = current.next;
        }
        length--;

        return current.value;
    }

    @Override
    public boolean remove(Object o) {
        boolean exists = false;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (o != null && o.equals(current.value) || o == null && current.value == null) {
                exists = true;
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                length--;
                break;
            }
            prev = current;
            current = current.next;
        }
        return exists;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<T>(this).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        for (T element : collection) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean exists = false;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (c.contains(current.value)) {
                exists = true;
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                length--;
            }
            prev = current;
            current = current.next;
        }
        return exists;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean changed = false;
        Node<T> prev = null;
        Node<T> current = head;
        while (current != null) {
            if (!c.contains(current.value)) {
                changed = true;
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                length--;
            }
            prev = current;
            current = current.next;
        }
        return changed;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        length = 0;
    }

    public T get(int index) {
        Node<T> node = head;
        while (index > 0) {
            if (node == null) break;
            node = head.next;
            index--;
        }
        if (node == null) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return node.value;
    }


}
