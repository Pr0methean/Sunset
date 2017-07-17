package sunset.memory;

import java.lang.reflect.Field;

import sunset.common.SunsetUtil;

/**
 * Low-level memory-access methods that can be used for type punning. Descriptions and signatures
 * are copied from {@link sun.misc.Unsafe}.
 */
public interface LowLevelMemoryAccessor {

  static LowLevelMemoryAccessor getInstance() {
    return SunsetUtil.loadService(LowLevelMemoryAccessor.class);
  }

  /**
   * Fetches a value from a given Java variable. More specifically, fetches a field or array element
   * within the given object <code>o</code> at the given offset, or (if <code>o</code> is null) from
   * the memory address whose numerical value is the given offset.
   * <p>
   * The results are undefined unless one of the following cases is true:
   * <ul>
   * <li>The offset was obtained from {@link #objectFieldOffset} on the
   * {@link java.lang.reflect.Field} of some Java field and the object referred to by <code>o</code>
   * is of a class compatible with that field's class.
   *
   * <li>The offset and object reference <code>o</code> (either null or non-null) were both obtained
   * via {@link #staticFieldOffset} and {@link #staticFieldBase} (respectively) from the reflective
   * {@link Field} representation of some Java field.
   *
   * <li>The object referred to by <code>o</code> is an array, and the offset is an integer of the
   * form <code>B+N*S</code>, where <code>N</code> is a valid index into the array, and
   * <code>B</code> and <code>S</code> are the values obtained by {@link #arrayBaseOffset} and
   * {@link #arrayIndexScale} (respectively) from the array's class. The value referred to is the
   * <code>N</code><em>th</em> element of the array.
   *
   * </ul>
   * <p>
   * If one of the above cases is true, the call references a specific Java variable (field or array
   * element). However, the results are undefined if that variable is not in fact of the type
   * returned by this method.
   * <p>
   * This method refers to a variable by means of two parameters, and so it provides (in effect) a
   * <em>double-register</em> addressing mode for Java variables. When the object reference is null,
   * this method uses its offset as an absolute address. This is similar in operation to methods
   * such as {@link #getInt(long)}, which provide (in effect) a <em>single-register</em> addressing
   * mode for non-Java variables. However, because Java variables may have a different layout in
   * memory from non-Java variables, programmers should not assume that these two addressing modes
   * are ever equivalent. Also, programmers should remember that offsets from the double-register
   * addressing mode cannot be portably confused with longs used in the single-register addressing
   * mode.
   *
   * @param o Java heap object in which the variable resides, if any, else null
   * @param offset indication of where the variable resides in a Java heap object, if any, else a
   *        memory address locating the variable statically
   * @return the value fetched from the indicated Java variable
   * @throws RuntimeException No defined exceptions are thrown, not even
   *         {@link NullPointerException}
   */
  int getInt(Object o, long offset);

  /**
   * Stores a value into a given Java variable.
   * <p>
   * The first two parameters are interpreted exactly as with {@link #getInt(Object, long)} to refer
   * to a specific Java variable (field or array element). The given value is stored into that
   * variable.
   * <p>
   * The variable must be of the same type as the method parameter <code>x</code>.
   *
   * @param o Java heap object in which the variable resides, if any, else null
   * @param offset indication of where the variable resides in a Java heap object, if any, else a
   *        memory address locating the variable statically
   * @param x the value to store into the indicated Java variable
   * @throws RuntimeException No defined exceptions are thrown, not even
   *         {@link NullPointerException}
   */
  void putInt(Object o, long offset, int x);

  /**
   * Fetches a reference value from a given Java variable.
   * 
   * @see #getInt(Object, long)
   */
  Object getObject(Object o, long offset);

  /**
   * Stores a reference value into a given Java variable.
   * <p>
   * Unless the reference <code>x</code> being stored is either null or matches the field type, the
   * results are undefined. If the reference <code>o</code> is non-null, car marks or other store
   * barriers for that object (if the VM requires them) are updated.
   * 
   * @see #putInt(Object, int, int)
   */
  void putObject(Object o, long offset, Object x);

  /** @see #getInt(Object, long) */
  boolean getBoolean(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putBoolean(Object o, long offset, boolean x);

  /** @see #getInt(Object, long) */
  byte getByte(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putByte(Object o, long offset, byte x);

  /** @see #getInt(Object, long) */
  short getShort(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putShort(Object o, long offset, short x);

  /** @see #getInt(Object, long) */
  char getChar(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putChar(Object o, long offset, char x);

  /** @see #getInt(Object, long) */
  long getLong(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putLong(Object o, long offset, long x);

  /** @see #getInt(Object, long) */
  float getFloat(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putFloat(Object o, long offset, float x);

  /** @see #getInt(Object, long) */
  double getDouble(Object o, long offset);

  /** @see #putInt(Object, int, int) */
  void putDouble(Object o, long offset, double x);

  /**
   * Allocates a new block of native memory, of the given size in bytes. The contents of the memory
   * are uninitialized; they will generally be garbage. The resulting native pointer will never be
   * zero, and will be aligned for all value types. Dispose of this memory by calling
   * {@link #freeMemory}, or resize it with {@link #reallocateMemory}.
   *
   * @throws IllegalArgumentException if the size is negative or too large for the native size_t
   *         type
   *
   * @throws OutOfMemoryError if the allocation is refused by the system
   *
   * @see #getByte(long)
   * @see #putByte(long, byte)
   */
  long allocateMemory(long bytes);

  /**
   * Resizes a new block of native memory, to the given size in bytes. The contents of the new block
   * past the size of the old block are uninitialized; they will generally be garbage. The resulting
   * native pointer will be zero if and only if the requested size is zero. The resulting native
   * pointer will be aligned for all value types. Dispose of this memory by calling
   * {@link #freeMemory}, or resize it with {@link #reallocateMemory}. The address passed to this
   * method may be null, in which case an allocation will be performed.
   *
   * @throws IllegalArgumentException if the size is negative or too large for the native size_t
   *         type
   *
   * @throws OutOfMemoryError if the allocation is refused by the system
   *
   * @see #allocateMemory
   */
  long reallocateMemory(long address, long bytes);

  /**
   * Sets all bytes in a given block of memory to a fixed value (usually zero).
   *
   * <p>
   * This method determines a block's base address by means of two parameters, and so it provides
   * (in effect) a <em>double-register</em> addressing mode, as discussed in
   * {@link #getInt(Object,long)}. When the object reference is null, the offset supplies an
   * absolute base address.
   *
   * <p>
   * The stores are in coherent (atomic) units of a size determined by the address and length
   * parameters. If the effective address and length are all even modulo 8, the stores take place in
   * 'long' units. If the effective address and length are (resp.) even modulo 4 or 2, the stores
   * take place in units of 'int' or 'short'.
   *
   * @since 1.7
   */
  void setMemory(Object o, long offset, long bytes, byte value);

  /**
   * Sets all bytes in a given block of memory to a fixed value (usually zero). This provides a
   * <em>single-register</em> addressing mode, as discussed in {@link #getInt(Object,long)}.
   *
   * <p>
   * Equivalent to <code>setMemory(null, address, bytes, value)</code>.
   */
  default void setMemory(long address, long bytes, byte value) {
    setMemory(null, address, bytes, value);
  }

  /**
   * Sets all bytes in a given block of memory to a copy of another block.
   *
   * <p>
   * This method determines each block's base address by means of two parameters, and so it provides
   * (in effect) a <em>double-register</em> addressing mode, as discussed in
   * {@link #getInt(Object,long)}. When the object reference is null, the offset supplies an
   * absolute base address.
   *
   * <p>
   * The transfers are in coherent (atomic) units of a size determined by the address and length
   * parameters. If the effective addresses and length are all even modulo 8, the transfer takes
   * place in 'long' units. If the effective addresses and length are (resp.) even modulo 4 or 2,
   * the transfer takes place in units of 'int' or 'short'.
   *
   * @since 1.7
   */
  void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset, long bytes);

  /**
   * Sets all bytes in a given block of memory to a copy of another block. This provides a
   * <em>single-register</em> addressing mode, as discussed in {@link #getInt(Object,long)}.
   *
   * Equivalent to <code>copyMemory(null, srcAddress, null, destAddress, bytes)</code>.
   */
  default void copyMemory(long srcAddress, long destAddress, long bytes) {
    copyMemory(null, srcAddress, null, destAddress, bytes);
  }

  /**
   * Disposes of a block of native memory, as obtained from {@link #allocateMemory} or
   * {@link #reallocateMemory}. The address passed to this method may be null, in which case no
   * action is taken.
   *
   * @see #allocateMemory
   */
  void freeMemory(long address);

  /**
   * Report the location of a given field in the storage allocation of its class. Do not expect to
   * perform any sort of arithmetic on this offset; it is just a cookie which is passed to the
   * unsafe heap memory accessors.
   *
   * <p>
   * Any given field will always have the same offset and base, and no two distinct fields of the
   * same class will ever have the same offset and base.
   *
   * <p>
   * As of 1.4.1, offsets for fields are represented as long values, although the Sun JVM does not
   * use the most significant 32 bits. However, JVM implementations which store static fields at
   * absolute addresses can use long offsets and null base pointers to express the field locations
   * in a form usable by {@link #getInt(Object,long)}. Therefore, code which will be ported to such
   * JVMs on 64-bit platforms must preserve all bits of static field offsets.
   * 
   * @see #getInt(Object, long)
   */
  long staticFieldOffset(Field f);

  /**
   * Report the location of a given static field, in conjunction with {@link #staticFieldBase}.
   * <p>
   * Do not expect to perform any sort of arithmetic on this offset; it is just a cookie which is
   * passed to the unsafe heap memory accessors.
   *
   * <p>
   * Any given field will always have the same offset, and no two distinct fields of the same class
   * will ever have the same offset.
   *
   * <p>
   * As of 1.4.1, offsets for fields are represented as long values, although the Sun JVM does not
   * use the most significant 32 bits. It is hard to imagine a JVM technology which needs more than
   * a few bits to encode an offset within a non-array object, However, for consistency with other
   * methods in this class, this method reports its result as a long value.
   * 
   * @see #getInt(Object, long)
   */
  long objectFieldOffset(Field f);

  /**
   * Report the location of a given static field, in conjunction with {@link #staticFieldOffset}.
   * <p>
   * Fetch the base "Object", if any, with which static fields of the given class can be accessed
   * via methods like {@link #getInt(Object, long)}. This value may be null. This value may refer to
   * an object which is a "cookie", not guaranteed to be a real Object, and it should not be used in
   * any way except as argument to the get and put routines in this class.
   */
  Object staticFieldBase(Field f);

  /**
   * Report the offset of the first element in the storage allocation of a given array class. If
   * {@link #arrayIndexScale} returns a non-zero value for the same class, you may use that scale
   * factor, together with this base offset, to form new offsets to access elements of arrays of the
   * given class.
   *
   * @see #getInt(Object, long)
   * @see #putInt(Object, long, int)
   */
  int arrayBaseOffset(Class<?> arrayClass);

  /**
   * Report the scale factor for addressing elements in the storage allocation of a given array
   * class. However, arrays of "narrow" types will generally not work properly with accessors like
   * {@link #getByte(Object, int)}, so the scale factor for such classes is reported as zero.
   *
   * @see #arrayBaseOffset
   * @see #getInt(Object, long)
   * @see #putInt(Object, long, int)
   */
  int arrayIndexScale(Class<?> arrayClass);

  /**
   * Report the size in bytes of a native pointer, as stored via {@link #putAddress}. This value
   * will be either 4 or 8. Note that the sizes of other primitive types (as stored in native memory
   * blocks) is determined fully by their information content.
   */
  int addressSize();

  /**
   * Report the size in bytes of a native memory page (whatever that is). This value will always be
   * a power of two.
   */
  int pageSize();

  /**
   * Atomically update Java variable to <tt>x</tt> if it is currently holding <tt>expected</tt>.
   * 
   * @return <tt>true</tt> if successful
   */
  boolean compareAndSwapObject(Object o, long offset, Object expected, Object x);

  /**
   * Atomically update Java variable to <tt>x</tt> if it is currently holding <tt>expected</tt>.
   * 
   * @return <tt>true</tt> if successful
   */
  boolean compareAndSwapInt(Object o, long offset, int expected, int x);

  /**
   * Atomically update Java variable to <tt>x</tt> if it is currently holding <tt>expected</tt>.
   * 
   * @return <tt>true</tt> if successful
   */
  boolean compareAndSwapLong(Object o, long offset, long expected, long x);

  /**
   * Fetches a reference value from a given Java variable, with volatile load semantics. Otherwise
   * identical to {@link #getObject(Object, long)}
   */
  Object getObjectVolatile(Object o, long offset);

  /**
   * Stores a reference value into a given Java variable, with volatile store semantics. Otherwise
   * identical to {@link #putObject(Object, long, Object)}
   */
  void putObjectVolatile(Object o, long offset, Object x);

  /** Volatile version of {@link #getInt(Object, long)} */
  int getIntVolatile(Object o, long offset);

  /** Volatile version of {@link #putInt(Object, long, int)} */
  void putIntVolatile(Object o, long offset, int x);

  /** Volatile version of {@link #getBoolean(Object, long)} */
  boolean getBooleanVolatile(Object o, long offset);

  /** Volatile version of {@link #putBoolean(Object, long, boolean)} */
  void putBooleanVolatile(Object o, long offset, boolean x);

  /** Volatile version of {@link #getByte(Object, long)} */
  byte getByteVolatile(Object o, long offset);

  /** Volatile version of {@link #putByte(Object, long, byte)} */
  void putByteVolatile(Object o, long offset, byte x);

  /** Volatile version of {@link #getShort(Object, long)} */
  short getShortVolatile(Object o, long offset);

  /** Volatile version of {@link #putShort(Object, long, short)} */
  void putShortVolatile(Object o, long offset, short x);

  /** Volatile version of {@link #getChar(Object, long)} */
  char getCharVolatile(Object o, long offset);

  /** Volatile version of {@link #putChar(Object, long, char)} */
  void putCharVolatile(Object o, long offset, char x);

  /** Volatile version of {@link #getLong(Object, long)} */
  long getLongVolatile(Object o, long offset);

  /** Volatile version of {@link #putLong(Object, long, long)} */
  void putLongVolatile(Object o, long offset, long x);

  /** Volatile version of {@link #getFloat(Object, long)} */
  float getFloatVolatile(Object o, long offset);

  /** Volatile version of {@link #putFloat(Object, long, float)} */
  void putFloatVolatile(Object o, long offset, float x);

  /** Volatile version of {@link #getDouble(Object, long)} */
  double getDoubleVolatile(Object o, long offset);

  /** Volatile version of {@link #putDouble(Object, long, double)} */
  void putDoubleVolatile(Object o, long offset, double x);

  /**
   * Version of {@link #putObjectVolatile(Object, long, Object)} that does not guarantee immediate
   * visibility of the store to other threads. This method is generally only useful if the
   * underlying field is a Java volatile (or if an array cell, one that is otherwise only accessed
   * using volatile accesses).
   */
  void putOrderedObject(Object o, long offset, Object x);

  /** Ordered/Lazy version of {@link #putIntVolatile(Object, long, int)} */
  void putOrderedInt(Object o, long offset, int x);

  /** Ordered/Lazy version of {@link #putLongVolatile(Object, long, long)} */
  void putOrderedLong(Object o, long offset, long x);

  /**
   * Atomically adds the given value to the current value of a field or array element within the
   * given object <code>o</code> at the given <code>offset</code>.
   *
   * @param o object/array to update the field/element in
   * @param offset field/element offset
   * @param delta the value to add
   * @return the previous value
   * @since 1.8
   */
  default int getAndAddInt(Object o, long offset, int delta) {
    int v;
    do {
      v = getIntVolatile(o, offset);
    } while (!compareAndSwapInt(o, offset, v, v + delta));
    return v;
  }

  /**
   * Atomically adds the given value to the current value of a field or array element within the
   * given object <code>o</code> at the given <code>offset</code>.
   *
   * @param o object/array to update the field/element in
   * @param offset field/element offset
   * @param delta the value to add
   * @return the previous value
   * @since 1.8
   */
  default long getAndAddLong(Object o, long offset, long delta) {
    long v;
    do {
      v = getLongVolatile(o, offset);
    } while (!compareAndSwapLong(o, offset, v, v + delta));
    return v;
  }

  /**
   * Atomically exchanges the given value with the current value of a field or array element within
   * the given object <code>o</code> at the given <code>offset</code>.
   *
   * @param o object/array to update the field/element in
   * @param offset field/element offset
   * @param newValue new value
   * @return the previous value
   * @since 1.8
   */
  default int getAndSetInt(Object o, long offset, int newValue) {
    int v;
    do {
      v = getIntVolatile(o, offset);
    } while (!compareAndSwapInt(o, offset, v, newValue));
    return v;
  }

  /**
   * Atomically exchanges the given value with the current value of a field or array element within
   * the given object <code>o</code> at the given <code>offset</code>.
   *
   * @param o object/array to update the field/element in
   * @param offset field/element offset
   * @param newValue new value
   * @return the previous value
   * @since 1.8
   */
  default long getAndSetLong(Object o, long offset, long newValue) {
    long v;
    do {
      v = getLongVolatile(o, offset);
    } while (!compareAndSwapLong(o, offset, v, newValue));
    return v;
  }

  /**
   * Atomically exchanges the given reference value with the current reference value of a field or
   * array element within the given object <code>o</code> at the given <code>offset</code>.
   *
   * @param o object/array to update the field/element in
   * @param offset field/element offset
   * @param newValue new value
   * @return the previous value
   * @since 1.8
   */
  default Object getAndSetObject(Object o, long offset, Object newValue) {
    Object v;
    do {
      v = getObjectVolatile(o, offset);
    } while (!compareAndSwapObject(o, offset, v, newValue));
    return v;
  }


  /**
   * Ensures lack of reordering of loads before the fence with loads or stores after the fence.
   * 
   * @since 1.8
   */
  void loadFence();

  /**
   * Ensures lack of reordering of stores before the fence with loads or stores after the fence.
   * 
   * @since 1.8
   */
  void storeFence();

  /**
   * Ensures lack of reordering of loads or stores before the fence with loads or stores after the
   * fence.
   * 
   * @since 1.8
   */
  void fullFence();

}
