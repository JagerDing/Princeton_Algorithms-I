import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
   public static void main(String[] args) {
   	RandomizedQueue<String> a = new RandomizedQueue<String>();   	 
  		while (！StdIn.isEmpty()) {
  			a.enqueue( StdIn.readString() );		
  		}
      
  		int k = Integer.parseInt(args[0]);
      Iterator<String> b = a.iterator();
  		for (int i = 0; i < k; i++) {
  			System.out.println(b.next());
  		}
   }
}
