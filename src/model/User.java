
/*
 * This file is the User class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.Objects;

/**
 * Represents a single user for registration or sign-in. User is an immutable object.
 * 
 * Constructors and methods of this class throw NullPointerException if required parameters are
 * null.
 * 
 * @author roys4
 * @version Winter 2020
 */
public final class User {
    
    /**User's name.*/
    private String myName;
    /**User's Password.*/
    private String myPassword;
    /**Checks if user is a VIP member.*/
    private boolean myVIPStatus;
    
    /**
     * Constructs a user account for an automotive rental system.
     * @param theName Account username
     * @param thePassword Account password
     */
    public User(final String theName, final String thePassword) {
        //Implicitly check if method arguments are null
        this.myName = Objects.requireNonNull(theName);
        this.myPassword = Objects.requireNonNull(thePassword);
        
        //Explicitly check if method arguments are empty
        if (theName.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Constructs a user account for a vehicle rental software.
     * @param theName Account username
     * @param thePassword Account password
     * @param theVIPStatus Account VIP status
     */
    public User(final String theName, final String thePassword, final boolean theVIPStatus) {
        this(theName, thePassword);
        this.myVIPStatus = Objects.requireNonNull(theVIPStatus);
    }

    /**
     * @return the myName
     */
    public String getMyName() {
        return myName;
    }

    /**
     * @return the myPassword
     */
    public String getMyPassword() {
        return myPassword;
    }

    /**
     * @return the myVIPStatus
     */
    public boolean isVIP() {
        return myVIPStatus;
    }
    
    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final String commaAndSpace = ", ";
        final String[] orderOfStrings = {getClass().getSimpleName(), "(", myName, commaAndSpace, myPassword, commaAndSpace, ((Boolean) myVIPStatus).toString(), ")"};
        //Build String in order of elements in array
        for (int i = 0; i < orderOfStrings.length; i++) {
            sb.append(orderOfStrings[i]);
        }
        return sb.toString();
    }
    
    @Override
    /**
     * Checks equivalence between a given User object and another object
     */
    public boolean equals(final Object theOtherObject) {
        boolean result = false;
        if (this == theOtherObject) {
            result = true;
        } else if (theOtherObject == null) {
            result = false;
        } else {
            final User otherUser = (User) theOtherObject;
            if (myName.equals(otherUser.myName) 
                && myPassword.equals(otherUser.myPassword) 
                && myVIPStatus == otherUser.myVIPStatus) {
                result = true;
            } 
        }
        return result;
    }
    
    @Override
    /**
     * Converts User fields into hash code
     */
    public int hashCode() {
        return Objects.hash(myName, myPassword, myVIPStatus);
    }
    
}
