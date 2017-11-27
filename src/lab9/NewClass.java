package lab9; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lab4.*;

public class NewClass{
 
    public static void main(String[] args) {
        int[] vertices = {0,1,2,3,4,5,6};
        int[][]edges={
            {0,1},{0,6},
            {1,0},{1,6},{1,2},
            {2,6},{2,1},{2,3},
            {3,2},{3,4},{4,6},
            {4,3},{4,5},
            {5,3},{5,4},{5,6},
            {6,0},{6,1},{6,2},{6,5}};
        
        
        UnweightedGraph<String > g= 
                new UnweightedGraph<>(edges,7);
        NewClass newc= new NewClass();
        System.out.println( newc.isMatching("0,1-2,3-5,6",g));
        System.out.println(newc.isSaturated("0,1-2,3-5,6",g));
        System.out.println(newc.isPerfect("0,1-2,3-5,6", g));
        System.out.println(newc.isAlternatingPath("0,1-1,2-2,3", g));
        
    }
    
     boolean isMatching(String s,UnweightedGraph g){
        String [] edges=s.split("-");
        for (int i = 0; i < edges.length; i++) {
            int iw =Integer.parseInt(edges[i].split(",")[0]);
            int ow=Integer.parseInt(edges[i].split(",")[1]);
            if(!g.getNeighbors(iw).contains(ow))
                    return false ; 
        }
        for (int i = 0; i < edges.length-1; i++) {
            int iw =Integer.parseInt(edges[i].split(",")[1]);
            int ow=Integer.parseInt(edges[i+1].split(",")[1]);
            if(g.getNeighbors(iw).contains(ow)||
                    g.getNeighbors(Integer.parseInt(edges[i].split(",")[0])).contains(Integer.parseInt(edges[i+1].split(",")[0])))
                return false; 
        }
     return true ;   
    }
     
     ArrayList<Integer> isSaturated(String s , UnweightedGraph g){
         NewClass n = new NewClass();
         ArrayList<Integer> vert= new ArrayList<>();
         String [] edges=s.split("-");
         for (int i = 0; i < edges.length; i++) {
             if(n.isMatching(edges[i], g)){
                 if(!vert.contains(Integer.parseInt(edges[i].split(",")[0])))
                     vert.add(Integer.parseInt(edges[i].split(",")[0]));
                 if(!vert.contains(Integer.parseInt(edges[i].split(",")[1])))
                     vert.add(Integer.parseInt(edges[i].split(",")[1]));
         }
         }
         
         return vert;
     }
     
     boolean isPerfect(String s,UnweightedGraph g){
         NewClass newc = new NewClass();
         return newc.isSaturated(s, g).size()==g.getVertices().size();
     }
    
     boolean isAlternatingPath(String s ,UnweightedGraph g ){
         String edges[]=s.split("-");
         for (int i = 0; i < edges.length-1; i++) {
             if(isMatching(edges[i], g)==isMatching(edges[i+1], g))
                 return false ; 
         }
         return true ;
     }
}