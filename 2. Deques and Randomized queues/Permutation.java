import edu.princeton.cs.algs4.StdIn;
import java.util.Iterator;

public class Permutation {
   public static void main(String[] args) {
   	RandomizedQueue<String> a = new RandomizedQueue<String>();
   	  System.out.println("Please input an integer:");
   	  int k = StdIn.readInt();
   	  System.out.println("Please input a sequence of strings:");
  		while (StdIn.hasNextLine()) {
  			String line = StdIn.readString();
  			if (line.equals("---")) {break;}
  			a.enqueue(line); 			
  		}
  		Iterator<String> b = a.iterator();

  		for (int i = 0; i < k; i++) {
  			System.out.println(b.next());
  		}

   }  
