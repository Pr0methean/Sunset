package sunset.common.sun;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import sun.misc.Unsafe;

/**
 * Created by chris on 7/16/2017.
 */
class SunsetSunUtilTest {

  @Test
  void getTheUnsafe() {
    Unsafe theUnsafe = SunsetSunUtil.getTheUnsafe();
    assertTrue(theUnsafe.addressSize() > 0);
  }

}