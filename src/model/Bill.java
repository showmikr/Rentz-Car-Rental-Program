
/*
 * Bill Class: Used to generate customer Bills.
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import model.vehicles.AbstractVehicle;

public class Bill {
    
    /**A unique Bill ID. */
    private final int myBillID;
    
    /**User for whom bill is being generated for. */
    private final User myPrimaryUser;
    
    /**Vehicle which bill is associated with. */
    private final AbstractVehicle myVehicle;
    
    /**Number of days vehicle is rented. */
    private final int myNumDays;
    
    /**Total rental cost for rented vehicle. */
    private BigDecimal myBillAmount;
    
    /**
     * Generates a Bill with all relevant user information to calculate total rental cost.
     * @param theBillID Bill ID number
     * @param thePrimaryUser User who is renting a car
     * @param theVehicle Vehicle user is renting
     * @param theNumDays Number of days user renting vehicle
     */
    public Bill(final int theBillID, final User thePrimaryUser,
                final AbstractVehicle theVehicle, final int theNumDays) {
        myBillID = theBillID;
        myPrimaryUser = thePrimaryUser;
        myVehicle = theVehicle;
        myNumDays = theNumDays;
        myBillAmount = BigDecimal.ZERO;
    }
    
    /**
     * Computes the total cost renting out a vehicle and then displays it on console.
     */
    public void computeAndPrintAmount() {
        //Print Header
        final String asteriskLine = "**********************";
        System.out.println(asteriskLine);
        System.out.println("Rental Bill Summary");
        System.out.println(asteriskLine);
        
        //Print vehicle info
        final String[] vehicleInfo = {"User Name: " + myPrimaryUser.getMyName(), "----Vehicle Information---",
                                      "VehicleName " + myVehicle.getMyName(), "vehicleID " + myVehicle.getMyVehicleID(),
                                      "VehicleType " + myVehicle.getMyVIN(), "VIN " + myVehicle.getMyVIN()};
        for (final String item : vehicleInfo) {
            System.out.println(item);
        }
        
        //Calculate insurance cost, VIP discount, and total Bill amount
        BigDecimal rentalAmount = myVehicle.getMyRentalAmount().multiply(new BigDecimal(myNumDays));
        final BigDecimal insurance = rentalAmount.multiply(new BigDecimal("0.01"));
        rentalAmount = rentalAmount.add(insurance);
        BigDecimal vipDiscount = BigDecimal.ZERO;
        if (myPrimaryUser.isVIP()) {
            vipDiscount = insurance;
            rentalAmount = rentalAmount.subtract(vipDiscount);
        }
        final BigDecimal tax = rentalAmount.multiply(new BigDecimal("0.10"));
        rentalAmount = rentalAmount.add(tax);
        myBillAmount = rentalAmount;
        
        //Print Cost Information
        final String[] costInfo = {"----Cost Information----", "RentalPerDay:", "Cost per Day: " + myVehicle.getMyRentalPrice(),
                                   "No.of Rental days: " + myNumDays, "Total Amount: " + dollarFormat(myBillAmount.subtract(tax)),
                                   "Insurance: " + dollarFormat(insurance), "VIPDiscount: -" + dollarFormat(vipDiscount),
                                   "Tax: " + dollarFormat(tax), "Total Rent: " + dollarFormat(myBillAmount)};
        for (final String item : costInfo) {
            System.out.println(item);
        }
        
        //Print last line of Bill
        System.out.println(asteriskLine);
    }
    
    /**
     * Gives string representation of BigDecimal in dollars.
     * @param theCost BigDecimal object to be converted to dollars.
     * @return Daily rental cost of vehicle in dollars.
     */
    public String dollarFormat(final BigDecimal theCost) {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        return nf.format(theCost);
    }
    
    /**
     * Show Bill ID.
     * @return ID number of Bill
     */
    public int getMyBillID() {
        return myBillID;
    }
    
    /**
     * Get total rental cost.
     * @return Total cost of vehicle rental.
     */
    public BigDecimal getMyBillAmount() {
        return myBillAmount;
    }

}
