public class Percolation {

	private int opensite;
	private int[][] grid;
	private int n;

	public Percolation(int num) { // create n-by-n grid with all sites blocked
		if (num <= 0) {
			System.out.println("java.lang.IllegalArgumentException");
			return;
		}
		n = num;
		grid = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				grid[i][j] = 0; // 0 means blocked
			}
		}
		opensite = 0;
	}

	private void nearbyopen(int row, int col, int rowne, int colne) {
		// int n = grid.length;
		// find the root of the new open site
		while (grid[row - 1][col - 1] != (row - 1) * n + col && grid[row - 1][col - 1] > 0) {
			int row_t = (grid[row - 1][col - 1] - 1) / n + 1;
			col = (grid[row - 1][col - 1] - 1) % n + 1;
			row = row_t;
		}
		// find the root of the nearby open site
		while (grid[rowne - 1][colne - 1] != (rowne - 1) * n + colne && grid[rowne - 1][colne - 1] > 0) {
			int rowne_t = (grid[rowne - 1][colne - 1] - 1) / n + 1;
			colne = (grid[rowne - 1][colne - 1] - 1) % n + 1;
			rowne = rowne_t;
		}
		if (grid[row - 1][col - 1] >= grid[rowne - 1][colne - 1]) {
			grid[row - 1][col - 1] = (rowne - 1) * n + colne;
		} else {
			grid[rowne - 1][colne - 1] = (row - 1) * n + col;
		}
	}

	public void open(int row, int col) { // open a site and connect it with nearby open site
		// int n = grid.length;
		if (row <= 0 || row > n || col <= 0 || col > n) {
			System.out.println("java.lang.IllegalArgmentException");
			return;
		}
		grid[row - 1][col - 1] = (row - 1) * n + col;
		opensite++;

		if (row == 1) { // top row
			grid[row - 1][col - 1] = -1; // top imagine root is -1
			if (col == 1) {
				if (grid[row][col - 1] != 0) { // if its undersite is open
					nearbyopen(row, col, row + 1, col);
				}
				if (grid[row - 1][col] != 0) { // if its rightsite is open
					nearbyopen(row, col, row, col + 1);
				}

			} else if (col == n) {
				if (grid[row][col - 1] != 0) { // if its undersite is open
					nearbyopen(row, col, row + 1, col);
				}
				if (grid[row - 1][col - 2] != 0) { // if its leftsite is open
					nearbyopen(row, col, row, col - 1);
				}

			} else {
				if (grid[row][col - 1] != 0) { // if its undersite is open
					nearbyopen(row, col, row + 1, col);
				}
				if (grid[row - 1][col] != 0) { // if its rightsite is open
					nearbyopen(row, col, row, col + 1);
				}
				if (grid[row - 1][col - 2] != 0) { // if its leftsite is open
					nearbyopen(row, col, row, col - 1);
				}
			}
		}

		else if (row == n) { // bottom row
			grid[row - 1][col - 1] = -2; // bottom imagine root is -2
			if (col == 1) {
				if (grid[row - 2][col - 1] != 0) { // if its upsite is open
					nearbyopen(row, col, row - 1, col);
				}
				if (grid[row - 1][col] != 0) { // if its rightsite is open
					nearbyopen(row, col, row, col + 1);
				}

			} else if (col == n) {
				if (grid[row - 2][col - 1] != 0) { // if its upsite is open
					nearbyopen(row, col, row - 1, col);
				}
				if (grid[row - 1][col - 2] != 0) { // if its leftsite is open
					nearbyopen(row, col, row, col - 1);
				}

			} else {
				if (grid[row - 2][col - 1] != 0) { // if its upsite is open
					nearbyopen(row, col, row - 1, col);
				}
				if (grid[row - 1][col] != 0) { // if its rightsite is open
					nearbyopen(row, col, row, col + 1);
				}
				if (grid[row - 1][col - 2] != 0) { // if its leftsite is open
					nearbyopen(row, col, row, col - 1);
				}
			}
		}

		else if (col == 1) { // left collom
			if (grid[row - 2][col - 1] != 0) { // if its upsite is open
				nearbyopen(row, col, row - 1, col);
			}
			if (grid[row][col - 1] != 0) { // if its underside is open
				nearbyopen(row, col, row + 1, col);
			}
			if (grid[row - 1][col] != 0) { // if its rightside is open
				nearbyopen(row, col, row, col + 1);
			}
		}

		else if (col == n) { // right collom
			if (grid[row - 2][col - 1] != 0) { // if its upsite is open
				nearbyopen(row, col, row - 1, col);
			}
			if (grid[row][col - 1] != 0) { // if its underside is open
				nearbyopen(row, col, row + 1, col);
			}
			if (grid[row - 1][col - 2] != 0) { // if its leftside is open
				nearbyopen(row, col, row, col - 1);
			}
		}

		else { // inner sites
			if (grid[row - 2][col - 1] != 0) { // if its upsite is open
				nearbyopen(row, col, row - 1, col);
			}
			if (grid[row][col - 1] != 0) { // if its underside is open
				nearbyopen(row, col, row + 1, col);
			}
			if (grid[row - 1][col - 2] != 0) { // if its leftside is open
				nearbyopen(row, col, row, col - 1);
			}
			if (grid[row - 1][col] != 0) { // if its rightside is open
				nearbyopen(row, col, row, col + 1);
			}
		}
	}

	public boolean isOpen(int row, int col) {
		// int n = grid.length;
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("row or col index out of bounds");
		}
		return grid[row - 1][col - 1] != 0;
	}

	public boolean isFull(int row, int col) {
		// int n = grid.length;
		if (row <= 0 || row > n || col <= 0 || col > n) {
			throw new IndexOutOfBoundsException("row or col index out of bounds");
		}
		if (grid[row - 1][col - 1] == 0) {
			return false;
		}
		while (grid[row - 1][col - 1] != (row - 1) * n + col && grid[row - 1][col - 1] > 0) {
			int row_t = (grid[row - 1][col - 1] - 1) / n + 1;
			col = (grid[row - 1][col - 1] - 1) % n + 1;
			row = row_t;
		}
		return grid[row - 1][col - 1] == -1;
	}

	public int numberOfOpenSites() {
		return opensite;
	}

	public boolean percolates() { // does the system percolate?
		// int n = grid.length;
		for (int i = 0; i < n; i++) {
			if (grid[0][i] > n * (n - 1)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		/*
		 * int n = 10; Percolation a = new Percolation(n); while(!a.percolates()) { int
		 * row = (int) (Math.random()*n) + 1; int col = (int) (Math.random()*n) + 1;
		 * if(!a.isOpen(row,col)) { System.out.println("("+row+","+col+")");
		 * 
		 * a.open(row, col);
		 * 
		 * } } System.out.println((double)(a.numberOfOpenSites())/n/n);
		 */
	}
}