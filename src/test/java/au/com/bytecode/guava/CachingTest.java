package au.com.bytecode.guava;

import au.com.bytecode.domain.Account;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Working with in-memory caching.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class CachingTest {

    @Test
    public void cacheFun() throws InterruptedException, ExecutionException {
        final AtomicInteger counter = new AtomicInteger();
        
        LoadingCache<String, Account> accountDetailsCache
                = CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .build(new CacheLoader<String, Account>() {
                    public Account load(String userId) {
                        return new Account(userId, Integer.toString(counter.incrementAndGet()), 
                                userId + "@sample.com");
                    }
                });
        
        Account glen = accountDetailsCache.get("glen");
        assertEquals("1", glen.getPassword());
        glen = accountDetailsCache.get("glen");
        assertEquals("1", glen.getPassword());
        
        Thread.sleep(1100);
        
        glen = accountDetailsCache.get("glen");
        assertEquals("2", glen.getPassword());
        glen = accountDetailsCache.get("glen");
        assertEquals("2", glen.getPassword());

        
    }

}
