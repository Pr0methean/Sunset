package sunset.signal;

import sunset.common.SunsetUtil;

/**
 * A service that allows registration of C/POSIX signal handlers.
 */
public interface CSignalService {

  static CSignalService getInstance() {
    return SunsetUtil.loadService(CSignalService.class);
  }

  /**
   * Looks up the signal with the given name.
   * 
   * @param name The name of this signal as defined in the C header {@code <signal.h>}, excluding
   *        the "SIG" prefix.
   */
  SignalInfo lookupSignal(String name);

  /**
   * @return A signal handler that has the operating-system-defined default behavior for each
   *         signal. Where possible, this is implemented by falling through to {@code SIG_DFL}
   *         defined in the C header {@code <signal.h>}.
   */
  CSignalHandler lookupDefaultHandler();

  /**
   * @return A signal handler that ignores all signals. Where possible, this is implemented by
   *         falling through to {@code SIG_IGN} defined in the C header {@code <signal.h>}.
   */
  CSignalHandler lookupNoOpHandler();

  /**
   * Registers a signal handler.
   *
   * @param signal The signal to handle.
   * @param newHandler The {@link CSignalHandler} that will handle that signal.
   * @return The {@link CSignalHandler} that previously handled that signal.
   */
  CSignalHandler handle(SignalInfo signal, CSignalHandler newHandler);
}
