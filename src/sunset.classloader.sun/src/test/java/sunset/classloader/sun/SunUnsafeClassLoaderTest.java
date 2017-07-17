package sunset.classloader.sun;

import static org.junit.jupiter.api.Assertions.*;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.DynamicType.Builder;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

/**
 * Created by chris on 7/16/2017.
 */
class SunUnsafeClassLoaderTest {

  private static final Builder.MethodAnnotationTarget<Object> CLASS_BASE =
      new ByteBuddy(ClassFileVersion.JAVA_V9)
          .subclass(Object.class)
          .method(ElementMatchers.named("toString"))
          .intercept(FixedValue.value("Hello World!"));

  @SuppressWarnings("deprecation")
  private void testInstantiation(Class clazz)
      throws IllegalAccessException, InstantiationException {
    Object instance = clazz.newInstance();
    assertEquals(instance.getClass(), clazz);
    assertEquals("Hello World!", instance.toString());
  }

  @Test
  void defineClass() throws IllegalAccessException, InstantiationException {
    byte[] classBytes = CLASS_BASE.name("HelloWorld").make().getBytes();
    Class<?> clazz = new SunUnsafeClassLoader()
        .defineClass("HelloWorld", classBytes, 0, classBytes.length, getClass().getClassLoader(),
            null);
    assertEquals("HelloWorld", clazz.getName());
    assertEquals(getClass().getClassLoader(), clazz.getClassLoader());
    assertFalse(clazz.isAnonymousClass());
    testInstantiation(clazz);
  }

  @Test
  void defineAnonymousClass() throws InstantiationException, IllegalAccessException {
    byte[] classBytes = CLASS_BASE.name(getClass().getPackage().getName()+".$$$x").make().getBytes();
    Class<?> clazz = new SunUnsafeClassLoader()
        .defineAnonymousClass(getClass(), classBytes, new Object[0]);
    testInstantiation(clazz);
  }

}