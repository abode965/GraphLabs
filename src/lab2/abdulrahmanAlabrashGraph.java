package lab2;

import java.util.*;

public class abdulrahmanAlabrashGraph extends UnweightedGraph {

//    public static List<List<Integer>> getConnectedComponents(Graph g) {
//        List<Integer> connected = new ArrayList<>();
//        List<Integer> notConnected = new ArrayList<>();
//        List<List<Integer>> e = new ArrayList<>();
//        for (int i = 0; i < g.getVertices().size(); i++) {
//            List<Integer> temp = g.getNeighbors((int) g.getVertices().get(i));
//            if (temp.size() == 1) {
//                notConnected.add((Integer) g.getVertices().get(i));
//            } else
//                connected.add((Integer) g.getVertices().get(i));
//
//        }
//
//        e.add(notConnected);
//        e.add(connected);
//        return e;
//    }

    public static List<List<Integer>> getConnectedComponents(Graph g) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (g == null || g.getSize() == 0) {
            return rst;
        }
        HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (int i = 0; i < g.getSize(); i++) {
            map.put((Integer) g.getVertices().get(i), false);
        }
        for (int i = 0; i <g.getSize(); i++) {
            if (!map.get((Integer) g.getVertices().get(i))) {
                bfs(rst, (Integer) g.getVertices().get(i), map,g);
            }
        }
        return rst;
    }

    private static void bfs(List<List<Integer>> rst, int vertex, HashMap<Integer, Boolean> map,Graph g) {
        Queue queue = new LinkedList<Integer>();
        List<Integer> list = new ArrayList<Integer>();
        queue.add(vertex);
        map.put(vertex, true);
        int temp;
        while (!queue.isEmpty()) {
            temp =(int) queue.poll();
            list.add(temp);
            for (int i = 0; i <g.getNeighbors(vertex).size() ; i++) {
                if (!map.get(g.getNeighbors(vertex).get(i))) {
                    queue.offer(g.getNeighbors(vertex).get(i));
                    map.put((Integer) g.getNeighbors(vertex).get(i), true);
                }
            }
        }
        Collections.sort(list);
        rst.add(list);
    }



}


