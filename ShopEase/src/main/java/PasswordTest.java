

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String rawPassword = "Parth@983480";
        String dbHash = "$2a$10$6ntByM30E2iRAPUUKZ5fJe7S08TWv7p3ZiBCKR7ThQ.Yhp7T6WHIO";

        boolean matches = encoder.matches(rawPassword, dbHash);
        System.out.println("Password matches DB hash? " + matches);

        // Optional: generate a new hash for your DB
        String newHash = encoder.encode(rawPassword);
        System.out.println("New hash for DB: " + newHash);
    }
}
