/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author HandSome
 */
public class lap1 {

  
    public static void main(String[] args) {
        City city0= new City("Mug", 123456, "Ebrar");
        City city1 = new City("Houston",2099451,"Annise Parker");
        City city2 = new City("Istanbul",1234567,"Omer");
        City city3 = new City("Ankara",854196,"Cem");
        City city4 = new City("Denizli",7418523,"Erol");
        City[]cities = {city0,city1,city2,city3,city4};
        
        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i].getCityName()+"\t "+cities[i].getPop()+"\t"+cities[i].getMayor());
        }
        
        Edge e =new Edge(0,1);
        ArrayList<Edge> edges = new ArrayList();
        edges.add(new Edge(0,1));
        edges.add(new Edge(0,3));
        edges.add(new Edge(0,5));
        System.out.println( e.equals(edges.get(0)));
        
        
    }
    
    
    
}
