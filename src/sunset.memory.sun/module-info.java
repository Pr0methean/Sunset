module sunset.memory.sun {
  requires jdk.unsupported;
  requires sunset.common.sun;
  requires sunset.memory;
  provides sunset.memory.LowLevelMemoryAccessor with sunset.memory.sun.SunLowLevelMemoryAccessor;
}
