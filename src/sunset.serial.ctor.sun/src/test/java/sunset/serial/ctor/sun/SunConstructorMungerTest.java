package sunset.serial.ctor.sun;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

/**
 * Created by chris on 7/16/2017.
 */
class SunConstructorMungerTest {

  private static class Foo {

    private final int anInt;
    private final Object anObject;

    private Foo() {
      anInt = 1;
      anObject = "hello";
    }
  }

  private static class Bar {

    private final Object anObject;
    private final int anInt;

    private Bar() {
      anInt = 2;
      anObject = "goodbye";
    }
  }

  @Test
  void newConstructorForSerialization()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    Constructor<Bar> sourceCtor = Bar.class.getDeclaredConstructor();
    Constructor<Foo> mungedCtor = new SunConstructorMunger()
        .newConstructorForSerialization(Foo.class, sourceCtor);
    Foo foo = mungedCtor.newInstance();
    assertEquals(2, foo.anInt);
    assertEquals("goodbye", foo.anObject);
  }

}