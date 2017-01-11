module sunset.signal.sun {
  requires jdk.unsupported;
  requires sunset.signal;
  provides sunset.signal.CSignalService with sunset.signal.sun.SunCSignalService;
}