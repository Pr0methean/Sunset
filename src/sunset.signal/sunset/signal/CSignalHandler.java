package sunset.signal;

/**
 * Callback interface for a signal handler that can be registered with a {@link CSignalService}.
 */
public interface CSignalHandler {

  void handle(SignalInfo signal);
}
