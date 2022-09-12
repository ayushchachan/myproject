/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

/**
 * @author Dippin Chachan
 */
public class UnionFind1 extends UnionFind {

    public UnionFind1(int n) {
        super(n);
    }

    @Override
    public void union(int p, int q) {

        if (!connected(p, q)) {
            data[find(p)] = q;
            this.numConnectedComponents--;
        }

    }

}
