package au.com.bytecode.guava;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Basic work around String operations.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class StringsTest {

    @Test
    public void livingInFearOfNullNoMore() {
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(""));
        assertFalse(Strings.isNullOrEmpty("       "));
        assertNull(Strings.emptyToNull(""));
        assertNotNull(Strings.nullToEmpty(null));
    }

    @Test
    public void basicSplitting() {

        List<String> splitRoles = Splitter.on(',').trimResults()
                .omitEmptyStrings().splitToList("role1, role2 ,role3,,,role4,");
        assertEquals(4, splitRoles.size());

        Set<String> rolesSet = ImmutableSet.copyOf(splitRoles);
        ImmutableSet<String> expectedSet = ImmutableSet.of("role1", "role2", "role3", "role4");
        assertTrue(rolesSet.containsAll(expectedSet));
    }

    @Test
    public void splittingToMaps() {
        String faveCols = "Glen=Orange;Kylie=Aqua;Isaac=Blue;Zoe=Yellow";
        Map<String, String> userToColour = Splitter.on(";")
                .withKeyValueSeparator("=")
                .split(faveCols);
        assertEquals("Orange", userToColour.get("Glen"));
    }
    
    

    @Test
    public void basicJoining() {

        List<String> someRoles = Lists.newArrayList("role1", "role2", null, "role3");
        String rolesString = Joiner.on(",").skipNulls().join(someRoles);
        assertEquals("role1,role2,role3", rolesString);

        rolesString = Joiner.on(",").useForNull("(missing)").join(someRoles);
        assertEquals("role1,role2,(missing),role3", rolesString);

    }
    
    @Test
    public void joinToMap() {
        Map<String,Integer> mapToJoin = ImmutableMap.of("one", 1, "two", 2, "three", 3);
        
        String joined = Joiner.on(",").withKeyValueSeparator("=").join(mapToJoin);
        assertEquals("one=1,two=2,three=3", joined);
        
        
    }

    @Test
    public void prefixing() {
        assertEquals("winwinwin", Strings.repeat("win", 3));
        assertEquals("win", Strings.commonPrefix("winning", "wins"));
        assertEquals("winning", Strings.commonSuffix("bi-winning", "so winning"));
    }
    
    @Test
    public void charMatchingFun() {
        assertTrue(CharMatcher.DIGIT.matches('0'));
        assertTrue(CharMatcher.DIGIT.matchesAllOf("123456789"));
        assertTrue(CharMatcher.WHITESPACE.or(CharMatcher.DIGIT).
                or(CharMatcher.JAVA_LOWER_CASE).matchesAllOf("abc 123"));
        assertEquals("123-def", CharMatcher.anyOf("cba").collapseFrom("123abcdef", '-'));
        assertEquals("123def", CharMatcher.anyOf("cba").removeFrom("123abcdef"));
        assertEquals("abc", CharMatcher.anyOf("cba").retainFrom("123abcdef"));
        
    }

}
