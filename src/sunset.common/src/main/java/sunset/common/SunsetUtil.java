package sunset.common;

import java.util.Optional;
import java.util.ServiceLoader;

public class SunsetUtil {
  public static <T> T loadService(Class<T> serviceClass) {
    Optional<T> service = ServiceLoader.load(serviceClass).findFirst();
    if (service.isPresent()) {
      return service.get();
    } else {
      throw new RuntimeException("No implementation of CSignalService installed");
    }
  }
}
