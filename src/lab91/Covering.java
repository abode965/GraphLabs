package lab91  ;
import java.util.List;
import lab4.*;
public class Covering{
    
    public static void main(String[] args) {
        
    }
    
    
public boolean isCovered (UnweightedGraph g,int[] vertices){   
    List<Integer> tem=g.getVertices();
    for (int i = 0; i < vertices.length; i++) {
        tem.remove((int)vertices[i]);
    }
    
    for (int i = 0; i < tem.size()-1; i++) {
        if(!g.getNeighbors(tem.get(i)).contains(tem.get(i+1)))
            return false;
    }
    return true;
}


}
