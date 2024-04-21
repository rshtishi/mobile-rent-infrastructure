package com.github.rshtishi;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneManager phoneManager = new PhoneManager();
        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Display available phones");
            System.out.println("2. Book a phone");
            System.out.println("3. Return a phone");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            Scanner input = new Scanner(System.in);

            switch (input.nextLine()) {
                case "1":
                    phoneManager.displayPhones();
                    break;
                case "2":
                    System.out.print("Enter phone id: ");
                    String phoneId = scanner.nextLine();
                    phoneManager.selectPhone(phoneId);
                    System.out.print("Enter your name: ");
                    String name = scanner.nextLine();
                    phoneManager.bookPhone(new Customer(name));
                    break;
                case "3":
                    System.out.print("Enter phone id: ");
                    phoneId = scanner.nextLine();
                    phoneManager.selectPhone(phoneId);
                    System.out.print("Enter your name: ");
                    name = scanner.nextLine();
                    phoneManager.returnPhone(new Customer(name));
                    break;
                case "4":
                    System.exit(0);
                    break;
            }
        }
    }
}
