package au.com.bytecode.guava;

import com.google.common.base.Strings;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic work around String operations.
 * 
 * @author Glen
 */
public class StringRelatedTimeSavers {
    
    @Test
    void livingInFearOfNullNoMore() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty("       "));
    }
    
}
