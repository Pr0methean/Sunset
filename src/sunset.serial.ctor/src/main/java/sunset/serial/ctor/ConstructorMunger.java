package sunset.serial.ctor;

import java.lang.reflect.Constructor;

import sunset.common.SunsetUtil;

/**
 * Factory that can munge one class's constructors to create instances of another class. For
 * examples of how this is used in the real world, see:
 * <ul>
 * <li>
 * https://github.com/easymock/objenesis/blob/master/main/src/main/java/org/objenesis/instantiator/sun/SunReflectionFactoryInstantiator.java
 * </li>
 * <li>
 * https://github.com/easymock/objenesis/blob/master/main/src/main/java/org/objenesis/instantiator/sun/SunReflectionFactorySerializationInstantiator.java
 * </li>
 * </ul>
 * For interesting tidbits about the full extent of its power (e.g. when the class whose constructor
 * is invoked is <i>not</i> a superclass of the class actually instantiated), see
 * http://mydailyjava.blogspot.com/2013/12/sunmiscunsafe.html
 */
public interface ConstructorMunger {

  static ConstructorMunger getInstance() {
    return SunsetUtil.loadService(ConstructorMunger.class);
  }

  /**
   * Returns a {@link Constructor} that invokes the same {@code <init>()} method as the given one,
   * but can return an instance of a different class.
   *
   * @param classToInstantiate The class the {@code Constructor will actually create and return.
   * @param constructorToCall The constructor whose code is to be used.
   */
  <T> Constructor<T> newConstructorForSerialization(Class<T> classToInstantiate,
      Constructor<? /* not necessarily super T! */> constructorToCall);
}
