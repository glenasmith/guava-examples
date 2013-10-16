package au.com.bytecode.guava;

import au.com.bytecode.domain.Account;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Making basic collections easier to work with.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class CollectionsTest {

    @Test
    public void collectionCreation() {
        List<String> empty = Lists.newArrayList();
        Map<String, String> userIdToName = Maps.newHashMap();

        List<String> names = Lists.newArrayList("Glen", "Kylie", "Isaac", "Zoe");
        assertEquals(4, names.size());
    }

    @Test
    public void immutableCollections() {
        Set<String> PEOPLE = ImmutableSet.of("Glen", "Kylie", "Isaac", "Zoe");

        try {
            PEOPLE.add("Add me");
            fail("Should not be able to add()");
        } catch (UnsupportedOperationException uoe) {
            // all is well
        }

    }

    @Test
    public void immutableMaps() {

        ImmutableMap<String, String> userIdToEmail = ImmutableMap.of("glen", "glen@bytecode.com.au",
                "kylie", "kylie@bytecode.com.au");
        assertEquals("glen@bytecode.com.au", userIdToEmail.get("glen"));
    }

    @Test
    public void buildingImmutableMaps() {

        ImmutableMap<Integer, String> postcodes = new ImmutableMap.Builder<Integer, String>()
                .put(2600, "Tony")
                .put(2615, "Glen")
                .build();
        assertEquals("Tony", postcodes.get(2600));

    }

    @Test
    public void setOperations() {
        Set<String> first = Sets.newHashSet("a", "b", "c");
        Set<String> second = Sets.newHashSet("c", "d", "e");

        assertEquals(ImmutableSet.of("a", "b"), Sets.difference(first, second));
        assertEquals(ImmutableSet.of("a", "b", "d", "e"), Sets.symmetricDifference(first, second));
        assertEquals(ImmutableSet.of("c"), Sets.intersection(first, second));
        assertEquals(ImmutableSet.of("a", "b", "c", "d", "e"), Sets.union(first, second));
    }
    
    @Test
    public void orderingCollections() {
        List<Account> accounts = ImmutableList.of(
                new Account("glen", "pw", "glen@glensmith.com.au"),
                new Account("glen", "pw", "glen@bytecode.com.au"),
                new Account("kylie", "pw", "kylie@bytecode.com.au")
                
        );
        Ordering<Account> byNameThenEmail = new Ordering<Account>() {

            @Override
            public int compare(Account left, Account right) {
                return ComparisonChain.start().
                        compare(left.getUsername(), right.getUsername()).
                        compare(left.getEmail(), right.getEmail()).
                        result();
            }
            
        };
        assertFalse(byNameThenEmail.isOrdered(accounts));
        assertEquals("glen@bytecode.com.au", 
                byNameThenEmail.sortedCopy(accounts).get(0).getEmail());
        assertEquals("kylie", byNameThenEmail.reverse().min(accounts).getUsername());
        
    }

}
