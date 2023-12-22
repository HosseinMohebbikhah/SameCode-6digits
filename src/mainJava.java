import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

public class Main {

  public static final int changeEverySeconds = 10;

  public static void main(String[] args) {
    String key = "Hi";
    System.out.println("Code : " + generateCode(key));
  }

  public static int generateCode(String key) {
    long currentTime = Instant.now().getEpochSecond();
    long timestampAtMinuteStart =
      currentTime - (currentTime % changeEverySeconds);

    String hashInput = (key + timestampAtMinuteStart) + timestampAtMinuteStart;
    byte[] hashBytes;

    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      hashBytes = digest.digest(hashInput.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      return 0;
    }

    int reversedInt =
      (hashBytes[0] & 0xFF) << 24 |
      (hashBytes[1] & 0xFF) << 16 |
      (hashBytes[2] & 0xFF) << 8 |
      (hashBytes[3] & 0xFF);

    int code = Math.abs(reversedInt) % 1000000;

    return Integer.valueOf(String.format("%06d", code));
  }
}
