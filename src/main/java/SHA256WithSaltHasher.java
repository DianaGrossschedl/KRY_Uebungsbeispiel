import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256WithSaltHasher {
    public static String hashWithSHA256AndSalt(String input) throws NoSuchAlgorithmException {
        // Zufällig generieren Sie ein Salt
        byte[] salt = generateSalt();

        // Das Salt dem Eingabestring hinzufügen
        String saltedInput = input + bytesToHex(salt);

        // Hashing mit SHA-256
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = md.digest(saltedInput.getBytes());

        // Das Ergebnis enthält sowohl den Hash als auch das Salt
        String hashedPassword = bytesToHex(hashBytes) + bytesToHex(salt);

        return hashedPassword;
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[16]; // 16 Bytes entsprechen 128 Bit
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}