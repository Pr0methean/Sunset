package sunset.classloader;

import java.security.ProtectionDomain;

/**
 * An object that registers classes in the JVM given arrays of bytecode, but can associate them with
 * any {@link ClassLoader}. Method descriptions and signatures are copied from
 * {@link sun.misc.Unsafe}.
 */
public interface UnsafeClassLoader {
  /**
   * Tell the VM to define a class, without security checks. By default, the class loader and
   * protection domain come from the caller's class.
   */
  Class<?> defineClass(String name, byte[] b, int off, int len, ClassLoader loader,
      ProtectionDomain protectionDomain);

  /**
   * Define a class but do not make it known to the class loader or system dictionary.
   * <p>
   * For each CP entry, the corresponding CP patch must either be null or have the a format that
   * matches its tag:
   * <ul>
   * <li>Integer, Long, Float, Double: the corresponding wrapper object type from java.lang
   * <li>Utf8: a string (must have suitable syntax if used as signature or name)
   * <li>Class: any java.lang.Class object
   * <li>String: any object (not just a java.lang.String)
   * <li>InterfaceMethodRef: (NYI) a method handle to invoke on that call site's arguments
   * </ul>
   * 
   * @params hostClass context for linkage, access control, protection domain, and class loader
   * @params data bytes of a class file
   * @params cpPatches where non-null entries exist, they replace corresponding CP entries in data
   */
  Class<?> defineAnonymousClass(Class<?> hostClass, byte[] data, Object[] cpPatches);
}
