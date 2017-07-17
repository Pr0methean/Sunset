package sunset.sneakythrow.sun;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Created by chris on 7/16/2017.
 */
class SunSneakyThrowerTest {

  @Test
  void sneakyThrow() {
    assertThrows(IOException.class, () -> {
      new SunSneakyThrower().sneakyThrow(new IOException());
    });
  }

}