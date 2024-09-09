package fr.uvsq.pglp.composite;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.*;

public class EntryTest {
  @Test
  void entryShouldBeAbstract() {
    Class<Entry> clazz = Entry.class;
    assertTrue(Modifier.isAbstract(clazz.getModifiers()));
  }

  @Test
  void entryShouldHaveANameField() throws NoSuchFieldException, NoSuchMethodException {
    Field name = Entry.class.getDeclaredField("name");
    assertEquals(String.class, name.getType());
    assertTrue(Modifier.isPrivate(name.getModifiers()));
    Method getName = Entry.class.getDeclaredMethod("getName");
    assertTrue(Modifier.isPublic(getName.getModifiers()));
  }

  @Test
  void entryShouldHaveAnAbstractGetSizeMethod() throws NoSuchMethodException {
    Method getSize = Entry.class.getDeclaredMethod("getSize");
    assertTrue(Modifier.isAbstract(getSize.getModifiers()));
  }
}
