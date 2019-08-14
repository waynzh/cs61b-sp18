package hw3.hash;

import java.util.List;
import java.util.ArrayList;

public class OomageTestUtility {
    /**
     * Task3: Have Nice HashCode Spread
     * The number of oomages per bucket has to be within the range (N / 50, N / 2.5).
     *
     * @param oomages a list of Oomage
     * @param M       the number of the buckets
     * @return {@code true}: the given {@code Oomages} would have a nice spread if thrown into
     * {@code M} buckets using their hashCodes.
     */
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        ArrayList<Oomage>[] buckets = (ArrayList<Oomage>[]) new ArrayList[M];
        for (int i = 0; i < M; i++) {
            buckets[i] = new ArrayList<>();
        }
        oomages.forEach(o -> {
            int bucketNumber = (o.hashCode() & 0x7FFFFFFF) % M;  // Provided
            buckets[bucketNumber].add(o);
        });

        int N = oomages.size();      // number of items
        for (int i = 0; i < M; i++) {
            if (buckets[i].size() < N / 50 || buckets[i].size() > N / 2.5) return false;
        }
        return true;
    }
}
