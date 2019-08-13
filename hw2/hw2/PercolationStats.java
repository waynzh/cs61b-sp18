package hw2;

import edu.princeton.cs.introcs.StdRandom;

public class PercolationStats {
    private int[] x;
    private int T;

    /**
     * perform T independent experiments on an N-by-N grid
     *
     * @param N  n x n grid
     * @param T  T times experiment
     * @param pf percolation factory
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("can't less than 0!!!");
        }

        this.T = T;
        x = new int[T];

        for (int i = 0; i < T; i++) {
            int count = 0;
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int rowRand = StdRandom.uniform(N); // Returns a random integer uniformly in [0, n).
                int colRand = StdRandom.uniform(N);
                p.open(rowRand, colRand);
                count += 1;
            }

            x[i] = count;
        }

    }

    /**
     * Sample mean of percolation threshold
     *
     * @return mean: μ
     */
    public double mean() {
        int sum = 0;
        for (int i : x) {
            sum += i;
        }
        return (double) sum / T;
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return standard deviation: σ
     */
    public double stddev() {
        int sum = 0;
        for (int i : x) {
            sum += (i - mean()) * (i - mean());
        }
        return (double) sum / (T - 1);
    }

    /**
     * low endpoint of 95% confidence interval
     *
     * @return low : [low, high]
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return high : [low, high]
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
