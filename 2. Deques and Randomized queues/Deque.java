public class Deque<Item> implements Iterable<Item> {
	 private class Node<Item> {
	 	Item item;
	 	Node next;
	 	Node front;
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
   	Node<Item> oldfirst = first;
   	first = new Node<Item>();
   	first.item = item;
   	first.front = null;
   	if(oldfirst == null) {first = last;}
   	else {oldfirst.front = first;}
   	first.next = oldfirst;
   }         
   
   public void addLast(Item item) {      // add the item to the end
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
   		last.front = oldfirst;
   	}
   }          
   
   public Item removeFirst() {   // remove and return the item from the front
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
   	
   }   
   public static void main(String[] args)   // unit testing (optional)
}
