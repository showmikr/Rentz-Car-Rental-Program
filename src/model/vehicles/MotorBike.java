
/*
 * Motor Bike Class: represents a motor-bike object.
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Motor Bike Object.
 * @author roys4
 * @version Winter 2021
 */
public class MotorBike extends AbstractVehicle {
    
    /**
     * Daily base motor bike fare.
     */
    private static final BigDecimal BIKE_FARE = BASE_FARE.multiply(new BigDecimal("2.00"));
    
    /**
     * Checks if motor bike is a touring type or not.
     * If set to "true" then it is a touring bike.
     */
    private final boolean myTouringCondition;
    
    /**
     * Initializes all core fields of motor bike.
     * @param theName Motor bike name
     * @param theVIN Motor bike VIN
     * @param theAvailability Motor Bike Availability
     * @param theTouringCondition Checks if motor bike is a touring bike
     */
    public MotorBike(final String theName, final String theVIN, final boolean theAvailability, final boolean theTouringCondition) {
        super(theName, theVIN);
        setMyAvailability(theAvailability);
        myTouringCondition = theTouringCondition;
        calculateRentalAmount();
    }
    
    @Override
    /**
     * Calculates the daily fare for the motor bike.
     * @return Car daily fare
     */
    public final void calculateRentalAmount() {
        BigDecimal cost = BIKE_FARE;   
        if (myTouringCondition) {
            cost = cost.add(new BigDecimal("5.00"));
        }
        setMyRentalAmount(cost);
    }
    
    @Override
    /**
     * Checks if a Motor Bike is equal to another object.
     */
    public boolean equals(final Object theOtherObject) {
        boolean result = false;
        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else if (this.getClass() == theOtherObject.getClass()) {
            final MotorBike otherMotorBike = (MotorBike) theOtherObject;
            result = getMyVehicleID() == otherMotorBike.getMyVehicleID()
                     && getMyVIN().equals(otherMotorBike.getMyVIN())
                     && getMyName().equals(otherMotorBike.getMyName())
                     && myTouringCondition == otherMotorBike.myTouringCondition;
        }
        return result;
    }
    
    @Override
    /**
     * Generates object hash code based on ID, Name, VIN, and Cycle Type.
     */
    public int hashCode() {
        return Objects.hash(getMyVehicleID(), getMyName(), getMyVIN(), myTouringCondition);
    }
    
    @Override
    /**
     * Generates string to represent core motor bike information.
     * @return text information about motor bike
     */
    public String toString() {
        final StringBuilder sb = toStringStarter();
        final String[] strings = {"isTouring?:", Boolean.toString(this.myTouringCondition), ")"};
        for (final String item : strings) {
            sb.append(item);
        }
        return sb.toString();
    }

}
