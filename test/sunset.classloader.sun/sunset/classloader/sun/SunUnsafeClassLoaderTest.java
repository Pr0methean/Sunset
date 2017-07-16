package sunset.classloader.sun;

import static org.junit.jupiter.api.Assertions.*;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

/**
 * Created by chris on 7/16/2017.
 */
class SunUnsafeClassLoaderTest {

  private static final byte[] CLASS_BYTES = new ByteBuddy()
      .subclass(Object.class)
      .method(ElementMatchers.named("toString"))
      .intercept(FixedValue.value("Hello World!"))
      .make()
      .getBytes();

  @SuppressWarnings("deprecation")
  private void testInstantiation(Class clazz)
      throws IllegalAccessException, InstantiationException {
    Object instance = clazz.newInstance();
    assertEquals(instance.getClass(), clazz);
    assertEquals("Hello World!", instance.toString());
  }

  @Test
  void defineClass() throws IllegalAccessException, InstantiationException {
    Class<?> clazz = new SunUnsafeClassLoader()
        .defineClass("HelloWorld", CLASS_BYTES, 0, CLASS_BYTES.length, getClass().getClassLoader(),
            null);
    assertEquals("HelloWorld", clazz.getName());
    assertEquals(getClass().getClassLoader(), clazz.getClassLoader());
    assertFalse(clazz.isAnonymousClass());
    testInstantiation(clazz);
  }

  @Test
  void defineAnonymousClass() throws InstantiationException, IllegalAccessException {
    Class<?> clazz = new SunUnsafeClassLoader()
        .defineAnonymousClass(getClass(), CLASS_BYTES, new Object[0]);
    assertTrue(clazz.isAnonymousClass());
    assertEquals(getClass(), clazz.getEnclosingClass());
    assertNull(clazz.getName());
    testInstantiation(clazz);
  }

}