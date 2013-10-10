package au.com.bytecode.guava;

import org.junit.Test;
import static org.junit.Assert.*;
import au.com.bytecode.domain.Account;

/**
 * Basic work around Common object-related operations.
 * 
 * @author Glen
 */
public class ObjectsTest {
    
    @Test
    public void aMuchNicerToString() {
        Account account = new Account("glen", "password", "glen@bytecode.com.au");
        String expected = "Account{name=glen, email=glen@bytecode.com.au, created=null, lastLogon=null}";
        assertEquals(expected, account.toString());
    }
    
}
