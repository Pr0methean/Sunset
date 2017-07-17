package sunset.signal;

/**
 * Object representing one of the C or POSIX signals, or a nonstandard signal defined by the current
 * operating system.
 */
public final class SignalInfo {
  private final int number;
  private final String name;

  /**
   * @param name The name of this signal as defined in the C header {@code <signal.h>}, excluding
   *        the "SIG" prefix.
   * @param number The code number that represents this signal in system calls.
   */
  public SignalInfo(String name, int number) {
    this.name = name;
    this.number = number;
  }

  /**
   * @return The name of this signal as defined in the C header {@code <signal.h>}, excluding the
   *         "SIG" prefix.
   */
  public String getName() {
    return name;
  }

  /**
   * @return The code number that represents this signal in system calls.
   */
  public int getNumber() {
    return number;
  }

  public String toString() {
    return "SIG" + name;
  }
}
