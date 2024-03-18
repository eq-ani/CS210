/*
 * Copyright 2023 Marc Liberatore.
 */

package lists;

public class LinkedList<E> implements List<E> {
    // Note: do not declare any additional instance variables
    Node<E> head;
    Node<E> tail;
    int size;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Node<E> n = head;
        while (n != null) {
            result = prime * result + head.data.hashCode();
        }
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof List))
            return false;
        List other = (List) obj;
        if (size != other.size())
            return false;
        for(int i = 0; i < size; i++) //iterates through the list; if they are unequal return false, returns true otherwise. 
        {
            if (!this.get(i).equals(other.get(i)))
            {
                return false;
            }
        }    
        // TODO before returning true, make sure each element of the lists are equal!
        return true;
    }

    @Override
    public int size() { //size will be updated through the remove and add functions
        return size;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) //checks for index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = head;
        int count = 0;
        while (curr.next != null) //interates until reaching the index
        {
            if (count == index)
            {
                return curr.data;
            }
            curr = curr.next;
            count++;
        }
        return curr.data;
    }
    
    @Override
    public void add(E e) {
        Node<E> temp = new Node(e);
        if (size == 0) //sets the head and tail equal to the new node with data; if size 1, the head and tail are the same. 
        {
            head = tail = temp;
            tail.next = null; //always set tail.next to null, signifying the end of the list
        }
        else if (size == 1) //sets the tail equal to head.next which was set to the addition
        {
            head.next = temp;
            tail = head.next;
            tail.next = null;
        }
        else //otherwise, append to the end of the list
        {
            Node<E> curr = tail;
            curr.next = temp; //curr is the the current tail, and curr.next is what the new tail will be
            tail = curr.next;
            tail.next = null;
        }
        size++;
    }

    @Override
    public void add(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index > size()) //checks for index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = new Node(e);
        if (size == 0) //if the size is zero, do the same as the other add method
        {
            head = tail = temp;
            tail.next = null;
        }
        else if (index == 0) //if the index is zero, update the head
        {
            temp.next = head;
            head = temp;
            head.next = temp.next;
        }
        else if (index == size) //if the index is == to the current size, interate to the end and update the tail
        {
            Node<E> curr = tail;
            curr.next = temp;
            tail = curr.next;
            tail.next = null;
        }
        else //otherwise, interate to the index and set the previous.next node equal to the new node, and the new node.next to the current node. 
        {
            Node<E> curr = head;
            Node<E> prev = null;
            int count = 0;
            while (true) //interates until the index is found
            {
                if (count == index) {
                    break;
                }
                prev = curr;
                curr = curr.next;
                count++;
            }
            prev.next = temp;
            temp.next = curr;
        }
        size++;
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size || size == 0) //checks for an index out of bounds exception, same as the rest but includes if the size is zero. 
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr;
        if (index == 0) //if index is zero, remove from the front by setting head.next to head (and return the data that was deleted)
        {
            Node<E> ret;
            ret = head;
            curr = head.next;
            head = curr;
            size--;
            return ret.data;
        }
        if (size == 1) //if size is 1, that means that you empty the linkedlist by setting head and tail both equal to null. (return curr, the temporary node storing our data)
        {
            curr = head; //head and tail should both be the same
            head = null;
            tail = null;
            size--;
            return curr.data;
        }
        else if (index == size - 1) //if removing the last element in the list, interate through to the end of the list, curr being the tail and prev being the element before the tail. 
        {                           //then update tail appropriately
            curr = head;
            Node<E> prev = null;
            while (curr.next != null)
            {  
                prev = curr;
                curr = curr.next;
            }
            tail = prev;
            tail.next = null;
            size--;
            return curr.data;
        }
        else //otherwise interate to the index, curr being the node being removed, and set previous.next = to curr.next, removing curr. 
        {
            curr = head;
            Node<E> prev = null;
            int count = 0;
            while (true) 
            {
                if (count == index)
                {
                    break;
                }
                prev = curr;
                curr = curr.next;
                count++;
            }
            prev = curr.next;
            size--;
            return curr.data;
        }
    }

    @Override
    public E set(int index, E e) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) //check for an index out of bounds exception
        {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = head; 
        int count = 0;
        while (true) //interate to the correct index and set the current nodes data to the chosen data. 
        {
            if(count == index)
            {
                break;
            }
            curr = curr.next;
            count++;
        }
        curr.data = e;
        return this.get(index);
    }

    @Override
    public int indexOf(E e) {
        if (size == 0) //if the size is 0, there is no data to find the index of
        {
            return -1;
        }
        Node<E> curr = head;
        int count = 0;
        while (curr != null)  //interate through the entire linked list
        {
            if (curr.data.equals(e)) //if found, stop the loop 
            {
                return count;
            }
            if (curr.next != null) //keeps the loop going until curr.next isn't null, where if curr.next is null, it stops the loop. 
            {
                curr = curr.next;
                count++;
            }
            else 
            {
                break;
            }
        }  
        return -1; //returns -1 if not found
    }
}
