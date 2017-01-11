module sunset.sneakythrow.oldjdk {
  requires sunset.sneakythrow;
  provides sunset.sneakythrow.SneakyThrower with sunset.sneakythrow.oldjdk.ThreadStopSneakyThrower;
}
