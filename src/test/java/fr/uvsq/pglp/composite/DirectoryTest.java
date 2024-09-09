package fr.uvsq.pglp.composite;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectoryTest {
  private static final String DIRECTORYNAME = "src";
  private static final String FILENAME = "README.md";
  private static final int FILESIZE = 1024;

  private Directory src;
  private Directory root;
  private File readme;

  @BeforeEach
  public void setup() {
    src = new Directory(DIRECTORYNAME);
    readme = new File(FILENAME, FILESIZE);

    root = new Directory("/");
    var etc = new Directory("etc");
    etc.add(new File("passwd", 10000));
    etc.add(new File("hosts", 20000));
    root.add(etc);
    var home = new Directory("home");
    var hal = new Directory("hal");
    hal.add(new File(".bashrc", 1000));
    home.add(hal);
    root.add(home);
  }

  @Test
  public void shouldCreateAnEmptyDirectory() {
    assertEquals(DIRECTORYNAME, src.getName());
    assertEquals(0, src.getSize());
  }

  @Test
  public void directoryNameCantBeNull() {
    Exception exception = assertThrows(NullPointerException.class, () ->
        new Directory(null));
    assertEquals("The name can't be null", exception.getMessage());
  }

  @Test
  public void directoryNameCantBeAnEmptyString() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new Directory(""));
    assertEquals("The name can't be empty", exception.getMessage());
  }

  @Test
  public void shouldCreateADirectoryWithAFile() {
    var d = new Directory("/");
    d.add(readme);
    assertTrue(d.contains(readme));
    assertEquals(FILESIZE, d.getSize());
  }

  @Test
  public void shouldCreateADirectoryStructure() {
    assertEquals(31000, root.getSize());
  }

  @Test
  public void aDirectoryCantBeAddedToItself() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          var d = new Directory("src");
          d.add(d);
        }
    );
    assertEquals("A directory can't be added to itself", exception.getMessage());
  }

  @Test
  public void aDirectoryCantBeAddedToADescendantOfItself() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
          var d1 = new Directory("src");
          var d2 = new Directory("main");
          d1.add(d2);
          d2.add(d1);
        }
    );
    assertEquals("A directory can't be added to a descendant of itself", exception.getMessage());
  }

  @Test
  public void aDirectoryCanBeTraversed() {
    var file1 = new File("file1", 10);
    var file2 = new File("file2", 20);
    final List<Entry> expectedEntries = List.of(file1, file2);
    var directory = new Directory("dir1");
    directory.add(file1);
    directory.add(file2);
    List<Entry> visitedEntries = new ArrayList<>();
    for (Entry entry : directory) {
      visitedEntries.add(entry);
    }
    assertEquals(expectedEntries, visitedEntries);
  }

  @Test
  public void nestedDirectoriesCanBeTraversed() {
    var file1 = new File("file1", 10);
    var file2 = new File("file2", 20);
    final List<Entry> expectedEmployees = List.of(file1, file2, file1, file2, file1, file2);
    var d1 = new Directory("d1");
    var d2 = new Directory("d2");
    var d3 = new Directory("d3");
    d3.add(file1); d3.add(file2);

    d2.add(file2); d2.add(d3); d2.add(file1);

    d1.add(file1); d1.add(d2); d1.add(file2);

    List<Entry> visitedEntries = new ArrayList<>();
    for (Entry element : d1) {
      visitedEntries.add(element);
    }
    assertEquals(expectedEmployees, visitedEntries);
  }
}
