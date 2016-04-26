package wanderlust;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Presentation layer for Ice Cream Fridge
 *
 * @author J JIMENEZ
 */
public class AttractionViewWindow extends JFrame {

    private Container container;
    private AttractionManager attractionsManager;
    private JComboBox<Attraction> itemNames;
    private JTextArea textArea;
    private JTextField txtCountry;
    private JTextField txtCity;
    private JTextField txtAttraction;
    private JTextField txtDistance;
    private JTextField txtDuration;
    private JButton btnNew;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnPrintLog;
    private final static String newline = "\n";
    private final JLabel nuLine = new JLabel(" ");
    private final JLabel lblTitle = new JLabel("AttractionsApp by J. Cheyo Jimenez");
    private final String errorMsgNameOfAttraction = "Text is too long";
    private final String errorMsgDistance = "Invalid Distance number entered";
    private final String errorMsgDuration = "Invalid Duration entered";


     /**
     * The constructor that creates the JFrame and calls the other methods
     * of this class to build the GUI, wire the components, and initialize
     * the display.
     * 
     * @param title the title of the underlying JFrame
     */
    
    public AttractionViewWindow(String title) {
        super(title);
        attractionsManager = new AttractionManager();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container = this.getContentPane();
        addWindowComponents();
        addEventHandlers();
        initializeDisplay();
        
    }

     /**
     * Show errors based on the message string
     * 
     * @param message message to display
     */   
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(AttractionViewWindow.this, message);
    }

    /**
     * Builds the GUI containers and components.
     */
    
    private void addWindowComponents() {
        // main panel
        JPanel mainPanel = new JPanel();
        JPanel titlePanel = new JPanel();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel rightPnlFields = new JPanel();
        JPanel leftPnlFields = new JPanel();        
        //combo box
        itemNames = new JComboBox<>(attractionsManager.getSortedArray());
        leftPnlFields.add(itemNames, BorderLayout.NORTH);
        //buttons
        JPanel pnlButtons = new JPanel();
        btnNew = new JButton("New");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnPrintLog = new JButton("PrintLog");
        pnlButtons.add(btnNew);
        pnlButtons.add(btnUpdate);
        pnlButtons.add(btnDelete);
        pnlButtons.add(btnPrintLog);
        container.add(pnlButtons, BorderLayout.SOUTH);
        //fields

        rightPnlFields.setLayout(new BoxLayout(rightPnlFields, BoxLayout.Y_AXIS));
        leftPnlFields.setLayout(new BoxLayout(leftPnlFields, BoxLayout.Y_AXIS));
        
        //Name of Attraction
        JPanel pnlName = new JPanel();
        pnlName.add(new JLabel("    Name of Attraction: "));
        txtAttraction = new JTextField(15);
        pnlName.add(txtAttraction);
        
        //City Name
        JPanel pnlCity = new JPanel();
        pnlCity.add(new JLabel("                 City Name: "));
        txtCity = new JTextField(15);
        pnlCity.add(txtCity);
        
        //Country Name
        JPanel pnlCountry = new JPanel();
        pnlCountry.add(new JLabel("           Country Name: "));
        txtCountry = new JTextField(15);
        pnlCountry.add(txtCountry);
        
        //Distance from Seattle
        JPanel pnlDistance = new JPanel();
        pnlDistance.add(new JLabel("Distance from Seattle: "));
        txtDistance = new JTextField(15);
        pnlDistance.add(txtDistance);
        
        //Duration of Stay
        JPanel pnlDuration = new JPanel();
        pnlDuration.add(new JLabel("        Duration of Stay: "));
        txtDuration = new JTextField(15);
        pnlDuration.add(txtDuration);
        
        
        //add to right panel
        // set fonts for title and names
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        rightPnlFields.add(lblTitle);
        rightPnlFields.add(nuLine);
        rightPnlFields.add(pnlName);
        rightPnlFields.add(pnlCity);
        rightPnlFields.add(pnlCountry);
        rightPnlFields.add(pnlDistance);
        rightPnlFields.add(pnlDuration);
        // make textarea
        textArea = new JTextArea(10,18);
        textArea.setEditable(false);

        // add to left side
        leftPnlFields.add(textArea);
        //add to container

        rightPanel.add(rightPnlFields, BorderLayout.CENTER);
        
        leftPanel.add(leftPnlFields, BorderLayout.CENTER);
        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);
        container.add(mainPanel, BorderLayout.CENTER);             
    }

    /**
     * Creates event handlers for the combo box and buttons.
     */
    private void addEventHandlers() {
        //combo box event handler
        itemNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //populate fields
                if (itemNames.getItemCount() > 0) {
                    Attraction c = (Attraction) itemNames.getSelectedItem();
                    if (c != null) {
                        String name = c.getName();
                        String city = c.getCity();
                        String country = c.getCountry();
                        String duration = Double.toString(c.getDurationOfStay());
                        String distance = Double.toString(c.getDistanceFromHome());
                        txtAttraction.setText(name);
                        txtCity.setText(city);
                        txtCountry.setText(country);
                        txtDistance.setText(distance);
                        txtDuration.setText(duration);
                        textArea.setText(null);
                        UpdateTxtArea(name,city,country, distance, duration);
                        
                    } else {
                        txtAttraction.setText("");
                        txtCity.setText("");
                        txtCountry.setText("");
                        txtDistance.setText("");
                        txtDuration.setText("");
                        textArea.setText(null);
                    }
                }
            }
        });
        
           //PrintLong button event handler
        btnPrintLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attractionsManager.generateLinkedListByCity();
                
            }});
                
                
        //update button event handler
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attraction c = (Attraction) itemNames.getSelectedItem();
                String name = c.getName();
                String city = c.getCity();
                String country = c.getCountry();
                String duration = Double.toString(c.getDurationOfStay());
                String distance = Double.toString(c.getDistanceFromHome());
                if (!txtAttraction.getText().isEmpty()) {

                    if (!c.setAttractionName(txtAttraction.getText())) {
                        showErrorMessage(errorMsgNameOfAttraction);
                    }
                    if (!c.setCityLimitLength(txtCity.getText())) {
                            showErrorMessage(errorMsgNameOfAttraction);
                        }
                    if (!c.setCountryLimitLength(txtCountry.getText())) {
                            showErrorMessage(errorMsgNameOfAttraction);
                        }
                    
                    if (!c.setDistanceWithString(txtDistance.getText())) {
                        showErrorMessage(errorMsgDistance);
                    }
                    if (!c.setDurationWithString(txtDuration.getText())) {
                        showErrorMessage(errorMsgDuration);
                    }
                    itemNames.updateUI();
                    textArea.setText(null);
                    UpdateTxtArea(name,city,country, distance, duration);
                }
            }
        });
        //new/add button event handler
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (btnNew.getText().equals("New")) {
                    txtAttraction.setEnabled(true);
                    txtAttraction.setText("");
                    txtCity.setText("");
                    txtCity.setEnabled(true);
                    txtCountry.setText("");
                    txtCountry.setEnabled(true);
                    txtDistance.setEnabled(true);
                    txtDistance.setText("");
                    txtDuration.setEnabled(true);
                    txtDuration.setText("");
                    btnNew.setText("Add");
                    btnDelete.setEnabled(false);
                    btnUpdate.setEnabled(false);
                    txtAttraction.requestFocus();
                } else {
                    if (!txtAttraction.getText().isEmpty() ) {
                        Attraction c = new Attraction();
                        if (!c.setAttractionName(txtAttraction.getText())) {
                            showErrorMessage(errorMsgNameOfAttraction);
                        }
                        if (!c.setCityLimitLength(txtCity.getText())) {
                            showErrorMessage(errorMsgNameOfAttraction);
                        }
                        if (!c.setCountryLimitLength(txtCountry.getText())) {
                            showErrorMessage(errorMsgNameOfAttraction);
                        }
                        
                        if (!c.setDistanceWithString(txtDistance.getText())) {
                            showErrorMessage(errorMsgDistance);
                        }
                        if (!c.setDurationWithString(txtDuration.getText())) {
                            showErrorMessage(errorMsgDuration);
                        }
                        attractionsManager.addAttraction(c);
                        itemNames.setModel(new DefaultComboBoxModel(
                                attractionsManager.getSortedArray()));
                        itemNames.setSelectedItem(c);
                    }
                    btnDelete.setEnabled(true);
                    btnUpdate.setEnabled(true);
                    btnNew.setText("New");
                }
            }
        });
        //delete button event handler
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemNames.getItemCount() > 0) {
                    int answer = JOptionPane.showConfirmDialog(AttractionViewWindow.this,
                            "Are you sure you want to delete this item?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION);
                    if (answer == JOptionPane.YES_OPTION) {
                        attractionsManager.clearAllObjects();
                        itemNames.removeItemAt(itemNames.getSelectedIndex());
                        attractionsManager.deleteAttraction((Attraction) itemNames.getSelectedItem());
                        itemNames.updateUI();
                    }
                }
                if (itemNames.getItemCount() == 0) {
                    
                    btnNew.doClick();
                }
            }
        });
    }

    /**
     * Sets up how the different GUI components should be viewed at startup.
     */
    private void initializeDisplay() {
        if (itemNames.getItemCount() > 0) {
            itemNames.setSelectedIndex(0);

        } else {
            txtAttraction.setEnabled(false);
            txtCity.setEnabled(false);
            txtCountry.setEnabled(false);
            txtDistance.setEnabled(false);
            txtDuration.setEnabled(false);
            btnUpdate.setEnabled(false);
            btnDelete.setEnabled(false);
            btnNew.doClick();
        }
    }
    
    private void UpdateTxtArea(String name, String city, String country, 
            String distance, String duration)
    {
        textArea.append(newline + "Attraction Name: " + name + newline );
        textArea.append("Attraction City: " + city + newline );
        textArea.append("Attraction Country: " + country + newline  );
        textArea.append("Distance from Seattle: " + distance + newline  );
        textArea.append("Duration of stay: " + duration + newline  );
    }
    
}
