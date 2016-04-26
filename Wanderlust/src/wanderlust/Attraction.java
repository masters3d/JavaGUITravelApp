package wanderlust;

import java.io.Serializable;
import java.util.Objects;

/**
 * Single-item representing location and name of attraction
 *
 * @author J JIMENEZ
 */
public class Attraction implements Comparable, Serializable {

    /**
     *
     */
    public Attraction() {
        this.name = "Downtown";
        this.city = "Seattle";
        this.country = "U.S.A";
        this.distanceFromHome = 0;
        this.durationOfStay = 0;
        this.key = null;
        this.nextByCity = null;
        this.nextByName = null;
    }

    /**
     * Internal fields .
     */
    private String name;
    private String city;
    private String country;
    private double distanceFromHome;
    private double durationOfStay;
    
    /**
     * This is called value in other linked list implementations
     */
    public Attraction key; 

    /**
     * Node based on city sorting
     */
    public Attraction nextByCity;

    /**
     * Node based on name sorting
     */
    public Attraction nextByName;

    

   

    /**
     *  Custom Sorting for Linked list
     * @return
     */
        public String getCityAndAttractionName(){
        return city + name;
    }
    
    /**
     * returns the name of the attraction
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the attraction
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * used by the UI to check that the name is not too long
     * @param value
     * @return Boolean
     */
    public Boolean setAttractionName(String value) {
        // Limiting the length of the name to 30 characters
        if (value.length() < 31) {
            name = value;
            return true;
        } else {
            return false;
        }
    }

    /**
     * returns the city name
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city name and checks that the name is not too long
     * @param value
     * @return Boolean
     */
    public Boolean setCityLimitLength(String value) {
        if (value.length() < 31) {
            city = value;
            return true;
        } else {
            return false;
        }
    }

    /**
     * set the city name string
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * returns the name of the country
     * @return String
     */
    public String getCountry() {
        return country;
    }

    /**
     * sets the name of the country String
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Checks that the name of the country is not too long
     * @param value
     * @return
     */
    public Boolean setCountryLimitLength(String value) {
        if (value.length() < 31) {
            country = value;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Sets the distance from Seattle to destination
     * @param distanceFromHome
     */
    public void setDistanceFromHome(double distanceFromHome) {
        this.distanceFromHome = distanceFromHome;
    }

    /**
     * Sets the duration of stay in Days
     * @param durationOfStay
     */
    public void setDurationOfStay(double durationOfStay) {
        this.durationOfStay = durationOfStay;
    }
    /**
     * Helper method that helps to convert the string to doubles and caching the
     * exception by return a default value.
     *
     * @param input the string value being converted to double
     * @return double returns the value converted or Zero.
     */
    private Double stringToDouble(String input) {
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /** 
     * gets duration of stay in day
     * @return
     */
    public double getDurationOfStay() {
        return durationOfStay;
    }

    /**
     * gets distance from home
     * @return
     */
    public double getDistanceFromHome() {
        return distanceFromHome;
    }

    /**
     * sets duration and makes sure that the String entered is a number
     * @param value
     * @return
     */
    public Boolean setDurationWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {

            return false;
        } else {
            setDurationOfStay(toUpdate);
            return true;
        }
    }

    /**
     * sets Distance and makes sure that the String entered is a number
     * @param value
     * @return
     */
    public Boolean setDistanceWithString(String value) {
        Double toUpdate = stringToDouble(value);
        if (toUpdate == 0.0) {
            return false;
        } else {
            setDistanceFromHome(toUpdate);
            return true;
        }
    }

    // this is the defaul way to sort a list of this type of object
    @Override
    public int compareTo(Object obj) {
        if (obj != null && obj instanceof Attraction) {
            Attraction other = (Attraction) obj;
            return name.compareTo(other.name);
        }
        return 1;
    }

// this is what gets printed at the top of the list on the left of UI
    @Override
    public String toString() {
        return name + " in " + city;
    }

// This compared each object for equality
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Attraction other = (Attraction) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        
        return true;
    }
    // this makes sure that they object can be serilized
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.name);
        hash = 31 * hash + Objects.hashCode(this.distanceFromHome);
        hash = 31 * hash + Objects.hashCode(this.durationOfStay);
        hash = 31 * hash + Objects.hashCode(this.city);
        hash = 31 * hash + Objects.hashCode(this.country);

        return hash;

    }

}
