/**
 * This file tests all public methods (except prtinSignIn) and constructors of the Registration class.
 */

package tests;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import model.Registration;
import model.User;
import org.junit.Before;
import org.junit.Test;
import utility.FileLoader;

/**
 * @author ShowmikNEW
 * @version Winter 2020
 */
public class RegistrationTest {
    /**
     * Class object to be tested.
     */
    private Registration myRegister;
    
    /**
     * Expected list users in "registeredusers.txt" file.
     */
    private final Map<String, User> myUserTestList = FileLoader.readItemsFromFile(Registration.USERFILE_NAME);

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        myRegister = new Registration();
    }

    /**
     * Test method for {@link model.Registration#Registration()}.
     */
    @Test
    public void testRegistration() {
        assertEquals("testRegistration failed", myUserTestList, myRegister.getMyUserList());
    }

    /**
     * Test method for {@link model.Registration#getMyUserList()}.
     */
    @Test
    public void testGetMyUserList() {
        assertEquals("getMyUserList fails", myUserTestList, myRegister.getMyUserList());
    }

    /**
     * Test method for {@link model.Registration#login(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testLogin() {
        assertEquals("testLogin failed", true, myRegister.login("Jack", "Kangaroo32"));
    }
    
    /**
     * Make sure testLogin method throws null exception for null username argument. 
     */
    @Test(expected = NullPointerException.class)
    public void testLoginNullUsernameArgument() {
        myRegister.login(null, "Kangaroo32");
    }
    
    /**
     * Make sure testLogin method throws null exception for null password argument.
     */
    @Test(expected = NullPointerException.class)
    public void testLoginNullPasswordArgument() {
        myRegister.login("Jack", null);
    }
    
    /**
     * Make sure testLogin method throws illegal argument exception for empty username argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginIllegalUsernameArgument() {
        myRegister.login("", "Kangaroo32");
    }
    
    /**
     * Make sure testLogin method throws illegal argument exception for empty password argument.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testLoginIllegalPasswordArgument() {
        myRegister.login("Jack", "");
    }

    /**
     * Test method for {@link model.Registration#register(model.User)}.
     */
    @Test
    public void testRegister() {
        myRegister.register(new User("Boris", "BanktheTank22", false));
        assertEquals("registser failed", true, myUserTestList.containsKey("Boris"));
        assertEquals("register failed", "BanktheTank22", myUserTestList.get("Boris").getMyPassword());
    }
    
    /**
     * Make sure testRegister method throw null exception if passed null User argument.
     */
    @Test(expected = NullPointerException.class)
    public void testRegisterIllegalUserArgument() {
        myRegister.register(null);
    }

    /**
     * Test method for {@link model.Registration#clear()}.
     */
    @Test
    public void testClear() {
        myRegister.clear();
        assertEquals("clear failed", true, myRegister.getMyUserList().isEmpty());
    }

    /**
     * Test method for {@link model.Registration#toString()}.
     */
    @Test
    public void testToString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Registered UserList {");

        final Set<String> keys = myUserTestList.keySet();
        final Iterator<String> itr = keys.iterator();
        final String commaPlusSpace = ", ";

        while (itr.hasNext()) {
            final String key = itr.next();
            sb.append(key);
            sb.append(" = ");
            sb.append(myUserTestList.get(key));
            sb.append(commaPlusSpace);
        }
        sb.append('}');
        sb.delete(sb.lastIndexOf(commaPlusSpace), sb.indexOf("}"));
        assertEquals("toString method failed", sb.toString(), myRegister.toString());
    }

}
