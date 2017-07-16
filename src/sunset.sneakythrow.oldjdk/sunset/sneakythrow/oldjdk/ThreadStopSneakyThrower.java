package sunset.sneakythrow.oldjdk;

import sunset.sneakythrow.SneakyThrower;

/**
 * An implementation of {@link SneakyThrower} that uses a long-deprecated, but as of Java 9 still
 * almost universally available, method to throw the exception.
 */
public class ThreadStopSneakyThrower implements SneakyThrower {

  @SuppressWarnings("deprecation")
  @Override
  public <T> T sneakyThrow(Throwable throwable) {
    Thread.currentThread().stop(throwable);
    throw new RuntimeException("Thread.stop failed", throwable);
  }
}
