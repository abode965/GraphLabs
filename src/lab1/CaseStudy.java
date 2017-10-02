package lab1;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CaseStudy {
    public static void main(String[] args) {
        List<ArrayList<Edge>> neighbors = new ArrayList<>();

        neighbors.add(new ArrayList<>());
        neighbors.get(0).add(new Edge(0,1));
        neighbors.get(0).add(new Edge(0,2));
        neighbors.get(0).add(new Edge(0,3));
        neighbors.get(0).add(new Edge(0,4));
        neighbors.get(0).add(new Edge(0,5));

        neighbors.add(new ArrayList<>());
        neighbors.get(1).add(new Edge(1,0));
        neighbors.get(1).add(new Edge(1,2));
        neighbors.get(1).add(new Edge(1,3));
        neighbors.get(1).add(new Edge(1,4));

        neighbors.add(new ArrayList<>());
        neighbors.get(2).add(new Edge(2,1));
        neighbors.get(2).add(new Edge(2,0));
        neighbors.get(2).add(new Edge(2,3));
        neighbors.get(2).add(new Edge(2,4));

        neighbors.add(new ArrayList<>());
        neighbors.get(3).add(new Edge(3,0));
        neighbors.get(3).add(new Edge(3,1));
        neighbors.get(3).add(new Edge(3,2));
        neighbors.get(3).add(new Edge(3,4));
        neighbors.get(3).add(new Edge(3,5));

        neighbors.add(new ArrayList<>());
        neighbors.get(4).add(new Edge(4,0));
        neighbors.get(4).add(new Edge(4,1));
        neighbors.get(4).add(new Edge(4,2));
        neighbors.get(4).add(new Edge(4,3));

        neighbors.add(new ArrayList<>());
        neighbors.get(5).add(new Edge(5,0));
        neighbors.get(5).add(new Edge(5,3));

        toString(neighbors);

        //System.out.println(neighbors.get(1).get(0).equals(neighbors.get(0).get(0)));
        //System.out.println(neighbors.get(1).get(0));
        //System.out.println(neighbors.get(0).get(0));
        System.out.println(isPath("1423",neighbors));

    }
    public static void toString(List<ArrayList<Edge>>  neighbors){
        for (ArrayList<Edge> e: neighbors) {
            for (Edge x :e) {
                System.out.println(x.toString());
            }
        }
    }

    public static boolean isPath(String x, List<ArrayList<Edge>> list){
        Map<Character,Integer> vertics=new HashMap<Character,Integer>();
        for (int i = 0; i <x.length()-1; i++) {
            if (!isValidPath(list, x.charAt(i)-48,x.charAt(i+1)-48)){
                System.out.println("There is no Vaild path between" + x.charAt(i) + " " + x.charAt(i + 1));
            return false;
        }
            if( vertics.containsKey(x.charAt(i))){
                System.out.println(x+" is not a PATH cause there are two vertices equal to "+ (x.charAt(i)-48));
                return false;
            }vertics.put( x.charAt(i),i);
        }
        if(vertics.containsKey( x.charAt(x.length()-1)))
        {
            System.out.println(x+" is not a PATH cause there are two vertices equal to "+ (x.charAt(x.length()-1)-48));
            return false;
        }
    return true;
    }

    public static boolean isValidPath(List<ArrayList<Edge>> list,int u , int v ){
        Edge edge = new Edge(u,v);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                Edge e = list.get(i).get(j);
                if(edge.equals(e)){
                  //  System.out.println(e.hashCode()+e.toString());
                    return  true;
            }
            }
        }
        return false;
    }
}
//https://gist.github.com/gennad/791932