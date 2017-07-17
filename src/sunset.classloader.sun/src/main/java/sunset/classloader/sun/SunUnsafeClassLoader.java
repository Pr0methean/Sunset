package sunset.classloader.sun;

import java.security.ProtectionDomain;

import sun.misc.Unsafe;
import sunset.classloader.UnsafeClassLoader;
import sunset.common.sun.SunsetSunUtil;

/**
 * An implementation of {@link UnsafeClassLoader} using {@link sun.misc.Unsafe}.
 */
public class SunUnsafeClassLoader implements UnsafeClassLoader {

  private static final Unsafe unsafe = SunsetSunUtil.getTheUnsafe();

  @Override
  public Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader,
      ProtectionDomain protectionDomain) {
    return unsafe.defineClass(name, b, off, len, loader, protectionDomain);
  }

  @Override
  public Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches) {
    return unsafe.defineAnonymousClass(hostClass, data, cpPatches);
  }

}
