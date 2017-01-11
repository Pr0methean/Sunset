package sunset.memory.sun;

import sun.misc.Unsafe;
import java.lang.reflect.Field;
import sunset.common.sun.SunsetSunUtil;
import sunset.memory.LowLevelMemoryAccessor;

public class SunLowLevelMemoryAccessor implements LowLevelMemoryAccessor {

  private static final Unsafe unsafe = SunsetSunUtil.getTheUnsafe();
  
  @Override
  public int getInt(Object o, long offset) {
    return unsafe.getInt(o, offset);
  }

  @Override
  public void putInt(Object o, long offset, int x) {
    unsafe.putInt(o, offset, x); 
  }

  @Override
  public Object getObject(Object o, long offset) {
    return unsafe.getObject(o, offset);
  }

  @Override
  public void putObject(Object o, long offset, Object x) {
    unsafe.putObject(o, offset, x);
  }

  @Override
  public boolean getBoolean(Object o, long offset) {
    return unsafe.getBoolean(o, offset);
  }

  @Override
  public void putBoolean(Object o, long offset, boolean x) {
    unsafe.putBoolean(o, offset, x);
  }

  @Override
  public byte getByte(Object o, long offset) {
    return unsafe.getByte(o, offset);
  }

  @Override
  public void putByte(Object o, long offset, byte x) {
    unsafe.putByte(o, offset, x); 
  }

  @Override
  public short getShort(Object o, long offset) {
    return unsafe.getShort(o, offset);
  }

  @Override
  public void putShort(Object o, long offset, short x) {
    unsafe.putShort(o, offset,  x);
  }

  @Override
  public char getChar(Object o, long offset) {
    return unsafe.getChar(o, offset);
  }

  @Override
  public void putChar(Object o, long offset, char x) {
    unsafe.putChar(o, offset, x);
  }

  @Override
  public long getLong(Object o, long offset) {
    return unsafe.getLong(o, offset);
  }

  @Override
  public void putLong(Object o, long offset, long x) {
    unsafe.putLong(o, offset, x);
  }

  @Override
  public float getFloat(Object o, long offset) {
    return unsafe.getFloat(o, offset);
  }

  @Override
  public void putFloat(Object o, long offset, float x) {
    unsafe.putFloat(o, offset, x);
  }

  @Override
  public double getDouble(Object o, long offset) {
    return unsafe.getDouble(o, offset);
  }

  @Override
  public void putDouble(Object o, long offset, double x) {
    unsafe.putDouble(o, offset, x);
  }

  @Override
  public long allocateMemory(long bytes) {
    return unsafe.allocateMemory(bytes);
  }

  @Override
  public long reallocateMemory(long address, long bytes) {
    return unsafe.reallocateMemory(address, bytes);
  }

  @Override
  public void setMemory(Object o, long offset, long bytes, byte value) {
    unsafe.setMemory(o, offset, bytes, value);
  }

  @Override
  public void copyMemory(Object srcBase, long srcOffset, Object destBase, long destOffset,
      long bytes) {
    unsafe.copyMemory(srcBase, srcOffset, destBase, destOffset, bytes);
  }

  @Override
  public void freeMemory(long address) {
    unsafe.freeMemory(address);
  }

  @Override
  public int arrayBaseOffset(Class<?> arrayClass) {
    return unsafe.arrayBaseOffset(arrayClass);
  }

  @Override
  public int arrayIndexScale(Class<?> arrayClass) {
    return unsafe.arrayIndexScale(arrayClass);
  }

  @Override
  public int addressSize() {
    return unsafe.addressSize();
  }

  @Override
  public int pageSize() {
    return unsafe.pageSize();
  }

  @Override
  public Object getObjectVolatile(Object o, long offset) {
    return unsafe.getObjectVolatile(o, offset);
  }

  @Override
  public void putObjectVolatile(Object o, long offset, Object x) {
    unsafe.putObjectVolatile(o, offset, x);
  }

  @Override
  public int getIntVolatile(Object o, long offset) {
    return unsafe.getIntVolatile(o, offset);
  }

  @Override
  public void putIntVolatile(Object o, long offset, int x) {
    unsafe.putIntVolatile(o, offset, x);
  }

  @Override
  public boolean getBooleanVolatile(Object o, long offset) {
    return unsafe.getBooleanVolatile(o, offset);
  }

  @Override
  public void putBooleanVolatile(Object o, long offset, boolean x) {
    unsafe.putBooleanVolatile(o, offset, x);
  }

  @Override
  public byte getByteVolatile(Object o, long offset) {
    return unsafe.getByteVolatile(o, offset);
  }

  @Override
  public void putByteVolatile(Object o, long offset, byte x) {
    unsafe.putByteVolatile(o, offset, x);
  }

  @Override
  public short getShortVolatile(Object o, long offset) {
    return unsafe.getShortVolatile(o, offset);
  }

  @Override
  public void putShortVolatile(Object o, long offset, short x) {
    unsafe.putShortVolatile(o, offset, x);
  }

  @Override
  public char getCharVolatile(Object o, long offset) {
    return unsafe.getCharVolatile(o, offset);
  }

  @Override
  public void putCharVolatile(Object o, long offset, char x) {
    unsafe.putCharVolatile(o, offset, x);
  }

  @Override
  public long getLongVolatile(Object o, long offset) {
    return unsafe.getLongVolatile(o, offset);
  }

  @Override
  public void putLongVolatile(Object o, long offset, long x) {
    unsafe.putLongVolatile(o, offset, x);
  }

  @Override
  public float getFloatVolatile(Object o, long offset) {
    return unsafe.getFloatVolatile(o, offset);
  }

  @Override
  public void putFloatVolatile(Object o, long offset, float x) {
    unsafe.putFloatVolatile(o, offset, x);
  }

  @Override
  public double getDoubleVolatile(Object o, long offset) {
    return unsafe.getDoubleVolatile(o, offset);
  }

  @Override
  public void putDoubleVolatile(Object o, long offset, double x) {
    unsafe.putDoubleVolatile(o, offset, x);
  }

  @Override
  public void putOrderedObject(Object o, long offset, Object x) {
    unsafe.putOrderedObject(o, offset, x);
  }

  @Override
  public void putOrderedInt(Object o, long offset, int x) {
    unsafe.putOrderedInt(o, offset, x);
  }

  @Override
  public void putOrderedLong(Object o, long offset, long x) {
    unsafe.putOrderedLong(o, offset, x);
  }

  @Override
  public void loadFence() {
    unsafe.loadFence();
  }

  @Override
  public void storeFence() {
    unsafe.storeFence();
  }

  @Override
  public void fullFence() {
    unsafe.fullFence();
  }

  @Override
  public long staticFieldOffset(Field f) {
    return unsafe.staticFieldOffset(f);
  }

  @Override
  public long objectFieldOffset(Field f) {
    return unsafe.objectFieldOffset(f);
  }

  @Override
  public Object staticFieldBase(Field f) {
    return unsafe.staticFieldBase(f);
  }

  @Override
  public boolean compareAndSwapObject(Object o, long offset, Object expected, Object x) {
    return unsafe.compareAndSwapObject(o, offset, expected, x);
  }

  @Override
  public boolean compareAndSwapInt(Object o, long offset, int expected, int x) {
    return unsafe.compareAndSwapInt(o, offset, expected, x);
  }

  @Override
  public boolean compareAndSwapLong(Object o, long offset, long expected, long x) {
    return unsafe.compareAndSwapLong(o, offset, expected, x);
  }

}
