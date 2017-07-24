import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
//import edu.princeeton.cs.algs4.WeightedQuickUnionUF;

public class PercolationStats {
	private final double[] results;
	private double ave;
	private double stddev;

	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IndexOutOfBoundsException("row or col index out of bounds");
		}
		results = new double[trials];
		for (int i = 0; i < trials; i++) {      // recycle for "trials" times
			Percolation a = new Percolation(n);   // creat a n-by-n grid
			while (!a.percolates()) {
				int row = StdRandom.uniform(n) + 1;
				int col = StdRandom.uniform(n) + 1;
				if (!a.isOpen(row, col)) {
					a.open(row, col);
				}
			}
			results[i] = (double) (a.numberOfOpenSites()) / n / n;
		}
	}

	public double mean() { // sample mean of percolation threshold
		ave = StdStats.mean(results);
		return ave;
	}

	public double stddev() { // sample standard deviation of percolation threshold
		stddev = StdStats.stddev(results);
		return stddev;
	}

	public double confidenceLo() { // low endpoint of 95% confidence interval
		return ave - 1.96 * stddev / Math.sqrt(results.length);
	}

	public double confidenceHi() { // high endpoint of 95% confidence interval
		return ave + 1.96 * stddev / Math.sqrt(results.length);
	}

	public static void main(String[] args) { // test client (described below)
		PercolationStats b = new PercolationStats(300, 300);
		System.out.println("mean = " + b.mean());
		System.out.println("stddev = " + b.stddev());
		System.out.println("95% confidence interval = [" + b.confidenceLo() + ", " + b.confidenceHi() + "]");
	}
}
