/**
 * This file tests all public methods and constructors in User class.
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Objects;
import model.User;
import org.junit.Before;
import org.junit.Test;

/**
 * @author ShowmikNEW
 * @version Winter 2020
 */
public class UserTest {
    
    /**
     * Class object to be tested.
     */
    private User myUser1;
    
    /**
     * Class object to be tested.
     */
    private User myUser2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        myUser1 = new User("Bob", "CoolCats22");
        myUser2 = new User("Bob", "CoolCats22", true);
    }

    /**
     * Test method for {@link model.User#hashCode()}.
     */
    @Test
    public void testHashCode() {
        final User user3 = new User("Bob", "CoolCats22", true);
        assertEquals("hashChode() failed", Objects.hashCode(user3), myUser2.hashCode());
        assertEquals("hashCode failed", Objects.hash(myUser2.getMyName(), myUser2.getMyPassword(), myUser2.isVIP()), myUser2.hashCode());
    }

    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testUserStringString() {
        assertNotNull("Username is null", myUser1.getMyName());
        assertNotNull("Password is null", myUser1.getMyPassword());
    }
    
    /**
     * Make sure User constructor throws null pointer exception when passed a null username argument.
     */
    @Test(expected = NullPointerException.class)
    public void testUserNullNameArgument() {
        myUser1 = new User(null, "CoolCats22");
    }
    
    /**
     * Make sure User constructor throws null pointer exception when passed a null password argument.
     */
    @Test(expected = NullPointerException.class)
    public void testUserNullPasswordArgument() {
        myUser1 = new User("Bob", null);
    }
    
    /**
     * Make sure User constructor throws illegal argument exception when passed an empty username argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalNameArgument() {
        myUser1  = new User("", "CoolCats22");
    }
    
    /**
     * Make sure User constructor throws illegal argument exception when passed an empty password argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testUserIllegalPasswordArgument() {
        myUser1 = new User("Bob", "");
    }

    /**
     * Test method for {@link model.User#User(java.lang.String, java.lang.String, boolean)}.
     */
    @Test
    public void testUserStringStringBoolean() {
        assertNotNull("VIP Status is null", myUser2.isVIP());
        assertEquals("Incorrect VIP Status returned", true, myUser2.isVIP());
    }

    /**
     * Test method for {@link model.User#getMyName()}.
     */
    @Test
    public void testGetMyName() {
        assertEquals("Not returning correct username", "Bob", myUser1.getMyName());
    }

    /**
     * Test method for {@link model.User#getMyPassword()}.
     */
    @Test
    public void testGetMyPassword() {
        assertEquals("Not returning correct password", "CoolCats22", myUser1.getMyPassword());
    }

    /**
     * Test method for {@link model.User#getMyVIPStatus()}.
     */
    @Test
    public void testGetMyVIPStatus() {
        assertEquals("Not returning correct VIP Status", true, myUser2.isVIP());
    }

    /**
     * Test method for {@link model.User#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Not returning correct String representation", "User(Bob, CoolCats22, true)", myUser2.toString());
    }

    /**
     * Test method for {@link model.User#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        assertEquals("equals() method faild", myUser2, myUser2);
        
        final User user3 = new User("Bob", "CoolCats22", true);
        assertEquals("equals() method failed", myUser2, user3);
    }
    
    /**
     * Test to make sure an object of a different type can't be cast to a User object
     */
    @Test(expected = ClassCastException.class)
    public void testIllegalClassCastArgument() {
        assertNotEquals("equals() method failed", myUser2, new ArrayList<String>());
    }
    
    /**
     * Test all possible User argument combinations for equals method where we expect users to not be equal.
     */
    @Test
    public void testEqualsObjectNegative() {
        assertNotEquals("equals() method failed", myUser2, null);
        
        User user3 = new User("Jerry", "DunkinDonuts83", false);
        assertNotEquals("equals() method failed", myUser2, user3);
        
        user3 = new User("Bob", "CoolCats22", false);
        assertNotEquals("equals() method failed", myUser2, user3);
        
        user3 = new User("Jerry", "CoolCats22", true);
        assertNotEquals("equals() method failed", myUser2, user3);
        
        user3 = new User("Bob", "Doh82", true);
        assertNotEquals("equals() method failed", myUser2, user3);
    }

}
