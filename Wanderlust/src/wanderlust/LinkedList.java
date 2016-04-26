/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wanderlust;

/**
 *
 * @author J Jimenez
 */
public class LinkedList {
    
    /**
     * /City sorted start of the list
     */
    public Attraction head = new Attraction(); 

    /**
     * Name sorted start of the list
     */
    public Attraction tail = new Attraction(); 

    
    //establish head node for city sorting

    /**
     * add a link to the city node
     * @param key
     */
        public void addCityLink(Attraction key ){
        if (head.key == null){
            head.key = key;
            return;
        }
        //establish iteration variables
        Attraction current = head; //  
        
        while (current != null) {
            if (current.nextByCity == null){
            Attraction childToUse = new Attraction();
            childToUse.key = key;
            current.nextByCity = childToUse;
            break;
            } else {
            current = current.nextByCity;
            }
        
        } // End while
    }
    
        //establish head node for Name sorting

    /**
     * add a link to the name based node
     * @param key
     */
        public void addNameLink(Attraction key ){
        if (tail.key == null){
            tail.key = key;
            return;
        }
        //establish iteration variables
        Attraction current = tail; //  
        
        while (current != null) {
            if (current.nextByName == null){
            Attraction childToUse = new Attraction();
            childToUse.key = key;
            current.nextByName = childToUse;
            break;
            } else {
            current = current.nextByName;
            }
        
        } // End while
    }


    
    
    
}
