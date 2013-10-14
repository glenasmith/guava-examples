package au.com.bytecode.guava;

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
 * Guava testing examples.
 *
 * @author Glen
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

}
