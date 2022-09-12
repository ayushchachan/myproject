
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 * @author Dippin Chachan
 */
public class UnionFind2 extends UnionFind {

    int[] size;

    public UnionFind2(int n) {
        super(n);
        size = new int[n];

        for (int i = 0; i < n; i++) {
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);

        if (rootP == rootQ) return;

        if (size[rootP] < size[rootQ]) {
            size[rootQ] = size[rootQ] + size[rootP];
            data[rootP] = rootQ;
        } else {
            size[rootP] = size[rootP] + size[rootQ];
            data[rootQ] = rootP;
        }
        numConnectedComponents--;
    }


}
