import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class RandomizedQueue<Item> implements Iterable<Item>{
	  private int N;   // number of items in the arrary
	  private Item[] queue;
	  private void resize(int capacity) {
	  	Item[] copy = (Item[]) new Object[capacity];
	  	for (int i = 0; i < N; i++) {
	  		copy[i] = queue[i];
	  	}
	  	queue = copy;
	  }
	  
	  public RandomizedQueue() {     //construct an empty randomized queue
	  	N = 0;
	    queue = (Item[]) new Object[2];
	  }

    public boolean isEmpty() {      //is the queue empty?
    	return N == 0;  	
  	}
  	
  	public int size() {         //return the number of items on the queue
  		return N; 	
  	}
  	
  	public void enqueue(Item item) {    // add item
  		if (item == null) {
  			throw new java.lang.IllegalArgumentException("can not add a null item.");
  		}
  		if (N == queue.length) {
  			resize(2*N);
  		}
  	  queue[N++] = item;
  	}
  	
  	public Item dequeue() {      //remove and return a  random item
  		if (isEmpty()) {
  			throw new java.util.NoSuchElementException();
  		}
  		int n = StdRandom.uniform(N);
  		Item item = queue[n];
      queue[n] = queue[--N];   // move the last item to the selected position
      queue[N] = null;         // avoid loitering
  		if (N > 0 && N == queue.length/4) {   //resize
  			resize(queue.length/2);
  		}
  		return item;
  	}     
  	
  	public Item sample() {       //return (but not remove) a random item
  		if (isEmpty()) {
  			throw new java.util.NoSuchElementException();
  		}
  		int n = StdRandom.uniform(N);
  		Item item = queue[n];
  		return item;
  	}   
  	
  	public Iterator<Item> iterator() {   // return an independent iterator over items in random order
  		return new ArrayIterator();
  	} 
  	
  	private class ArrayIterator implements Iterator<Item> {
  		private int num = N;
  		public boolean hasNext() {
  			return num > 0;
  		}
  		public void remove() {
  			throw new java.lang.UnsupportedOperationException();
  		}
  		public Item next() {
  			if (!hasNext()) {
  				throw new java.util.NoSuchElementException();
  			}
  			int n = StdRandom.uniform(num);
  			Item item = queue[n];
  			Item temp = queue[n];
  			queue[n] = queue[--num];
  			queue[num] = temp; 
  			return item;
  		}
  	}
  	
  	public static void main(String[] args) {      // unit testing
  		RandomizedQueue<String> a = new RandomizedQueue<String>();
  		while (StdIn.hasNextLine()) {
  			String line = StdIn.readString();
  			if (line.equals("---")) {break;}
  			a.enqueue(line); 			
  		}
  		Iterator<String> b = a.iterator();
  		while (b.hasNext()) {
  			System.out.println(b.next());
  		}
  		System.out.println("***********");
  		for (int i = 0; i < 5; i++) {
  			System.out.println(a.dequeue());
  		}
  		System.out.println("@@@@@@@@@");
  		Iterator<String> c = a.iterator();
  		while (c.hasNext()) {
  			System.out.println(c.next());
  		}
  	}  
}