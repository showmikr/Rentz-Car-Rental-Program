
/*
 * Generic Vehicle Object
 */

package model.vehicles;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Vehicle Class.
 * Class to represent core information for all vehicles in rental system.
 * 
 * @author roys4
 * @version Winter 2021
 *
 */
public abstract class AbstractVehicle {
    
    /**
     * The daily base fare to rent a vehicle.
     */
    protected static final BigDecimal BASE_FARE = new BigDecimal("10.00");
    
    /**
     * Vehicle instance counter.
     */
    private static int myIDCounter;
    
    /**
     * The daily cost of renting a vehicle.
     */
    private BigDecimal myRentalAmount = BASE_FARE;
    
    /**
     * Vehicle ID Number.
     */
    private final int myVehicleID;
    
    /**
     * Unique Vehicle Identification Number.
     */
    private final String myVIN;
    
    /**
     * Vehicle's Name.
     */
    private final String myName;
    
    /**
     * Determines if vehicle is available for rent or not.
     */
    private boolean myAvailability;
    
    /**
     * Constructor to initialize core vehicle properties.
     * @param theVIN Vehicle's Identification Number
     * @param theName Vehicle's name
     */
    public AbstractVehicle(final String theName, final String theVIN) {
        myIDCounter++;
        myVehicleID = myIDCounter;
        this.myName = theName;
        this.myVIN = theVIN;
    }
    
    /**
     * The Vehicle ID.
     * @return Vehicle ID
     */
    public int getMyVehicleID() {
        return myVehicleID;
    }
    
    /**
     * The Vehicle VIN.
     * @return VIN
     */
    public String getMyVIN() {
        return myVIN;
    }
    
    /**
     * The Vehicle Name.
     * @return Vehicle Name
     */
    public String getMyName() {
        return myName;
    }
    
    /**
     * The Vehicle Fare.
     * @return Vehicle Daily Fare.
     */
    public BigDecimal getMyRentalAmount() {
        return myRentalAmount;
    }
    
    /**
     * Gives string representation of myRentalAmount in dollars.
     * @return Daily rental cost of vehicle in dollars.
     */
    public String getMyRentalPrice() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(myRentalAmount);
    }
    
    /**
     * Adjusts the vehicle fare.
     * @param theNewAmount The new vehicle fare amount.
     */
    protected void setMyRentalAmount(final BigDecimal theNewAmount) {
        myRentalAmount = theNewAmount;
    }
    
    /**
     * Checks if vehicle is available for rent.
     * @return Vehicle Availability
     */
    public boolean isAvailable() {
        return myAvailability;
    }
    
    /**
     * Sets the availability of the vehicle.
     * @param theAvailability Vehicle Availability
     */
    public void setMyAvailability(final boolean theAvailability) {
        myAvailability = theAvailability;
    }
    
    /**
     * Builds a string containing the common properties of all vehicles
     * including name, ID, VIN, and availability.
     * @return Starting string of each vehicle toString() method.
     */
    protected StringBuilder toStringStarter() {
        final String[] strings = {"(ID:", String.valueOf(this.getMyVehicleID()), ", Name:", this.getMyName(),
                                  ", VIN:", this.getMyVIN(), ", CanRent:", Boolean.toString(this.isAvailable()), ", "};       
        final StringBuilder s = new StringBuilder();
        for (final String item : strings) {
            s.append(item);
        }
        return s;
    }
    
    /**
     * Check for equivalence between a vehicle and another object.
     * @param theOtherObject Object of comparison
     * @return equivalence condition between vehicle and other object
     */
    public abstract boolean equals(Object theOtherObject);
    
    /**
     * Generate vehicle hash code.
     * @return Vehicle hash code
     */
    public abstract int hashCode();
    
    
    /**
     * Overrides toString method for vehicle objects.
     * @return String representation of vehicle object
     */
    public abstract String toString();
    
    /**
     * Calculates the daily fare for a vehicle object.
     */
    protected abstract void calculateRentalAmount();
}
