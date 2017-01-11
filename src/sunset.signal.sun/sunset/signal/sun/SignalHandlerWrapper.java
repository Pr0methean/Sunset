package sunset.signal.sun;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import sunset.signal.CSignalHandler;
import sunset.signal.SignalInfo;

public final class SignalHandlerWrapper implements CSignalHandler {

  public SignalHandlerWrapper(SignalHandler wrapped) {
    this.wrapped = wrapped;
  }

  /* package-local */ final SignalHandler wrapped;

  @Override
  public void handle(SignalInfo signal) {
    wrapped.handle(new Signal(signal.getName()));
  }

  @Override
  public int hashCode() {
    return wrapped.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    return obj instanceof SignalHandlerWrapper && wrapped == ((SignalHandlerWrapper) obj).wrapped;
  }
}
