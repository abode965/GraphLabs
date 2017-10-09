package lab2;

import java.util.List;

/**
 *
 * @author FSMBLM1
 */
public interface Graph <T>{
    int getSize();
    List <T> getVertices();
    int getIndex(T t );
    List <Integer> getNeighbors(int index);
    T getVertex (int index);
    int getDegree(int v);
    void printEdges();
    boolean clear(); 
    boolean addVertex(T index ); 
    boolean addEdge (int u , int v ); 
    
    AbstractGraph<T>.Tree dsf(int v);
    AbstractGraph<T>.Tree bsf(int v);
}
