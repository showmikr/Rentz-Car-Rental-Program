
/*
 * Rental Manager: used to generate and display vehicle inventory.
 */

package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import model.vehicles.AbstractVehicle;
import model.vehicles.Bicycle;
import model.vehicles.Car;
import model.vehicles.MotorBike;

/**
 * Rental Manager generates the vehicle inventory based on a registration object.
 * 
 * @author ShowmikNEW
 * @version Winter 2021
 */
public class RentalManager {
    
    /**
     * A line of asterisks to act as a text border.
     */
    public static final String ASTERISK_LINE = "**********************";
    
    /**
     * Contains list of all vehicles in rental system.
     */
    private final Map<Integer, AbstractVehicle> myVehicleList = new HashMap<Integer, AbstractVehicle>();
    
    /**
     * Contains list of all bills in rental system.
     */
    private final Map<Integer, Bill> myBills;
    
    /**
     * Registration object to be used by Rental Manager.
     */
    private final Registration myRegistration;
    
    /**
     * Scanner for user input.
     */
    private final Scanner myScanner;
    
    /**
     * Initializes myRegistration field with a registration object as input.
     * @param theRegistration Registration file
     */
    public RentalManager(final Registration theRegistration) {
        myRegistration = theRegistration;
        myBills = new HashMap<Integer, Bill>();
        myScanner = myRegistration.getMyScanner();
    }
    
    /**
     * Generates the vehicle inventory for Rentz program.
     */
    public void generateInventory() {
        final String cruiser = "Cruiser";
        final String mountain = "Mountain";
        final AbstractVehicle[] vehicleList =  {new Car("Fiat", "V100", true, false, false, false), new Car("Outback", "V101", true, true, true, false), new Car("BMW", "V102", true, true, true, true),
                                                new MotorBike("Bike1", "B100", true, false), new MotorBike("Bike2", "B101", true, true),
                                                new Bicycle("Roadies", "C100", true, "Road"), new Bicycle(cruiser, "C101", true, cruiser), new Bicycle(mountain, "C102", true, mountain)};
        for (int i = 0; i < vehicleList.length; i++) {
            myVehicleList.put(vehicleList[i].getMyVehicleID(), vehicleList[i]);
        }
    }
    
    /**
     * Displays available vehicle inventory on console.
     */
    public void printOptions() {    
        boolean continueOptions = true;
        
        while (continueOptions) {
            //Ask for which option user wants to take.
            System.out.println("Enter 1 or 2 or 3 (1. Rent 2. Drop-off 3. Exit):");
            final int option = myScanner.nextInt();
            myScanner.nextLine(); //Skip scanner to next line after scanning for integer.
            if (option == 1) {
                rentProtocol();
            }
            if (option == 2) {
                dropOffProtocol();
            }
            //Exit options menu if option 3 is selected.
            final int three = 3;
            if (option == three) {
                System.out.println("You entered option 3");
                System.out.println();
                System.out.println(ASTERISK_LINE + ASTERISK_LINE);
                break;
            }
            //Ask if user wants to re-open options prompt.
            System.out.print("Do you want to continue?");
            continueOptions = myScanner.nextBoolean();
            myScanner.nextLine();
        }
    }
    
    /**
     * Returns hash map of vehicle inventory.
     * @return Vehicle Inventory HashMap
     */
    public Map<Integer, AbstractVehicle> getMyVehicleList() {
        return myVehicleList;
    }
    
    /**
     * Registration object used by Rental Manager to generate vehicle inventory.
     * @return Registration object
     */
    public Registration getMyRegistration() {
        return myRegistration;
    }
    
    /**
     * Checks if vehicle is available for rent.
     * @param theVehicleID ID number of vehicle in question
     * @return boolean that tells if vehicle is available for rent
     */
    public boolean isRentable(final int theVehicleID) {
        return myVehicleList.get(theVehicleID).isAvailable();
    }
    
    /**
     * Rents out a vehicle from myVehicleList.
     * @param theVehicleID Vehicle ID number
     * @param theUserName username 
     * @param theNumDays Number of days vehicle has been rented
     * @param theBillID Bill ID number
     * @return boolean that checks if all parameters are not null nor empty
     */
    public boolean rent(final int theVehicleID, final String theUserName, final int theNumDays, final int theBillID) {
        boolean result = false;
        Objects.requireNonNull(theVehicleID);
        Objects.requireNonNull(theUserName);
        Objects.requireNonNull(theNumDays);
        Objects.requireNonNull(theBillID);
        
        if (theUserName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        if (isRentable(theVehicleID) && myRegistration.getMyUserList().containsKey(theUserName)) {
            final User user = myRegistration.getMyUserList().get(theUserName);
            final AbstractVehicle vehicle = myVehicleList.get(theVehicleID);
            vehicle.setMyAvailability(false);
            final Bill bill = new Bill(theVehicleID, user, vehicle, theNumDays);
            myBills.put(bill.getMyBillID(), bill);
            bill.computeAndPrintAmount();
            result = true;
        }
        return result;
    }
    
    /**
     * Drops off a vehicle and makes it available for rent again.
     * @param theVehicleID Vehicle ID number
     * @return Check as to whether if vehicle has been successfully dropped off.
     */
    public boolean drop(final int theVehicleID) {
        boolean result = false;
        
        if (myVehicleList.containsKey(theVehicleID) && !isRentable(theVehicleID)) {
            result = true;
        }
        
        if (result) {
            myVehicleList.get(theVehicleID).setMyAvailability(true);
        }
        return result;
    }
    
    /**
     * Clears myVehicleList and myBills.
     */
    public void clearLists() {
        myVehicleList.clear();
        myBills.clear();
    }
    
    /**
     * Start rental procedure from option menu.
     */
    private void rentProtocol() {
        System.out.println("You entered option 1");
        //Print out all vehicles in myVehicle List
        System.out.println();
        System.out.println("***************List of Available Vehicles***************");
        for (int i = 1; i <= myVehicleList.size(); i++) {
            if (myVehicleList.get(i).isAvailable()) {
                System.out.println(myVehicleList.get(i));
            }
        }
        //Generate myBillID
        final int billID = myBills.size() + 1;
        //Show "Enter Rental Details"
        System.out.println(ASTERISK_LINE);
        System.out.println("Enter Rental Details");
        System.out.println(ASTERISK_LINE);
        //Ask for Vehicle ID
        System.out.print("Enter Vehicle ID: ");
        final int vehicleID = myScanner.nextInt();
        myScanner.nextLine();
        //Ask for Username
        System.out.print("Enter User Name: ");
        final String username = myScanner.nextLine();
        //Ask for number of days to rent vehicle
        System.out.print("Enter NumDays to Rent: ");
        final int numDays = myScanner.nextInt();
        myScanner.nextLine();
        System.out.println();
        //Rent out vehicle
        rent(vehicleID, username, numDays, billID);
    }
    
    /**
     * Start drop off procedure from option menu.
     */
    private void dropOffProtocol() {
        System.out.println("You entered option 2");
        System.out.println();
        System.out.println(ASTERISK_LINE + ASTERISK_LINE);
        System.out.println(ASTERISK_LINE);
        System.out.println("Enter Drop-off Details");
        System.out.println(ASTERISK_LINE);
        int vehicleID = -1;
        while (!drop(vehicleID)) {
            System.out.print("Enter Drop-off Vehicle ID: ");
            vehicleID = myScanner.nextInt();
            myScanner.nextLine();
            if (myVehicleList.containsKey(vehicleID)) {
                if (myVehicleList.get(vehicleID).isAvailable()) {
                    System.out.println("Vehicle is not rented already");
                }
            } else {
                System.out.println("Vehicle does not exist");
            }
        }
        System.out.println("Drop-off Successfull");
        System.out.println(ASTERISK_LINE);
    }
}
