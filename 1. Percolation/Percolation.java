/* Using Monte Carlo method to simulate percolation threshold
 * WeightedQuickUnionUF object is used.
 * Unfortunately, WQUUF doesnt include path compression.
 * Backwash problem is fixed
 * reference: http://www.cnblogs.com/anne-vista/p/4841732.html
 * @author: Jia Ding
 */
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {  
    private boolean[] isopen;        //record status of each site
    private boolean[] connectTop;    // if connected to the top
    private boolean[] connectBottom; //              ... bottom
    private final int n;             // length of grid
    private int count = 0;           // number of open sites
    private WeightedQuickUnionUF uf; 
    private boolean percolateFlag = false;
  
    public Percolation(int i)  {   
    	if (i <= 0) {
    		throw new IllegalArgumentException("input should larger than 0");
    	}
    	n = i;
    	uf = new WeightedQuickUnionUF(n*n);
    	isopen = new boolean[n*n];
    	connectTop = new boolean[n*n];
    	connectBottom = new boolean[n*n];
    	for (int j = 0; j < n*n; j++) {
    		isopen[j] = false;
    		connectTop[j] = false;
    		connectBottom[j] = false;
    	}
    }
    
    private void validate(int i, int j) {   
    	if (i < 1 || i > n || j < 1 || j > n)  {
    		throw new IllegalArgumentException("the index should between 1 and " + n);
    	}

    }
	
    /* open function
     * @para. index of site (row, col)
     * First, open the site
     * Then, union with nearby site. Sequence is up, down, left, right
     * Then, find root and decide if connected to the top or bottom
     * If one site connectTop && connectBottom, percolateFlag = true
     */
    public void open(int row, int col)  { 
      validate(row, col);
      int index = (row-1)*n + col - 1;
      if (isopen[index]) return;
      isopen[index] = true;
      count++;
      
      boolean top = false;
      boolean bottom = false;
      if (row > 1 && isopen[index-n] ) {
      	if (connectTop[uf.find(index)] || connectTop[uf.find(index-n)]) {
      		top = true;
      	}
      	if (connectBottom[uf.find(index)] || connectBottom[uf.find(index-n)]) {
      		bottom = true;
      	}
      	uf.union(index, index-n);
      }
      if (row < n && isopen[index+n] ) {
      	if (connectTop[uf.find(index)] || connectTop[uf.find(index+n)]) {
      		top = true;
      	}
      	if (connectBottom[uf.find(index)] || connectBottom[uf.find(index+n)]) {
      		bottom = true;
      	}
      	uf.union(index, index+n);
      } 
      if (col > 1 && isopen[index-1] ) {
      	if (connectTop[uf.find(index)] || connectTop[uf.find(index-1)]) {
      		top = true;
      	}
      	if (connectBottom[uf.find(index)] || connectBottom[uf.find(index-1)]) {
      		bottom = true;
      	}
      	uf.union(index, index-1);
      } 
      if (col < n && isopen[index+1] ) {
      	if (connectTop[uf.find(index)] || connectTop[uf.find(index+1)]) {
      		top = true;
      	}
      	if (connectBottom[uf.find(index)] || connectBottom[uf.find(index+1)]) {
      		bottom = true;
      	}
      	uf.union(index, index+1);
      } 
      
      if (row == 1) top = true;
      if (row == n) bottom = true;
      connectTop[uf.find(index)] = top;
      connectBottom[uf.find(index)] = bottom;
      if (top && bottom) percolateFlag = true;      
    }
    
    public boolean isOpen(int row, int col) { 
    	validate(row, col);
    	return isopen[(row-1)*n + col - 1];
    }

    public boolean isFull(int row, int col) {    
    	validate(row, col);
    	int index = (row-1)*n + col - 1;
      return connectTop[uf.find(index)];
    }
    
    public int numberOfOpenSites() {
    	return count;
    }

    public boolean percolates()  {             // does the system percolate? 
      return percolateFlag;
    }
    
    public static void main(String[] args) {    // test client
    	Percolation per = new Percolation(4);
    	per.open(1,3);
    	per.open(2,3);
    	per.open(3,3);
    	per.open(4,3);

    	per.open(4,2);
    	for (int i = 1; i<=4; i++) {
    		for (int j = 1; j<=4; j++) {
    			int index = (i-1)*4 + j - 1;
    			System.out.print(per.uf.find(index)+" ");
    	
    		}
    		System.out.println();
    	}
    	
    	for (int i = 1; i<=4; i++) {
    		for (int j = 1; j<=4; j++) {
    			int index = (i-1)*4 + j - 1;
    			System.out.print(per.connectTop[index]+" ");
    	
    		}
    		System.out.println();
    	}
    }
}
