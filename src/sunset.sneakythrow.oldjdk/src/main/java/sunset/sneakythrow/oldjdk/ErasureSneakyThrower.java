package sunset.sneakythrow.oldjdk;

import sunset.sneakythrow.SneakyThrower;

/**
 * An implementation of {@link SneakyThrower} that exploits generic type erasure to throw the
 * exception.
 */
public class ErasureSneakyThrower implements SneakyThrower {

  @SuppressWarnings("deprecation")
  @Override
  public <T> T sneakyThrow(Throwable throwable) {
    sneakyThrow0(throwable);
    throw new RuntimeException("sneakyThrow0 returned");
  }

  @SuppressWarnings("unchecked")
  static private <T extends Throwable> void sneakyThrow0(Throwable t) throws T {
    throw (T) t;
  }
}
