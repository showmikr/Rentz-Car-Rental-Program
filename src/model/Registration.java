
/*
 * This file is the registration class for the Vehicle Rental System.
 * 
 * TCSS 305 - Rentz
 */

package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import utility.FileLoader;

/**
 * Represents User Sign-in Object.
 * 
 * Methods of this class throw NullPointerException if required parameters are null.
 * 
 * @author roys4
 * @version Winter 2020
 */

public class Registration {

    /**
     * User Storage File.
     */
    public static final String USERFILE_NAME = "./resources/registeredusers.txt";
    
    /**
     * List of special ascii characters (i.e !, @, #, $, %, ^ etc).
     * Used for checking if new user-entered password contains any special characters.
     * The only reason this is stored as a field is so that we don't have to
     * constantly generate a new special character list every time we check
     * for special characters in the password.
     */
    private static final List<Character> SPECIAL_CHARACTERS = asciiSpecialCharacters();
    
    /**
     * Prompt to ask for user password.
     */
    private static final String ASK_PASSWORD = "Password: ";

    /**
     * String used to jump down 2 lines when printing to console.
     */
    private static final String SKIP_2_LINES = "\n\n";
    
    /**
     * Scanner for user-input.
     */
    private final Scanner myScanner = new Scanner(System.in);
    
    /**
     * The registered user list for sign-in.
     */
    private final Map<String, User> myUserList;

    /**
     * Constructs a sign-in/registration system.
     * 
     * 
     */
    public Registration() {
        myUserList = FileLoader.readItemsFromFile(USERFILE_NAME);
    }

    /**
     * getter for myUserList.
     * 
     * @return myUserList
     */
    public Map<String, User> getMyUserList() {
        return myUserList;
    }
    
    /**
     * Make scanner usable by other classes.
     * @return Scanner for user input
     */
    public Scanner getMyScanner() {
        return myScanner;
    }

    /**
     * display sign-in or registration options.
     * @return sign-in success condition.
     */
    public boolean printSignin() {

        // ------------Fill in--------------------//
        final String asteriskLine = "**********************";
        boolean result = false;

        // Display sign-in options menu
        System.out.print("Enter 1 or 2 (1. New Registration 2. Login): ");
        final int option = myScanner.nextInt(); // Integer storing which sign-in option is selected
        myScanner.nextLine(); // makes scanner skip an empty line. Absolutely necessary for program to properly read username.
        System.out.print("You entered option " + option + SKIP_2_LINES);

        // Display "Enter Details" Prompt
        System.out.println(asteriskLine);
        System.out.println("Enter Details");
        System.out.println(asteriskLine);

        // Get username
        System.out.print("User Name: ");
        String username = Objects.requireNonNull(myScanner.nextLine());

        // Check for which option user entered and sign in accordingly
        if (option == 1) {    
            newUserRegistration(username);
            System.out.println("Registration Successful");
            result = true;
        } else {
            String password = setPassword();
            // Repeat asking for user/pass if entered username doesn't exist or if it does exits and password is wrong
            while (!myUserList.containsKey(username) || !myUserList.get(username).getMyPassword().equals(password)) {
                // "Enter Username and Password" Prompt + variables to store said username and password
                System.out.println("\n" + "Wrong Credentials");
                System.out.print("Enter User Name: ");
                username = myScanner.nextLine();
                System.out.print("Enter Password: ");
                password = myScanner.nextLine();
            }
            login(username, password);
            System.out.println("Login Successful");
            result = true;
        }
        return result;
    }

    /**
     * Verify Sign-in procedure.
     * 
     * @param theUsername username for sign-in
     * @param thePassword password for sign-in
     * @return sign-in success
     */
    public boolean login(final String theUsername, final String thePassword) {

        // ------------Fill in--------------------//
        // Implicitly check if either method arguments are null
        Objects.requireNonNull(theUsername);
        Objects.requireNonNull(thePassword);

        // Explicitly check for empty strings in which case, throw IllegalArgumentException
        if (theUsername.isEmpty() || thePassword.isEmpty()) {
            throw new IllegalArgumentException();  
        }
        return true;
    }

    /**
     * Adds a user to the registered user list.
     * 
     * @param theUser an order to add to this shopping cart
     * @return true/false returns if registration is successful
     */
    public boolean register(final User theUser) {

        // ------------Fill in--------------------//
        // Implicitly check if method arguments are null
        Objects.requireNonNull(USERFILE_NAME);
        Objects.requireNonNull(theUser);

        // Write to file
        FileLoader.writeUserToFile(USERFILE_NAME, theUser);
        myUserList.put(theUser.getMyName(), theUser);
        return true;

    }

    /**
     * Empties the user list.
     */
    public void clear() {

        // ------------Fill in--------------------//
        myUserList.clear();
    }

    @Override
    /**
     * String representation of the object
     * 
     */
    public String toString() {

        // ------------Fill in--------------------//
        final StringBuilder sb = new StringBuilder();
        sb.append("Registered UserList {");

        final Set<String> keys = myUserList.keySet();
        final Iterator<String> itr = keys.iterator();
        final String commaPlusSpace = ", ";

        while (itr.hasNext()) {
            final String key = itr.next();
            sb.append(key);
            sb.append(" = ");
            sb.append(myUserList.get(key));
            sb.append(commaPlusSpace);
        }
        sb.append('}');
        sb.delete(sb.lastIndexOf(commaPlusSpace), sb.indexOf("}"));

        return sb.toString();
    }
    
    /**
     * Registration protocol to enter new user into registered users.
     * Asks for user to create a username and password while ensuring
     * the username is not a duplicate of previously registered users
     * and that the password is strong enough.
     * 
     * @param theUsername the new user's username
     */
    private void newUserRegistration(final String theUsername) {
        
        String username = theUsername;
        //ensure username entered doesn't already exist in myUserList
        while (myUserList.containsKey(username)) {
            System.out.print("User already exists, enter different user name: ");
            username = myScanner.nextLine();
        }

        String password = setPassword();
        
        //Check if password is not strong enough
        if (!hasStrongPassword(password)) {             
            displayWeakPasswordMessage();     //If not, this method prints "password doesn't comply" and prints the requirements.
            password = redoUserPassword();    //Prompts user to re-enter password as many times as needed
        }
        
        // Get VIP Status
        System.out.print("isVIP(true/false): ");
        final boolean isVIP = myScanner.nextBoolean();
        
        register(new User(username, password, isVIP));
    }
    
    /**
     * Prompts the user to enter their password.
     * @return user-entered password
     */
    private String setPassword() {
        System.out.print(ASK_PASSWORD);
        return Objects.requireNonNull(myScanner.nextLine());
    }

    /**
     * Checks if the user entered password is strong enough.
     * 
     * @param thePassword User password
     * @return Tells us if the user entered password is strong or weak
     */
    private static boolean hasStrongPassword(final String thePassword) {
        final int minCharacters = 10;
        final int maxCharacters = 40;

        return thePassword.length() >= minCharacters         // Password must have minimum of 10 characters
               && thePassword.length() <= maxCharacters      // Password cannot exceed 40 characters
               && !thePassword.contains(" ")                 // Password cannot contain empty spaces 
               && areThereSpecialCharacters(thePassword)     // Password contains at least one special character
               && areThereCapitalLetters(thePassword)        // Password has at least one capital letter
               && areThereNumbers(thePassword);              // Password has at least one numerical digit
    }
    
    /**
     * Console message indicating that user-entered password is too weak.
     * Lists all password requirements.
     */
    private static void displayWeakPasswordMessage() {
        System.out.println("Password does not comply.");
        System.out.println("Please note, passwords need to meet all conditions below:");
        System.out.println("- Have a minimum of 10 characters");
        System.out.println("- Cannot exceed 40 characters");
        System.out.println("- Cannot contain empty spaces");
        System.out.println("- Have at least one special character (i.e !, @, #, $, etc)");
        System.out.println("- Have at least one capital letter");
        System.out.println("- Have at least one number");
    }
    
    /**
     * Utility method to have user re-enter password until it meets all
     * password requirements.
     * @return new password
     */
    private String redoUserPassword() {
        
        System.out.print("\nPlease re-enter password: ");
        String password = myScanner.nextLine();
        
        while (!hasStrongPassword(password)) {
            System.out.print("\nPassword does not comply. Please re-enter: ");
            password = myScanner.nextLine();
        }
        System.out.print("Password Complies." + SKIP_2_LINES);
        return password;
    }

    /**
     * Utility method to determine if a User's password contains a numerical digit.
     * 
     * @param thePassword User password
     * @return truth value as to whether a number character is in the User's password
     */
    private static boolean areThereNumbers(final String thePassword) {
        boolean result = true;
        final int start = 48;
        final int end = 57;
        final List<Character> numbers = asciiCharacters(start, end);
        
        //check each character in password for numbers
        for (int i = 0; i < thePassword.length(); i++) {
            if (numbers.contains(thePassword.charAt(i))) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Utility method to determine if there is a capital letter in the User account's password.
     * 
     * @param thePassword User password
     * @return boolean that determines if the password contains a capital letter.
     */
    private static boolean areThereCapitalLetters(final String thePassword) {
        boolean result = false;
        final int start = 65;
        final int end = 90;
        final List<Character> capitalLetters = asciiCharacters(start, end);
        
        //Check each character in password for capital letters
        for (int i = 0; i < thePassword.length(); i++) {
            if (capitalLetters.contains(thePassword.charAt(i))) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Utility method to determine if the password on a User account has special characters.
     * Works in tandem with asciiSpecialCharacters() method.
     * 
     * @param thePassword User password
     * @return boolean that determines if the password on the User account has special
     *         characters
     */
    private static boolean areThereSpecialCharacters(final String thePassword) {
        boolean result = false;
        //Check each character in password for special characters
        for (int i = 0; i < thePassword.length(); i++) {
            if (SPECIAL_CHARACTERS.contains(thePassword.charAt(i))) {
                result = true;
            }
        }
        
        return result;
    }

    /**
     * Utility method that generates a list of all special ascii characters.
     * 
     * @return List of all special ascii characters
     */
    private static List<Character> asciiSpecialCharacters() {
        //List to store special characters
        final List<Character> charList = new ArrayList<Character>();
        
        //All the "bounds" are boundary values of the special ascii characters.
        //The range between each pair of bounds contain the special characters.
        final int bound1Start = 33;
        final int bound1End = 47;
        
        final int bound2Start = 58;
        final int bound2End = 64;
        
        final int bound3Start = 91;
        final int bound3End = 96;
        
        final int bound4Start = 123;
        final int bound4End = 127;
        
        //Array for holding bounds of special ascii characters - used in for-loop below
        final int[] asciiCharBounds = {bound1Start, bound1End, bound2Start, bound2End, bound3Start, bound3End, bound4Start, bound4End};
        
        //for-loop cycles through the ascii special character bounds
        for (int i = 0; i < asciiCharBounds.length; i += 2) {
            charList.addAll(asciiCharacters(asciiCharBounds[i], asciiCharBounds[i + 1])); //adds ascii characters within range of each pair of bounds to charList
        }
        return charList;
    }
    
    /**
     * Utility method used to generate a list of ascii characters within a given range.
     * @param theStart starting point of ascii characters to add into character list
     * @param theEnd ending point of ascii characters to add into character list
     * @return list of a given range of ascii characters
     */
    private static List<Character> asciiCharacters(final int theStart, final int theEnd) {
        final List<Character> charList = new ArrayList<Character>();
        for (int i = theStart; i <= theEnd; i++) {
            charList.add((char) i);
        }
        return charList;
    }

}
