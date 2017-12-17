package lab9;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lab4.*;

public class Match {

    public static void main(String[] args) {
        int[] vertices = {0, 1, 2, 3, 4, 5, 6};
        int[][] edges = {
            {0, 1}, {0, 6},
            {1, 0}, {1, 6}, {1, 2},
            {2, 6}, {2, 1}, {2, 3},
            {3, 2}, {3, 4}, {4, 5},
            {4, 3}, {4, 5},
            {5, 3}, {5, 4}, {5, 6},
            {6, 0}, {6, 1}, {6, 2}, {6, 5}};

        UnweightedGraph<String> g
                = new UnweightedGraph<>(edges, 7);
        Match newc = new Match();
//        System.out.println(newc.isMatching("0,6-6,2-2,3-3,4", g));
//        System.out.println(newc.isSaturated("0,1-2,3-5,6", g));
//        System.out.println(newc.isPerfect("0,1-2,3-5,6", g));
//        System.out.println(newc.isAlternatingPath("0,6-2,3", "0,6-6,2-2,3-4,5", g));

        //  System.out.println(newc.isCovered(g,a ));
        //System.out.println(newc.isIndependentSet(g, new int[]{1,4}));
        //System.out.println(newc.IsThorem(g, new int[]{1,4}));
        UnweightedGraph g1 = new UnweightedGraph(new int[][]{
            {0, 1}, {0, 2}, {0, 3},
            {1, 0}, {1, 2}, {1, 3}, {1, 4},
            {2, 0}, {2, 1}, {2, 3},
            {3, 0}, {3, 1}, {3, 2},
            {4, 1}
        }, 5);
        boolean[][] temp = newc.heuristic(g1);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                System.out.println(temp[i][j]);
            }
        }
    }

    boolean isMatching(String s, UnweightedGraph g) {
        String[] edges = s.split("-");
        for (int i = 0; i < edges.length; i++) {
            int iw = Integer.parseInt(edges[i].split(",")[0]);
            int ow = Integer.parseInt(edges[i].split(",")[1]);
            if (!g.getNeighbors(iw).contains(ow)) {
                return false;
            }
        }
        for (int i = 0; i < edges.length - 1; i++) {
            int iw = Integer.parseInt(edges[i].split(",")[1]);
            int ow = Integer.parseInt(edges[i + 1].split(",")[1]);
            if (g.getNeighbors(iw).contains(ow)
                    || g.getNeighbors(Integer.parseInt(edges[i].split(",")[0])).contains(Integer.parseInt(edges[i + 1].split(",")[0]))) {
                return false;
            }
        }
        return true;
    }

    ArrayList<Integer> isSaturated(String s, UnweightedGraph g) {
        Match n = new Match();
        ArrayList<Integer> vert = new ArrayList<>();
        String[] edges = s.split("-");
        for (int i = 0; i < edges.length; i++) {
            if (n.isMatching(edges[i], g)) {
                if (!vert.contains(Integer.parseInt(edges[i].split(",")[0]))) {
                    vert.add(Integer.parseInt(edges[i].split(",")[0]));
                }
                if (!vert.contains(Integer.parseInt(edges[i].split(",")[1]))) {
                    vert.add(Integer.parseInt(edges[i].split(",")[1]));
                }
            }
        }

        return vert;
    }

    boolean isPerfect(String s, UnweightedGraph g) {
        Match newc = new Match();
        return newc.isSaturated(s, g).size() == g.getVertices().size();
    }
    //lab 10 
    /*
     1.Given a graph, a matching, and a path, write a
     method to determine if the path is an
     alternating path.
     */

    boolean isAlternatingPath(String matching, String path, UnweightedGraph g) {
        if (isMatching(path, g) || !isPathValid(g, path)) {
            return false;
        }
        Set<int[]> outOfMatching = new HashSet<>();

        for (Object vertex : g.getVertices()) {

            int u = (int) g.getIndex(vertex);

            for (Object v : g.getNeighbors(u)) {

                boolean isMatchingEdge = false;
                String[] edges = matching.split("-");
                for (int i = 0; i < edges.length; i++) {
                    int iw = Integer.parseInt(edges[i].split(",")[0]);
                    int ow = Integer.parseInt(edges[i].split(",")[1]);
                    if ((u == iw && (int) v == ow) || (u == ow && (int) v == iw)) {
                        isMatchingEdge = true;
                        break;
                    }

                }

                if (!isMatchingEdge) {
                    outOfMatching.add(new int[]{u, (int) v});
                }

            }

        }
        String[] edges = path.split("-");
        for (int i = 0; i < edges.length - 1; i++) {
            int u = Integer.parseInt(edges[i].split(",")[0]);
            int v = Integer.parseInt(edges[i].split(",")[1]);
            if (i % 2 == 0) {

                boolean isInMatching = false;
                String[] edges1 = matching.split("-");
                for (int j = 0; j < edges1.length; j++) {
                    int edgeU = Integer.parseInt(edges1[j].split(",")[0]);
                    int edgeV = Integer.parseInt(edges1[j].split(",")[1]);
                    if (u == edgeU && v == edgeV) {
                        isInMatching = true;
                        break;
                    }

                }

                if (!isInMatching) {
                    return false;
                }

            } else {

                boolean isInOutOfMatching = false;

                for (int[] edge : outOfMatching) {
                    int edgeU = edge[0];
                    int edgeV = edge[1];

                    if (u == edgeU && v == edgeV) {
                        isInOutOfMatching = true;
                        break;
                    }

                }

                if (!isInOutOfMatching) {
                    return false;
                }

            }

        }

        return true;
    }

    boolean isPathValid(UnweightedGraph g, String path) {
        String[] edges = path.split("-");
        for (int i = 0; i < edges.length - 1; i++) {
            int iw = Integer.parseInt(edges[i].split(",")[0]);
            int ow = Integer.parseInt(edges[i + 1].split(",")[0]);

            if (ow != iw) {
                return false;
            }
        }

        return true;
    }

    /*
     2.Write a method to check whether the given set
     is a covering.
     */
    public boolean isCovered(UnweightedGraph g, int[] v) {
        List<Integer> tem = g.getVertices();
        for (int i = 0; i < v.length; i++) {
            Integer a = v[i];
            tem.remove(a);
        }

        for (int i = 0; i < tem.size() - 1; i++) {
            if (isNeighbor(g, tem.get(i), tem.get(i + 1))) {
                return false;
            }
        }
        return true;
    }
    /*
     3.Write a method to check whether the given set
     is an independent set
     */

    public boolean isIndependentSet(UnweightedGraph g, int[] v) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = 1 + i; j < v.length - i; j++) {
                if (isNeighbor(g, v[i], v[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isNeighbor(UnweightedGraph g, int u, int v) {
        return g.getNeighbors(u).contains(v);
    }
    /*
     A set S ⊆ V is an independent set of G if and only if V\S is
     a covering of G.
      Write a program to validate the theorem:
     Take an independent set as an input
     Find the V\S
     Check whether the V\S is a covering of G.
     */

    public boolean IsThorem(UnweightedGraph g, int[] v) {
        if (!isIndependentSet(g, v)) {
            return false;
        }
        int[] a = getDifference(g.getVertices(), v);
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
        if (isCovered(g, getDifference(g.getVertices(), v))) {
            return true;
        }

        return false;
    }

    public int[] getDifference(List<Object> a, int b[]) {
        for (int i = 0; i < b.length; i++) {
            a.remove((Integer) b[i]);
        }
        int tempArray[] = new int[a.size()];
        for (int i = 0; i < a.size(); i++) {
            tempArray[i] = (int) a.get(i);
        }
        return tempArray;
    }

    //Lab11 
/*
     Ni
     [j] = true if a neighbor of i is colored in j
     A(i): Adjacency list of i
     C(i): Color of i
     */
    public boolean[][] heuristic(UnweightedGraph g) {
        boolean[][] color = new boolean[g.getSize()][g.getSize()];
        int j = 0;
        int[] c = new int[g.getSize()];
        for (int i = 0; i < g.getSize(); i++) {
            for (int k = 0; k < g.getSize(); k++) {
                color[i][j] = false;
            }
        }

        for (int i = 0; i < g.getSize(); i++) {
            while (color[i][j]) {
                j++;
            }
            for (Object v : g.getNeighbors(i)) {
                color[(int) v][j] = true;
            }
            c[i] = (Integer) j;
        }
        for (int i = 0; i < c.length; i++) {
            System.out.println(c[i]);
        }
        return color;
    }

}
