package com.prog5121.poe;

import java.util.Scanner;

/**
 * Main.java
 *
 * Console entry point for the Part 1 Registration and Login feature.
 * Accepts input from the user, registers them, and then allows them to
 * attempt a login, printing the appropriate output messages at each step.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();

        System.out.println("=== Chat App: Registration ===");

        System.out.print("Enter a first name: ");
        login.setFirstName(scanner.nextLine());

        System.out.print("Enter a last name: ");
        login.setLastName(scanner.nextLine());

        System.out.print("Enter a username (must contain '_' and be <= 5 characters): ");
        login.setUsername(scanner.nextLine());

        System.out.print("Enter a password (8+ chars, capital, number, special char): ");
        login.setPassword(scanner.nextLine());

        System.out.print("Enter a South African cell number (e.g. +27838968976): ");
        login.setCellPhoneNumber(scanner.nextLine());

        String registrationMessage = login.registerUser();
        System.out.println(registrationMessage);

        if (registrationMessage.equals("User successfully registered.")) {
            System.out.println("\n=== Chat App: Login ===");

            System.out.print("Enter your username: ");
            String loginUsername = scanner.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = scanner.nextLine();

            boolean loginSuccessful = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loginSuccessful));
        }

        scanner.close();
    }
}
