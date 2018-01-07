/* Randomized Queue used Array to implement
 * @author: Jia Ding
 * Key point is changing the size of array
 * Here, I introduced two methods: shrink() & resize()
 * So that, each randomized queue operation (besides iterator) in constant amortized time
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdIn;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Item[] it = (Item[]) new Object[1];
	private int count = 0;     //number of items including empty sites by dequeue
	private int emptyNum = 0;  //number of empty sites by dequeue
	
  public RandomizedQueue() {
  }
  
  public boolean isEmpty() { return count-emptyNum == 0;}
  
  public int size() {     //return the number of itmes on the dqueue
  	return count-emptyNum;
  }
  
  private void resize(int n) {
  	Item[] temp = (Item[]) new Object[n];
  	for (int i = 0; i < count; i++) {
  		temp[i] = it[i];
  	}
  	it = temp;
  }
  
  public void enqueue(Item item) {
  	if (item == null) {throw new IllegalArgumentException();}
    if (count == it.length) { resize(2*count); }
    it[count++] = item;
  }
   
  private void shrink(int n) {  // shrink the array and delete the null sites
  	Item[] temp = (Item[]) new Object[n];
  	for (int i = 0, j = 0; i < count; i++) {
  		if (it[i] != null) {
  			temp[j++] = it[i];
  		}
  	}
  	it = temp;
  	count = count - emptyNum;
  	emptyNum = 0;
  }
  
  public Item dequeue() {  //remove and return a random item
  	if (isEmpty()) {throw new NoSuchElementException();}
  	int n = StdRandom.uniform(count);
  	while (it[n] == null) { n = StdRandom.uniform(count); }
  	Item temp = it[n];
  	it[n] = null;
  	emptyNum++;
  	if ( count > 1 && (count-emptyNum) < 0.25*it.length ) {
  		shrink(it.length/2);
  	}
  	return temp;
  }
  
  public Item sample() {  //return a random item (but do not remove it)
  	if (isEmpty()) {throw new NoSuchElementException();}
        int n = StdRandom.uniform(count);
        while (it[n] == null) { n = StdRandom.uniform(count); }
        return it[n];
  }
  
  public Iterator<Item> iterator() { // return an independent iterator over items in random order
  	return new RQIterator();
  }
  
  private class RQIterator implements Iterator<Item> {
  	private int rank = count - emptyNum;
        private Item[] temp = (Item[]) new Object[count-emptyNum];
        private RQIterator() {
          for (int i = 0, j = 0; i < count; i++) {
  		  if (it[i] != null) {
  			  temp[j++] = it[i];
  		  } 
  	}
  	
  	public boolean hasNext() { return rank > 0; }
  	public void remove() { throw new UnsupportedOperationException();}
  	public Item next() {
          if (rank == 0) {throw new NoSuchElementException();}
          int n = StdRandom.uniform(rank--);
          Item a = temp[n];
          temp[n] = temp[rank];
          temp[rank] = a;
          return a;
  	}
  }
  
  public static void main(String[] args) {
  	     RandomizedQueue<Integer> rq1 = new RandomizedQueue<Integer>();
  	    // RandomizedQueue<Integer> rq2 = new RandomizedQueue<Integer>();
         System.out.println(rq1.isEmpty());
         System.out.println(rq1.size());
        
         //rq2.isEmpty();
         rq1.enqueue(1);
         System.out.println(rq1.isEmpty());
         //rq1.enqueue(20);
        
         System.out.println(rq1.dequeue());
         System.out.println(rq1.count+" "+rq1.emptyNum);
          rq1.enqueue(0);
         System.out.println(rq1.size());
  }
}
