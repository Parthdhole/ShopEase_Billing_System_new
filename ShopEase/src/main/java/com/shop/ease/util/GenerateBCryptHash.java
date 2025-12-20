package com.shop.ease.util;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Scanner;

public class GenerateBCryptHash {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password to hash: ");
        String rawPassword = scanner.nextLine();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        // Generate hash
        String hash = encoder.encode(rawPassword);
        System.out.println("BCrypt hash for DB: " + hash);

        // Optional: verify match
        System.out.print("Enter password again to verify: ");
        String verifyPassword = scanner.nextLine();
        boolean matches = encoder.matches(verifyPassword, hash);
        System.out.println("Password matches hash? " + matches);

        scanner.close();
    }
}
