/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author HandSome
 */
public class Edge {
    int u ; 
    int v ;

    @Override
    public String toString() {
        return String.valueOf(this.u)+" "+String.valueOf(this.v);
    }

    public  boolean equals(Object o){
        Edge e = (Edge) o ;
       return (e.u==this.u &&e.v==this.v )||(e.u==this.v &&e.v==this.u);
    }

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }
}
