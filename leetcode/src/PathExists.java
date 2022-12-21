/*
    Q. 1971
 */

public class PathExists {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1}, {0, 2}, {3, 5}, {5, 4}, {4, 3}};
        int source = 0, destination = 5;

    }
}

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        WeightedQuickUnionUF g = new WeightedQuickUnionUF(n);

        for (int[] e : edges) {
            g.union(e[0], e[1]);

        }
        return g.connected(source, destination);
    }
}

/**
 * @author Chankit Chachan
 */
class WeightedQuickUnionUF {
    private int count;          // number of connected components
    private int[] parent;
    private int[] sz;           // size (no. of nodes) of subtree roted at i


    /**
     * Initialize N empty sets
     */
    public WeightedQuickUnionUF(int N) {
        parent = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
        count = N;
    }

    /**
     * Returns the number of sets.
     */
    public int count() {
        return this.count;
    }

    public int find(int p) {
        validate(p);
        while (p != parent[p]) p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    /**
     * Returns true if p and q are in the same component
     */
    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return find(p) == find(q);
    }

    /**
     * Adds a connection between p and q
     */
    public void union(int p, int q) {
        //        System.out.println("union is called with p = " + p + ", q = " + q);
        //        System.out.println("id = " + java.util.Arrays.toString(id));
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) return;

        if (sz[qRoot] < sz[pRoot]) {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        }


        count--;
        //        System.out.println("id = " + java.util.Arrays.toString(id));
        //        System.out.println("------------method terminated--------------");
    }

}

