package au.com.bytecode.guava;

import au.com.bytecode.domain.Account;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Guava testing examples.
 *
 * @author Glen
 */
public class FunctionalTest {

    List<Account> accounts;

    @Before
    public void setup() {
        accounts = Lists.newArrayList(new Account("glen", "pw", "glen@bytecode.com.au"),
                new Account("joe", "pw", "joe@joecool.com.au"),
                new Account("mary", "secret", "mary@marycool.com.au"));

    }

    @Test
    public void filteringCollection() {
        List<Account> badPasswords = Lists.newArrayList(Iterables.filter(accounts, new Predicate<Account>() {
            @Override
            public boolean apply(Account account) {
                return account.getPassword().equals("pw");
            }
        }));
        assertEquals(2, badPasswords.size());
    }

    @Test
    public void transformCollection() {
        Collection<String> emails = Collections2.transform(accounts, new Function<Account, String>() {
            @Override
            public String apply(Account account) {
                return account.getEmail();
            }
        });
        ImmutableSet<String> expected = ImmutableSet.of("glen@bytecode.com.au", "joe@joecool.com.au", "mary@marycool.com.au");
        Set<String> actual = Sets.newHashSet(emails);
        assertEquals(0, Sets.symmetricDifference(expected, actual).size());
    }
}
