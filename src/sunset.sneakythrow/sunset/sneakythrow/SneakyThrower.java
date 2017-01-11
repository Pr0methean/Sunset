package sunset.sneakythrow;

import sunset.common.SunsetUtil;

public interface SneakyThrower {
  static SneakyThrower getInstance() {
    return SunsetUtil.loadService(SneakyThrower.class);
  }
  
  /**
   * Throws {@code throwable} without the caller having to declare it, even if it is a checked exception.
   */
  <T> T sneakyThrow(Throwable throwable);
}
