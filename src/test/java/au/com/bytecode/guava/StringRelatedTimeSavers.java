package au.com.bytecode.guava;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic work around String operations.
 * 
 * @author Glen
 */
public class StringRelatedTimeSavers {
    
    @Test
    public void livingInFearOfNullNoMore() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(""));
        assertFalse(Strings.isNullOrEmpty("       "));
    }
    
    @Test
    public void splittingLikeButter() {
        
        List<String> roles = Lists.newArrayList("role1", " role1 , role2 ",
                null, " ", "role2 ");
        
        Splitter mySplitter = Splitter.on(',').trimResults().omitEmptyStrings();
        Set<String> rolesFound = Sets.newHashSet();
        for (String nextRole : roles) {
            if (!Strings.isNullOrEmpty(nextRole)) {
                for (String eachRoleOnLine : mySplitter.split(nextRole)) {
                    rolesFound.add(eachRoleOnLine);
                }
            }
        
        }
        assertEquals(2, rolesFound.size());
        assertTrue(rolesFound.contains("role1"));
        assertTrue(rolesFound.contains("role2"));
        
    }
    
}
