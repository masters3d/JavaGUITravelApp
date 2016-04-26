package wanderlust;


/**
 * Launcher for project
 * 
 * @author J Jimenez
 */
public class Wanderlust {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AttractionViewWindow view = new AttractionViewWindow("Wanderlust: Travel Planner");
        view.pack();
        view.setLocation(200, 200);
        view.setVisible(true);
    }
    
}
