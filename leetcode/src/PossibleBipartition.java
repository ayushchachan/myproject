import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/*
   Q. 886
 * We want to split a group of n people (labeled from 1 to n)
 * into two groups of any size.
 * Each person may dislike some other people,
 * and they should not go into the same group.

  Given the integer n and the array dislikes where dislikes[i] = [ai, bi]
* indicates that the person labeled ai does not like the person labeled bi,
* return true if it is possible to split everyone into two groups in this way.
*/
public class PossibleBipartition {
    public static void main(String[] args) {
        int n = 4;
        int[][] dislikes = {{1, 2}, {1, 3}, {2, 3}};

        PossibleBipartition p = new PossibleBipartition();
        System.out.println(p.possibleBipartition(n, dislikes));

    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        HashMap<Integer, HashSet<Integer>> G = new HashMap();

        for (int i = 0; i < n; i++) {
            G.put(i, new HashSet<>());
        }

        for (int i = 0; i < dislikes.length; i++) {
            int[] edge = dislikes[i];
            int u, v;
            u = edge[0] - 1;
            v = edge[1] - 1;
            G.get(u).add(v);
            G.get(v).add(u);
        }

        // use BFS to check whether the graph is bipartite i.e. 2 colorable
        // let two colors be 1 and 2
        boolean[] visit = new boolean[n];
        HashMap<Integer, Boolean> color = new HashMap<>();

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                PriorityQueue<Integer> Q = new PriorityQueue<>();
                Q.add(i);
                color.put(i, true);

                while (!Q.isEmpty()) {
                    int x = Q.remove();
                    visit[x] = true;

                    boolean color_x = color.get(x);

                    for (int v : G.get(x)) {
                        if (!visit[v]) {
                            Q.add(v);
                            color.put(v, !color_x);
                        } else if (color.get(v) == color_x) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean isTwoColorable(int source, HashMap<Integer, HashSet<Integer>> G,
                       boolean[] visit, HashMap<Integer, Boolean> color ) {

        return true;

    }
}
