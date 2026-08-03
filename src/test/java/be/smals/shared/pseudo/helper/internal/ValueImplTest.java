package be.smals.shared.pseudo.helper.internal;

import static be.smals.shared.pseudo.helper.internal.TestUtils.createTestDomain;

import be.smals.shared.pseudo.helper.Domain;
import java.util.concurrent.ExecutionException;
import javax.crypto.spec.SecretKeySpec;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValueImplTest {

  private static final Domain domain = createTestDomain("test", 8, new SecretKeySpec(new byte[32], "AES"), true);

  @Test
  public void test_pseudonymize_and_identify() throws ExecutionException, InterruptedException {
    final var value = domain.valueFactory().from("test");
    final var pseudonymInTransit = value.pseudonymize().get();
    final var identified = pseudonymInTransit.identify();
    Assertions.assertEquals("test", identified.get().asString());
  }
}
