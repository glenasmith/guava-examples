package au.com.bytecode.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.Table;
import java.util.Collection;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Guava testing examples.
 *
 * @author Glen
 */
public class NewCollectionTypesTest {

    @Test
    public void multiSets() {
        Multiset<Integer> postcodes = HashMultiset.create();
        postcodes.add(2615);
        postcodes.add(2615);
        postcodes.add(2600);
        assertEquals(3, postcodes.size());
        assertEquals(2, postcodes.count(2615));
    }

    @Test
    public void multiMaps() {
        Multimap<Integer, String> postcodeToUserId = ArrayListMultimap.create();
        postcodeToUserId.put(2615, "glensmith");
        postcodeToUserId.put(2615, "kyliesmith");
        postcodeToUserId.put(2600, "tonyabbott");
        Collection<String> belcoUsers = postcodeToUserId.get(2615); // Glen & Kylie
        assertEquals(3, postcodeToUserId.size());
        postcodeToUserId.removeAll(2615);
        assertEquals(1, postcodeToUserId.size());
    }

    @Test
    public void biMap() {

        BiMap<String, String> userIdToEmail = HashBiMap.create();
        userIdToEmail.put("glen_smith", "glen@bytecode.com.au");
        assertEquals("glen_smith", userIdToEmail.inverse().get("glen@bytecode.com.au"));
        
    }
    
    @Test
    public void table() {
        Table<String, String, String> carsTable = HashBasedTable.create();
        carsTable.put("Holden", "Colour", "Blue");
        carsTable.put("Holden", "Model", "VY");
        carsTable.put("Ford", "Model", "XA");

        Map<String,String> holdenDetails = carsTable.row("Holden"); 
        assertEquals("Blue", holdenDetails.get("Colour"));
        assertEquals("VY", holdenDetails.get("Model"));
        
        Map<String,String> modelDetails = carsTable.column("Model"); 
        assertEquals("VY", modelDetails.get("Holden"));
        assertEquals("XA", modelDetails.get("Ford"));
        
    }

}
