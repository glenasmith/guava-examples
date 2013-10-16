package au.com.bytecode.guava;

import au.com.bytecode.domain.Account;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Reading, writing, copying, renaming, touching files.
 *
 * @author Glen Smith (glen@bytecode.com.au)
 */
public class FilesTest {

    @Test
    public void readingTextFiles() throws IOException {
        List<String> allLines = Files.readLines(new File("README.md"), Charsets.UTF_8);
        assertEquals(12, allLines.size());
    }

    @Test
    public void readingPerLine() throws IOException {
        List<Account> allAccounts = Files.readLines(new File("src/test/resources/accounts.txt"),
                Charsets.UTF_8, new LineProcessor<List<Account>>() {

                    List<Account> accounts = Lists.newArrayList();

                    @Override
                    public boolean processLine(String string) throws IOException {
                        List<String> items = Splitter.on(",").trimResults().splitToList(string);
                        if (items.size() != 3) {
                            throw new IllegalArgumentException(String.format("Malformed line in input file: {0}", string));
                        }
                        accounts.add(new Account(items.get(0), items.get(1), items.get(2)));
                        return true;
                    }

                    @Override
                    public List<Account> getResult() {
                        return accounts;
                    }

                });
        assertEquals(3, allAccounts.size());
        assertEquals("glen", allAccounts.get(0).getUsername());

    }

    @Test
    public void readingByteArray() throws IOException {

        byte[] allFileBytes = Files.toByteArray(new File("src/test/resources/logo.png"));
        assertEquals(9614, allFileBytes.length);
    }
    
    @Test
    public void writingTextFiles() throws IOException {
        
        File outFile = new File("target/writeme.txt");
        Files.write("Nothing to see here. Move along.", outFile, Charsets.UTF_8);
        Files.append("\nNo, really.", outFile, Charsets.UTF_8);
        
    }
    
    
    @Test
    public void touchAndGetFileDetails() throws IOException {
        
        File tempFile = new File("target/touching.txt");
        Files.touch(tempFile);
        assertEquals("txt", Files.getFileExtension(tempFile.getAbsolutePath()));
        assertEquals("touching", Files.getNameWithoutExtension(tempFile.getAbsolutePath()));
        
    }
    
    
    @Test
    public void renamingFiles() throws IOException {
        File from = new File("target/old.txt");
        Files.touch(from);
        File to = new File("target/new.txt");
        Files.move(from, to);
        assertTrue(new File("target/new.txt").exists());
    }
    
    @Test
    public void copyingFiles() throws IOException {
        File src = new File("target/src.txt");
        Files.touch(src);
        File dest = new File("target/dest.txt");
        Files.copy(src, dest);
        assertTrue(new File("target/dest.txt").exists());
    }
    
    @Test
    public void createParentDirs() throws IOException {
        File createMe = new File("/target/path/to/file.txt");
        Files.createParentDirs(createMe);
        assertTrue(new File("/target/path/to").exists());
    }

}
