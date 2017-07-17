package sunset.sneakythrow.sun;

import sun.misc.Unsafe;
import sunset.common.sun.SunsetSunUtil;
import sunset.sneakythrow.SneakyThrower;

/**
 * An implementation of {@link SneakyThrower} using {@link Unsafe}.
 */
public class SunSneakyThrower implements SneakyThrower {

  private static final Unsafe unsafe = SunsetSunUtil.getTheUnsafe();

  @Override
  public <T> T sneakyThrow(Throwable throwable) {
    unsafe.throwException(throwable);
    throw new RuntimeException("Unsafe.throwException failed", throwable);
  }

}
