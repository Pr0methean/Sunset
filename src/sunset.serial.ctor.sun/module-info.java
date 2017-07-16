import sunset.serial.ctor.ConstructorMunger;
import sunset.serial.ctor.sun.SunConstructorMunger;

module sunset.serial.ctor.sun {
  requires jdk.unsupported;
  requires sunset.serial.ctor;
  provides ConstructorMunger with SunConstructorMunger;
}
