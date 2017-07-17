package sunset.sneakythrow.oldjdk;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import org.junit.jupiter.api.Test;

/**
 * Created by chris on 7/16/2017.
 */
class ErasureSneakyThrowerTest {

  @Test
  void sneakyThrow() {
    assertThrows(IOException.class, () -> {
      new ErasureSneakyThrower().sneakyThrow(new IOException());
    });
  }

}