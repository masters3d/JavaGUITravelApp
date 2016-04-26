package wanderlust;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Instantiable class managing a the attractions
 *
 * @author J JIMENEZ
 */
public class AttractionManager {

    private String fileLocation = "destinationlist.bin";

    private ArrayList<Attraction> attractionList;

    /**
     * prints the attraction in a nice format
     * @param each
     */
    private void printAttraction(Attraction each){
    
        System.out.format( "Attraction Name: %s \n" +
        "Attraction City: %s \n" +
        "Attraction Country: %s\n" +
        "Distance from Seattle: %.2f \n \n",each.getName(), each.getCity(),
        each.getCountry(), each.getDistanceFromHome());
    }
    
    /**
     * Generates a Linked List with two nodes for different sorting
     */
    public void generateLinkedListByCity(){
    
        LinkedList attractionsByCityLinkedList = new LinkedList();
        ArrayList<Attraction> attractionListbyCity = (ArrayList<Attraction>) attractionList.clone();    
        Collections.sort(attractionListbyCity, 
                (o1, o2) -> o1.getCityAndAttractionName().compareTo(o2.getCityAndAttractionName()));

                System.out.println("<< ATTRACTIONS SORTED BY CITY >>");
                // populate the City Sorting
                for (Attraction each : attractionListbyCity) {
                   attractionsByCityLinkedList.addCityLink(each);
                   printAttraction(each);
                }
                System.out.println("<< ATTRACTIONS SORTED BY NAME >>");

                // populate the Name Sorting
                for (Attraction each : attractionList) {
                   attractionsByCityLinkedList.addNameLink(each);
                    printAttraction(each);
                }
    }

    
    
    /**
     * The default constructor.
     */
    public AttractionManager() {
        attractionList = new ArrayList<>();
        readCollection();
    }

    /**
     * adds attraction to the managing object with no duplicates
     * @param c
     * @return
     */
    public boolean addAttraction(Attraction c) {
        boolean success = false;
        if (!attractionList.contains(c)) {
            attractionList.add(c);
            success = writeCollection();
        }
        return success;
    }

    /**
     * delete and attraction and writes to file
     * @param c
     */
    public void deleteAttraction(Attraction c) {
        attractionList.remove(c);
        writeCollection();
    }

    /**
     * Sorts the current list of attractions and converts it to an array.
     *
     * @return Attraction[] the current list of attractions as a sorted
 array
     */
    public Attraction[] getSortedArray() {
        Collections.sort(attractionList);
        return attractionList.toArray(new Attraction[attractionList.size()]);
    }

    /**
     * Serializes the attractions.
     *
     * @return boolean - true if saved, false if not
     */
    private boolean writeCollection() {
        boolean success = true;
        try (FileOutputStream fos
                = new FileOutputStream(fileLocation);
                ObjectOutputStream output
                = new ObjectOutputStream(fos)) {
            output.writeObject(attractionList);
        } catch (Exception ex) {
            System.out.println("Cannot write to file:\n"
                    + ex.getMessage());
            success = false;
        }
        return success;
    }

    /**
     * Populates the attractions list with the serialized file contents.
     *
     * @return boolean - true if successful, false if not
     */
    private boolean readCollection() {
        boolean success = true;
        File fileOnDisk = new File(fileLocation);
        if (fileOnDisk.exists()) {
            try (FileInputStream fis
                    = new FileInputStream(fileLocation);
                    ObjectInputStream input
                    = new ObjectInputStream(fis)) {
                attractionList = (ArrayList<Attraction>) input.readObject();
            } catch (Exception ex) {
                System.out.println("Cannot read from file:\n"
                        + ex.getMessage());
                success = false;
            }
        }
        return success;
    }
    
    /**
     * Deletes all the objects
     */
    public void clearAllObjects()
    {
        attractionList.clear();
    }

}
