module sunset.sneakythrow.sun {
  requires jdk.unsupported;
  requires sunset.common.sun;
  requires sunset.sneakythrow;
  provides sunset.sneakythrow.SneakyThrower with sunset.sneakythrow.sun.SunSneakyThrower;
}
