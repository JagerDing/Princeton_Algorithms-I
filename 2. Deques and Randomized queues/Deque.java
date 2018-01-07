/* Double-ended queue used Linkedlist to implement
 * @author: Jia Ding
 * 1. In LinkedList, you should take care of the reference and objects.
 *    Sometimes, you changed the reference, but the object keeps the same.
 *    Especially for the first and last references with null.
 * 2. The defination of Iterator.hasNext():
 *    What I thought, if only one item left, hasNext() == false;
 *    However, hasNext() is kind like isEmpty()
 *    So, the best way is using the number of items.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int count = 0;
	private class Node {
		Item item;
		Node next;
		Node pre;
	}
	
  public Deque() {}
  
  public boolean isEmpty() {
  	return count == 0;    //Or: first == last.
  }
  
  public int size() {
  	return count;
  }
  
  public void addFirst(Item item) {
  	  if (item == null) {throw new IllegalArgumentException();}
  		Node oldfirst = first;
  		first = new Node();
  	  first.item = item;
  	  first.next = oldfirst;
  	  if (isEmpty()) {last = first;}
  	  else {oldfirst.pre = first;}
      count++;
  }
  
  public void addLast(Item item) {
  	  if (item == null) {throw new IllegalArgumentException();}
  	  Node oldlast = last;
  		last = new Node();
  	  last.item = item;
  	  last.pre = oldlast;
  	  if (isEmpty()) {first = last;}
  	  else {oldlast.next = last;}
      count++;
  }
  
  public Item removeFirst() { // remove and return the item from the front
  	if(isEmpty()){
   		throw new java.util.NoSuchElementException("can not remove from empty deque");
   	} else {
  		Item temp = first.item;
  		first = first.next;
  		//first.pre = null;   
  		//it will throw "java.lang.NullPointerException" when null = null
  		if (first == null) {last = null;}
  		count--;
  		return temp;
  	}
  } 
  
  public Item removeLast() {
  	if(isEmpty()){
   		throw new java.util.NoSuchElementException("can not remove from empty deque");
   	} else {
  		Item temp = last.item;
  		last = last.pre;
  		if (last == null ) {first = null;}
		else {last.next = null;}
  		count--;
  		return temp;
  	}
  }
  
  public Iterator<Item> iterator() { // return an iterator over items in order from front to end
  	return new ListIterator();
  }
  
  private class ListIterator implements Iterator<Item> {
  	private Node current = first;
  	public boolean hasNext() { return current != null; }
  	public void remove() { throw new UnsupportedOperationException();}
  	public Item next() {
  		if ( current == null) {throw new NoSuchElementException();}
  		Item temp = current.item;
  		current = current.next;
  		return temp;
  	}
  }

  public static void main(String[] args) {  //unit testing
  	   //Deque<String> deq1 = new Deque<String>();
        Deque<Integer> deque = new Deque<Integer>();
         deque.addLast(1);
         deque.removeFirst();
         deque.addFirst(3);
         deque.addFirst(4);
         deque.addFirst(5);
         deque.addLast(6);
         deque.removeLast();

        Iterator<Integer> itr = deque.iterator();
        while (itr.hasNext()) {
        	System.out.print(itr.next() + " ");       	
        }


  }
}
