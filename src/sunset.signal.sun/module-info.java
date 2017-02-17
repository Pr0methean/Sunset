module sunset.signal.sun {
  requires jdk.unsupported;
  requires sunset.signal;
  provides sunset.signal.CSignalManager with sunset.signal.sun.SunCSignalManager;
}
