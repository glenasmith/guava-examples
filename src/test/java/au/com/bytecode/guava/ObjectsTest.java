package au.com.bytecode.guava;

import org.junit.Test;
import static org.junit.Assert.*;
import au.com.bytecode.domain.Account;
import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import java.util.SortedSet;

/**
 * Basic work around Common object-related operations.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class ObjectsTest {

    @Test
    public void nicerToString() {
        Account account = new Account("glen", "password", "glen@bytecode.com.au");
        String expected = "Account{name=glen, email=glen@bytecode.com.au, created=null, lastLogon=null}";
        assertEquals(expected, account.toString());
    }

    @Test
    public void firstNonNull() {
        assertEquals("two", Objects.firstNonNull(null, "two"));
    }
    
    @Test
    public void nullsafeEquals() {
        assertTrue(Objects.equal(null, null));
        assertFalse(Objects.equal(null, "something"));
    }
    
    @Test
    public void implementingHashCode() {
        
        int code = Objects.hashCode(1, 2, 3, null, 4);
        assertEquals(29615141, code);
        
    }
    
    @Test
    public void comparisonChain() {
        
        Account one = new Account("glen", "pw", "glen@glensmith.com.au");
        Account two = new Account("glen", "pw", "glen@bytecode.com.au");
        
        SortedSet<Account> accounts = Sets.newTreeSet();
        accounts.add(one);
        accounts.add(two);
        Account first = Iterables.getFirst(accounts, null);
        assertEquals("glen@bytecode.com.au", first.getEmail());
       
        
    }
    

}
