package MisEspaciosApi;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordChecker {
    public static void main(String[] args) {
        String[] hashes = {
                "$2a$10$UYwobJvayI1C9rvb8gkLCOvVM.26l5Q/ClMKcCpO9oCfPmdsCzp8K", // password123
                "$2a$10$/5R8JFkH3HGkEbrq7r8ITOT4GNW5zgcg0vZt9Q85SIIITTXSy/vpm", // prueba1
                "$2a$10$P9sCrltcO95yABZtQkSWNu8cUa8DCmUmUSO9D/klNnqTNKv7p1tRa"  // prueba2
        };

        String[] passwords = {"password123", "prueba1", "prueba2"};

        for (int i = 0; i < hashes.length; i++) {
            boolean isMatch = BCrypt.checkpw(passwords[i], hashes[i]);
            System.out.println("Contraseña " + (i+1) + ": " + (isMatch ? "✅ Correcta" : "❌ Incorrecta"));
        }
    }
}