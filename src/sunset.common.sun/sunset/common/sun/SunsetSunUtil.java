package sunset.common.sun;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public final class SunsetSunUtil {
  public static Unsafe getTheUnsafe() {
    try {
      Field f = Unsafe.class.getDeclaredField("theUnsafe");
      f.setAccessible(true);
      return (Unsafe) f.get(null);
    } catch (ReflectiveOperationException | SecurityException e) {
      throw new RuntimeException(e);
    }
  }
}
