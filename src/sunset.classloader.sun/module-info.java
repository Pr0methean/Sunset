module sunset.classloader.sun {
  requires jdk.unsupported;
  requires sunset.common.sun;
  requires sunset.classloader;
  provides sunset.classloader.UnsafeClassLoader with sunset.classloader.sun.SunUnsafeClassLoader;
}