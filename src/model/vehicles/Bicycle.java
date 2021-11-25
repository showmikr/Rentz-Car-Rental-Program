
/*
 * Bicycle Class: represents a bicycle object.
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a bicycle which is an extension of the Vehicle class.
 * @author roys4
 * @version Winter 2021
 */
public class Bicycle extends AbstractVehicle {
    
    /**
     * Daily base cost of renting a bicycle.
     */
    protected static final BigDecimal CYCLE_FARE = BASE_FARE;
    
    /**
     * Determine's what type of Bicycle we are using.
     */
    private final String myType;
    
    /**
     * Initializes key properties of bicycle object.
     * @param theName Bicycle Name
     * @param theVIN Bicycle VIN
     * @param theAvailability Bicycle Availability
     * @param theType Bicycle Type
     */
    public Bicycle(final String theName, final String theVIN, final boolean theAvailability, final String theType) {
        super(theName, theVIN);
        setMyAvailability(theAvailability);
        myType = theType;
        calculateRentalAmount();
    }
    
    @Override
    /**
     * Calculates the daily fare for the bicycle.
     * @return Car daily fare
     */
    protected final void calculateRentalAmount() {
        final String[] types = {"Mountain", "Cruiser", "Hybrid"};
        BigDecimal cost = CYCLE_FARE;
        
        if (myType.equals(types[0])) {
            cost = cost.multiply(new BigDecimal("1.01"));
        } else if (myType.equals(types[1])) {
            cost = cost.multiply(new BigDecimal("1.02"));
        } else if (myType.equals(types[2])) {
            cost = cost.multiply(new BigDecimal("1.04"));
        }
        setMyRentalAmount(cost);
    }
    
    @Override
    /**
     * Checks if a bicycle is equal to another object.
     */
    public boolean equals(final Object theOtherObject) {
        boolean result = false;
        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else if (this.getClass() == theOtherObject.getClass()) {
            final Bicycle otherBicycle = (Bicycle) theOtherObject;
            result = getMyVehicleID() == otherBicycle.getMyVehicleID()
                     && getMyVIN().equals(otherBicycle.getMyVIN())
                     && getMyName().equals(otherBicycle.getMyName())
                     && myType.equals(otherBicycle.myType);
        }
        return result;
    }
    
    @Override
    /**
     * Generates object hash code based on ID, Name, VIN, and Cycle Type.
     */
    public int hashCode() {
        return Objects.hash(getMyVehicleID(), getMyName(), getMyVIN(), myType);
    }
    
    @Override
    /**
     * Generates string to represent core bicycle information.
     * @return text information about bicycle
     */
    public String toString() {
        final StringBuilder sb = toStringStarter();
        final String[] string = {"CycleType:", myType, ")"};
        for (final String item : string) {
            sb.append(item);
        }
        return sb.toString();
    }

}
