module sunset.serial.ctor.sun {
  requires jdk.unsupported;
  requires sunset.serial.ctor;
  provides sunset.serial.ctor.SerializationConstructorFactory with sunset.serial.ctor.sun.SunSerializationConstructorFactory;
}
