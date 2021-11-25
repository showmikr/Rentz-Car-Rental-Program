
/*
 * Car Class: represents car object and inherits members from Vehicle class.
 */

package model.vehicles;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Represents a car object.
 * @author roys4
 * @version Winter 2021
 *
 */
public class Car extends AbstractVehicle {
    
    /**
     * Base car fare.
     */
    private static final BigDecimal CAR_FARE = BASE_FARE.multiply(new BigDecimal("3.00"));
    
    /**
     * Checks if car is a luxury car.
     * If "true", then it is a luxury car.
     */
    private final boolean myLuxuryCondition;
    
    /**
     * Checks if car has navigation included.
     * If "true", then it has navigation.
     */
    private final boolean myNavCondition;
    
    /**
     * Checks if car has drive assistance included.
     * If "true", then drive assistance is included.
     */
    private final boolean myDriveAssistCondition;
    
    /**
     * Constructs a car object and initializes all the core fields in a car.
     * @param theName Vehicle Name
     * @param theVIN Vehicle VIN
     * @param theAvailability Vehicle Availability
     * @param theLuxuryCondition Checks if this is a luxury car
     * @param theNavCondition Checks if the car has navigation
     * @param theDriveAssistCondition Checks if the car has driver assistance
     */
    public Car(final String theName, final String theVIN, final boolean theAvailability, final boolean theLuxuryCondition, final boolean theNavCondition, final boolean theDriveAssistCondition) {
        super(theName, theVIN);
        setMyAvailability(theAvailability);
        myLuxuryCondition = theLuxuryCondition;
        myNavCondition = theNavCondition;
        myDriveAssistCondition = theDriveAssistCondition;
        calculateRentalAmount();
    }
    
    @Override
    /**
     * Calculates the daily fare for the car.
     * @return Car daily fare
     */
    protected final void calculateRentalAmount() {
        
        BigDecimal cost = CAR_FARE;
        
        if (myLuxuryCondition) {
            cost = cost.add(new BigDecimal("10.00"));
        }
        
        if (myNavCondition) {
            cost = cost.add(new BigDecimal("1.00"));
        }
        
        if (myDriveAssistCondition) {
            cost = cost.add(new BigDecimal("2.00"));
        }
        
        setMyRentalAmount(cost);
    }
    
    @Override
    /**
     * Checks if a car is equal to another object.
     */
    public boolean equals(final Object theOtherObject) {
        boolean result = false;
        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else if (this.getClass() == theOtherObject.getClass()) {
            final Car otherCar = (Car) theOtherObject;
            result = getMyVehicleID() == otherCar.getMyVehicleID()
                     && getMyVIN().equals(otherCar.getMyVIN())
                     && getMyName().equals(otherCar.getMyName())
                     && myLuxuryCondition == otherCar.myLuxuryCondition
                     && myNavCondition == otherCar.myNavCondition
                     && myDriveAssistCondition == otherCar.myDriveAssistCondition;
        }
        return result;
    }
    
    @Override
    /**
     * Generates object hash code based on ID, Name, VIN, Luxury Condition, Navigation Assistance, and Drive Assistance
     */
    public int hashCode() {
        return Objects.hash(getMyVehicleID(), getMyName(), getMyVIN(),
                            myLuxuryCondition, myNavCondition, myDriveAssistCondition);
    }
    
    @Override
    /**
     * Generates string to represent core car information.
     * @return text information about car
     */
    public String toString() {
        final StringBuilder sb = toStringStarter();
        final String[] strings = {"IsLuxury?:", Boolean.toString(this.myLuxuryCondition),
                                  ", HasNavigation?:", Boolean.toString(this.myNavCondition),
                                  ", HasAssistance?:", Boolean.toString(myDriveAssistCondition), ")"};
        for (final String item : strings) {
            sb.append(item);
        }
        return sb.toString();
    }
        
}
