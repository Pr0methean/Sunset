import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import sun.misc.Signal;
import sun.misc.SignalHandler;
import sunset.signal.CSignalHandler;
import sunset.signal.CSignalManager;
import sunset.signal.SignalInfo;

/**
 * An implementation of {@link CSignalManager} using {@link Signal} and {@link SignalHandler}.
 */
public class SunCSignalManager implements CSignalManager {
  private static final Map<String, SignalInfo> cachedSignalInfos =
      new ConcurrentHashMap<String, SignalInfo>();
  private static final CSignalHandler SIG_DFL = new SignalHandlerWrapper(SignalHandler.SIG_DFL);
  private static final CSignalHandler SIG_IGN = new SignalHandlerWrapper(SignalHandler.SIG_IGN);

  @Override
  public SignalInfo lookupSignal(String name) {
    SignalInfo result = cachedSignalInfos.get(name);
    if (result == null) {
      Signal wrapped = new Signal(name);
      result = new SignalInfo(wrapped.getName(), wrapped.getNumber());
      cachedSignalInfos.put(name, result);
    }
    return result;
  }

  @Override
  public CSignalHandler getSystemDefaultHandler() {
    return SIG_DFL;
  }

  @Override
  public CSignalHandler getSystemNoOpHandler() {
    return SIG_IGN;
  }

  @Override
  public CSignalHandler handle(SignalInfo signal, CSignalHandler newHandler) {
    if (newHandler instanceof SignalHandlerWrapper) {
      return handleInternal(signal, ((SignalHandlerWrapper) newHandler).wrapped);
    } else {
      return handleInternal(signal, new SignalHandler() {
        @Override
        public void handle(Signal sunSignal) {
          newHandler.handle(lookupSignal(sunSignal.getName()));
        }
      });
    }
  }

  /**
   * Unwraps the signal, calls {@link Signal#handle(Signal, SignalHandler)}, and wraps the old
   * handler.
   */
  private CSignalHandler handleInternal(SignalInfo signal, SignalHandler newHandler) {
    return new SignalHandlerWrapper(Signal.handle(new Signal(signal.getName()), newHandler));
  }
}
