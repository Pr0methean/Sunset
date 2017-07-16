package sunset.serial.ctor.sun;

import java.lang.reflect.Constructor;
import sun.reflect.ReflectionFactory;

import sunset.serial.ctor.ConstructorMunger;

/**
 * An implementation of {@link ConstructorMunger} using {@link ReflectionFactory}.
 */
public class SunConstructorMunger implements ConstructorMunger {
  private final ReflectionFactory factory = ReflectionFactory.getReflectionFactory();

  @SuppressWarnings("unchecked")
  @Override
  public <T> Constructor<T> newConstructorForSerialization(Class<T> classToInstantiate,
      Constructor<?> constructorToCall) {
    return (Constructor<T>) factory.newConstructorForSerialization(classToInstantiate,
        constructorToCall);
  }

}
