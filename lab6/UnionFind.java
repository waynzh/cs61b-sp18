public class UnionFind {
    private int[] parent;

    public UnionFind(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = -1;
        }
    }

    private void validate(int v1) {
        int n = parent.length;
        if (v1 < 0 || v1 >= n) {
            throw new IllegalArgumentException();
        }
    }

    public int sizeOf(int v1) {
        return -parent(find(v1));
    }

    public int parent(int v1) {
        return parent[v1];
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        if (!connected(v1, v2)) {
            if (sizeOf(v1) > sizeOf(v2)) {
                parent[find(v1)] -= sizeOf(v2);   // size计算问题？
                parent[find(v2)] = find(v1);
            } else {
                parent[find(v2)] -= sizeOf(v1);
                parent[find(v1)] = find(v2);
            }
        }
    }

    public int find(int v1) {
        validate(v1);
        int root = v1;
        while (parent(root) > -1) {
            root = parent(root);
        }
        int up = parent(v1);
        while (up != root) {
            parent[v1] = root;
            v1 = up;
            up = parent(v1);
        }
        return root;
    }
}