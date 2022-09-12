/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myproject;

import java.util.*;
import java.util.Map.Entry;

/**
 *
 * @author Ayush Chachan
 */
public class HackerRank {
    
    public static List<Integer> bfs(int n, int m, List<List<Integer>> edges, int s) {
        
        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for (int i = 1; i<= n; i++) {
            graph.put(i, new HashSet<>());
        }
        
        for (List<Integer> l : edges) {
            int u = l.get(0);
            int v = l.get(1);
            
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        
        // now that grapg is ready
        // we will run bfs starting from s;
        HashMap<Integer, Integer> d = new HashMap<>();
        d.put(s, 0);
        
        LinkedList<Integer> Q = new LinkedList<>();
        Q.add(s);
        
        while (!Q.isEmpty()) {
            int c = Q.removeFirst();
            
            for (int v : graph.get(c)) {
                if (!d.containsKey(v)) {
                    Q.add(v);
                    d.put(v, d.get(c) + 6);
                }
            }
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int i = 1; i<= n; i++) {
            if (d.containsKey(i)) {
                if (i != s) answer.add(d.get(i));
            } else {
                answer.add(-1);
            }
        }
        return answer;

    }
    
    public static List<Integer> dijkstra(int n, List<List<Integer>> edges, int s) {
        // Making the graph object in suitable dictionary form
        HashMap<Integer, HashMap<Integer, Integer>> graph = new HashMap<>();
        
        for (int i = 1; i<= n; i++) {
            graph.put(i, new HashMap<>());
        }
        
        for (List<Integer> l : edges) {
            int u = l.get(0);
            int v = l.get(1);
            
            int w = l.get(2);
            
            graph.get(u).put(v, w);
            graph.get(v).put(u, w);
        }
        
        // now that graph is ready
        
        // we will run dijkstra starting from s;
        HashMap<Integer, Integer> d = new HashMap<>();      // final map containg distance 
                                                            // of each vertex v from s
                                                            
        AdaptablePriorityQueue<Integer, Integer> Q = new HeapAdaptablePriorityQueue<>(); 
        HashMap<Integer, Integer> D = new HashMap<>();      // it mains the key-value pairs 
                                                            // same as in priority queue Q
        HashMap<Integer, Entry<Integer, Integer>> veMap = new HashMap<>();
        
        for (int i = 1; i <= n; i++) {
            Entry<Integer, Integer> e;
            if (i == s) {
                e = Q.insert(0, i);
                D.put(i, 0);
            } else {
                e = Q.insert(Integer.MAX_VALUE, i);
                D.put(i, Integer.MAX_VALUE);
            }
            veMap.put(i, e);
        }
        
        HashSet<Integer> S = new HashSet<>();       // the cloud of discovered vertex
        
        while (S.size() != n) {
            Entry<Integer, Integer> e = Q.removeMin();
            
            int dist = e.getKey();
            int u = e.getValue();
            
            d.put(u, dist);
            S.add(u);
            
            //relax each neighbour v of vertex u
            for (int v : graph.get(u).keySet()) {
                if (D.get(v) > D.get(u) + graph.get(u).get(v)) {
                    int shortest_found = D.get(u) + graph.get(u).get(v);
                    
                    Entry<Integer, Integer> pqentry = veMap.get(v);
                    D.put(v, shortest_found);
                    Q.replaceKey(pqentry, shortest_found);
                }
            }
        }
        
        ArrayList<Integer> answer = new ArrayList<>();
        
        for (int i = 1; i<= n; i++) {
            if (d.containsKey(i)) {
                if (i != s) answer.add(d.get(i));
            } else {
                answer.add(-1);
            }
        }
        
        return answer;
    }
    
    private static class MyEntry {
        int vertex;
        int distance;
        
        public MyEntry(int v, int d) {
            vertex = v;
            distance =d;
        }
    }
    
    private static class MyEntryComparator implements Comparator<MyEntry>{

        @Override
        public int compare(MyEntry o1, MyEntry o2) {
            return Integer.compare(o1.distance, o2.distance);
        }
    
    }
}
