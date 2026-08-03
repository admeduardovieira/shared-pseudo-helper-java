package be.smals.shared.pseudo.helper.internal;

import static com.nimbusds.jose.EncryptionMethod.A256GCM;

import java.time.Duration;
import java.util.Map;
import javax.crypto.SecretKey;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.jce.ECNamedCurveTable;

public class TestUtils {

  public static DomainImpl createTestDomain(final String key, final int bufferSize) {
    return createTestDomain(key, bufferSize, null, false);
  }

  public static DomainImpl createTestDomain(final String key, final int bufferSize, final SecretKey secretKey, final boolean isPseudoUnaware) {
    return new DomainImpl(key, "P-521",
                          ECNamedCurveTable.getParameterSpec("P-521").getCurve(),
                          null,
                          bufferSize,
                          secretKey == null ? null : Map.of("default", secretKey),
                          secretKey == null ? null : "default",
                          A256GCM,
                          Duration.ofMinutes(10),
                          null,
                          isPseudoUnaware,
                          CryptoServicesRegistrar.getSecureRandom());
  }
}
