package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int size;
    private int num;
    private int tail;
    private int head;

    private WeightedQuickUnionUF wqu;
    private WeightedQuickUnionUF wquHead;

    /**
     * create N-by-N grid, with all sites initially blocked
     *
     * @param N .
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("N can't less than 0!!!");
        }
        size = N;
        grid = new int[N][N];           // default: 0
        num = 0;

        wqu = new WeightedQuickUnionUF(N * N + 2);     // 1: head(n^2) 2: tail(n^2+1)
        wquHead = new WeightedQuickUnionUF(N * N + 1); // 1: head(n^2)
        head = N * N;           // virtual top site
        tail = N * N + 1;       // virtual bottom site
    }

    public static void main(String[] args) {

    }

    /**
     * helper method: xyTo1D 2D --> 1D
     *
     * @param x .
     * @param y .
     * @return 1D index
     */
    private int xyTo1D(int x, int y) {
        return size * x + y;
    }

    /**
     * helper method: connect , check if needs to union and union open sites.
     *
     * @param x .
     * @param y .
     */
    private void connect(int x, int y) {
        int index = xyTo1D(x, y);
        if (x == size - 1) {
            wqu.union(index, tail);
        }
        if (x == 0) {
            wqu.union(index, head);
            wquHead.union(index, head);
        }

        if (x != 0 && isOpen(x - 1, y)) {
            wqu.union(index, xyTo1D(x - 1, y));
            wquHead.union(index, xyTo1D(x - 1, y));
        }
        if (y != 0 && isOpen(x, y - 1)) {
            wqu.union(index, xyTo1D(x, y - 1));
            wquHead.union(index, xyTo1D(x, y - 1));

        }
        if (x != size - 1 && isOpen(x + 1, y)) {
            wqu.union(index, xyTo1D(x + 1, y));
            wquHead.union(index, xyTo1D(x + 1, y));
        }
        if (y != size - 1 && isOpen(x, y + 1)) {
            wqu.union(index, xyTo1D(x, y + 1));
            wquHead.union(index, xyTo1D(x, y + 1));
        }
    }

    /**
     * open the site (row, col) if it is not open already
     *
     * @param row .
     * @param col .
     */
    public void open(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Outside grid's prescribed range!!!");
        }
        if (grid[row][col] != 1) {
            grid[row][col] = 1;
            num += 1;
            connect(row, col);
        }
    }

    /**
     * is the site (row, col) open?
     *
     * @param row .
     * @param col .
     * @return {@code true}: is open / {@code false}: isn't
     */
    public boolean isOpen(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Outside grid's prescribed range!!!");
        }
        return grid[row][col] == 1;

    }

    /**
     * is the site (row, col) full?
     * 只于头相接，避免尾部 backwash.
     *
     * @param row .
     * @param col .
     * @return {@code true}: is full / {@code false}: isn't
     */
    public boolean isFull(int row, int col) {
        if (row > size - 1 || col > size - 1 || row < 0 || col < 0) {
            throw new java.lang.IndexOutOfBoundsException("Outside grid's prescribed range!!!");
        }
//        for (int i = 0; i < size; i++) {
//            if (wqu.connected(i, xyTo1D(row, col))) {
//                return true;
//            }
//        }
//        return false;
        return wquHead.connected(head, xyTo1D(row, col));
    }

    /**
     * return the number of open sites
     *
     * @return {@code num}: number of open sites
     */
    public int numberOfOpenSites() {
        return num;
    }

    /**
     * does the system percolate?
     * 直接用 wqu 头尾判断即可。
     *
     * @return {@code true}: from top to bottom / {@code false}: isn't percolate
     */
    public boolean percolates() {
//        for (int i = 0; i < size; i++) {
//            if (wqu.connected(i, tail)) {
//                return true;
//            }
//        }
//        return false;
        return wqu.connected(head, tail);
    }


}
