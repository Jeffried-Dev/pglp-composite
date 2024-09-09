package fr.uvsq.pglp.composite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileTest {
  private static final String FILENAME = "README.md";
  private static final int FILESIZE = 1024;

  private File readme;

  @BeforeEach
  public void setup() {
    readme = new File(FILENAME, FILESIZE);
  }

  @Test
  public void shouldCreateASimpleFile() {
    assertEquals(FILENAME, readme.getName());
    assertEquals(FILESIZE, readme.getSize());
  }

  @Test
  public void filenameCantBeNull() {
    Exception exception = assertThrows(NullPointerException.class, () ->
        new File(null, FILESIZE));
    assertEquals("The name can't be null", exception.getMessage());
  }

  @Test
  public void filenameCantBeAnEmptyString() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new File("", FILESIZE));
    assertEquals("The name can't be empty", exception.getMessage());
  }

  @Test
  public void fileSizeCantBeNegative() {
    Exception exception = assertThrows(IllegalArgumentException.class, () ->
        new File(FILENAME, -1));
    assertEquals("The file size can't be negative", exception.getMessage());
  }
}
