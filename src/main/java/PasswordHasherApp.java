
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordHasherApp {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String filePath = "src/main/resources/best110.txt";
        List<String> words = readWordsFromFile(filePath); // Pfade können an deine Projektstruktur angepasst werden

        List<String> hashedWords = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        System.out.print("Geben Sie den Hashvalue ein: ");
        String targetHash = scanner.nextLine();

        //String targetHash = "340d600392818df2413382dc7d8325c360d83ea49a262d31760348484bbc10b5";
        String foundWord = null;

        for (String word : words) {
            //System.out.println("Wort: " + word);

            String md5Hash = MD5Hasher.hashWithMD5(word);
            if (md5Hash.equals(targetHash)) {
                foundWord = word;
                break;
            }

            String sha256Hash = SHA256Hasher.hashWithSHA256(word);
            if (sha256Hash.equals(targetHash)) {
                foundWord = word;
                break;
            }

            String sha256SaltedHash = SHA256WithSaltHasher.hashWithSHA256AndSalt(word);
            if (sha256SaltedHash.equals(targetHash)) {
                foundWord = word;
                break;
            }

            String bcryptHash = BcryptHasher.hashWithBcrypt(word);
            if (bcryptHash.equals(targetHash)) {
                foundWord = word;
                break;
            }
        }

        if (foundWord != null) {
            System.out.println("Gefundenes Wort für den Hash: " + foundWord);
        } else {
            System.out.println("Hash nicht gefunden.");
        }
    }

        // Jetzt enthält "hashedWords" alle gehashten Werte für die Wörter aus der Datei.


    private static List<String> readWordsFromFile(String filePath) throws IOException {
        List<String> words = Files.readAllLines(Paths.get(filePath));
        return words;
    }
}

/*public class PasswordHasherApp {


    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Geben Sie das Passwort ein: ");
        String password = scanner.nextLine();

        System.out.print("Wählen Sie den Hash-Algorithmus (MD5, SHA256, SHA256 mit Salt, BCRYPT): ");
        String algorithm = scanner.nextLine();

        switch (algorithm) {
            case "MD5":
                String md5Hash = MD5Hasher.hashWithMD5(password);
                System.out.println("MD5-Hash: " + md5Hash);
                break;
            case "SHA256":
                String sha256Hash = SHA256Hasher.hashWithSHA256(password);
                System.out.println("SHA-256-Hash: " + sha256Hash);
                break;
            case "SHA256 mit Salt":
                String sha256SaltedHash = SHA256WithSaltHasher.hashWithSHA256AndSalt(password);
                System.out.println("SHA-256-Hash mit Salt: " + sha256SaltedHash);
                break;
            case "BCRYPT":
                String bcryptHash = BcryptHasher.hashWithBcrypt(password);
                System.out.println("BCrypt-Hash: " + bcryptHash);
                break;
            default:
                System.out.println("Ungültiger Hash-Algorithmus ausgewählt.");
        }
    }*/


