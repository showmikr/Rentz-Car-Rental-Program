
/*
 * Main class for the Vehicle Rental System TCSS 305
 * 
 * TCSS 305 - Rentz
 */

package model;

/**
 * RentalMain provides the main method for a simple VehicleRental application.
 * 
 * @author roys4
 * @version Winter 2021
 */
public final class RentalMain {

    /**
     * A private constructor, to prevent external instantiation.
     */
    private RentalMain() {

    }

    /**
     * Main method for Rentz.
     * 
     * @param theArgs argument for main method.
     */
    public static void main(final String[] theArgs) {
        final Registration reg = new Registration();
        if (reg.printSignin()) {
            final RentalManager rentalManager = new RentalManager(reg);
            rentalManager.generateInventory();
            rentalManager.printOptions();
            reg.getMyScanner().close();
        }
        
    }

}
