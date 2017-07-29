import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	 private class Node<Item> {
	 	Item item;
	 	Node next;
	 	Node front;
	 }
	 private class ListIterator implements Iterator<Item>{
   	private Node<Item> current = first;
   	
   	public boolean hasNext(){return current != null;}
   	public void remove() {
   		throw new UnsupportedOperationException ("Not supported yet.");
   	}
   	public Item next(){
   		if(!hasNext()){
   			throw new java.util.NoSuchElementException("no more items to return.");
   		}
   		Item item = current.item;
   		current = current.next;
   		return item;
   	}
   }
	 private Node<Item> first, last;
	 private int size;
	 
   public Deque() {                // construct an empty deque
   	first = null;
   	last = null;
   	size = 0;
   }	
   
   public boolean isEmpty() {       // is the deque empty?
   	return first == null;
   }               
   public int size() {          // return the number of items on the deque
   	return size;
   }                       
   public void addFirst(Item item) {     // add the item to the front
   	if(item == null){
   		throw new IllegalArgumentException("can not add a null item");
   	}
   	Node<Item> oldfirst = first;
   	first = new Node<Item>();
   	first.item = item;
   	first.front = null;
   	if(oldfirst == null) {  		
   		last = first;
   		last.next = null;
   	}
   	else {
   		oldfirst.front = first;
   		first.next = oldfirst;
   	}
   	
   }         
   
   public void addLast(Item item) {      // add the item to the end
   	if(item == null){
   		throw new IllegalArgumentException("can not add a null item");
   	}
   	Node<Item> oldlast = last;
   	last = new Node<Item>();
   	last.item = item;
   	last.next = null;
   	if(oldlast == null) {
   		first = last;
   		first.front = null;
   	}
   	else {
   		oldlast.next = last;
   		last.front = oldlast;
   	}
   }          
   
   public Item removeFirst() {   // remove and return the item from the front
   	if(first == null){
   		throw new java.util.NoSuchElementException("can not remove from empty deque");
   	}
   	Item item = first.item;
   	first = first.next;
   	first.front = null;
   	return item;
   }   
            
   public Item removeLast() {    // remove and return the item from the end
   	Item item = last.item;
   	last = last.front;
   	last.next = null;
   	return item;
   }            
   
   public Iterator<Item> iterator() {     // return an iterator over items in order from front to end
   	return new ListIterator();
   }   
   
   public static void main(String[] args) {     // unit testing (optional)
   	Deque<String> a = new Deque<String>();
   	System.out.println("is the deque empty? " + a.isEmpty());
   	a.addFirst(null);
   	a.addLast("zheshier");
   	a.addLast("asldjkhf");
   	a.addFirst("waoeirht");
   	Iterator<String> b = a.iterator();
   	while(b.hasNext()){
   		System.out.println(b.next());
   	}
   }  
}
