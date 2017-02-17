import edu.princeton.cs.algs4.WeightedQuickUnionUF;;

public class Percolation {
	/*
	 *
   public class Percolation {
   public Percolation(int n)                // create n-by-n grid, with all sites blocked
   public    void open(int row, int col)    // open site (row, col) if  it is not open already
   public boolean isOpen(int row, int col)  // is site (row, col) open?
   public boolean isFull(int row, int col)  // is site (row, col) full?
   public     int numberOfOpenSites()       // number of open sites
   public boolean percolates()              // does the system percolate?

   public static void main(String[] args)   // test client (optional)}
	 */
	private int[][] grid;
    private WeightedQuickUnionUF wc;
	private int openSites, n;
    public Percolation(int n){
        if (n <= 0){
            throw new IllegalArgumentException();
        }
        this.n = n;
		this.grid = new int[n][n];
		for (int i = 0; i < n; i++){
			for (int j = 0 ; j < n; j++){
				this.grid[i][j]=0;
			}
		}
	    this.wc = new WeightedQuickUnionUF(n*n + 2);
        this.openSites = 0;
		for (int i= 1 ;i <= n ;i++){
			this.wc.union(0, i);
		}
		for (int i=(n*n-n+1); i <= (n*n); i++){
			this.wc.union((n*n)+1, i);
		}
	}

    public void open(int row, int col){
        int n = this.n;
		int point;
        int[] xp = new int[4];
        int[] yp = new int[4];
        point = getIndex(row, col, n);
		if (point <= 0 || point > (n*n)){
			throw new IndexOutOfBoundsException();
		}
        if (this.grid[row-1][col-1] == 1){
            return;
        }
		this.grid[row-1][col-1] = 1;
        this.openSites += 1;
        // getting surrounding points;
        xp[0] = row-1; yp[0] = col;
        xp[1] = row; yp[1] = col-1;
        xp[2] = row; yp[2] = col+1;
        xp[3] = row+1; yp[3] = col;
		for (int i=0;i<4;i++){
            int val = getIndex(xp[i], yp[i], n);
            try{
                if (val>0 && val<=(n*n) && this.grid[xp[i]-1][yp[i]-1] == 1){
                    this.wc.union(point, val);
                }
            }catch(Exception e){
                e.getStackTrace();
            }
        }

	}

	public boolean isOpen(int row, int col){
        int n = this.n;
        if (row<1 || row>n || col<1 || col>n){
            throw new IndexOutOfBoundsException();
        }
        if (this.grid[row-1][col-1] == 1){
            return true;
        }
		return false;
	}

	public boolean isFull(int row, int col){
		//int top, bottom,
		int n=this.n;
        if (row<1 || row>n || col<1 || col>n){
            throw new IndexOutOfBoundsException();
        }
		if (this.grid[row-1][col-1] == 1){
			int point = getIndex(row, col, n);
			if (this.wc.connected(0, point)){
				return true;
			}
		}
		return false;
	}

	public int numberOfOpenSites(){
		return this.openSites;
	}

	public boolean percolates(){
		int n = this.n;
        if(this.openSites<=0){
            return false;
        }
        if (this.wc.connected(0, (n*n)+1)){
            return true;
        }
        return false;
	}

    private int getIndex(int i, int j, int n){
        return (i-1)*n + j;
    }

    public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
