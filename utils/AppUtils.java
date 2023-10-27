package uz.pdp.online.lesson10.task2.utils;

import uz.pdp.online.lesson10.task2.entity.User;

import java.util.Scanner;

import static uz.pdp.online.lesson10.task2.service.MessageService.viewAllMessages;
import static uz.pdp.online.lesson10.task2.service.MessageService.writeNewMessage;
import static uz.pdp.online.lesson10.task2.service.UserService.login;
import static uz.pdp.online.lesson10.task2.service.UserService.register;

public class AppUtils {
    private static final Scanner scannerAsInt = new Scanner(System.in);
    private static final Scanner scannerAsString = new Scanner(System.in);

    public static void printWelcomeMessage() {
        println("");
        println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        println("- - - - - - - - WELCOME TO OUR CHAT APP - - - - - - - -");
        println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        println("");
    }

    public static void printMenu() {
        println("1. Register");
        println("2. Login");
        println("3. Exit");
        print(">> ");
    }

    public static void applyOperationInAppMenu() {
        int operationToPerform = getConsoleAsInt();
        switch (operationToPerform) {
            case 1 -> register();
            case 2 -> login();
            case 3 -> exit();
        }
    }

    public static void printUserMenu() {
        println("1. Write a new message");
        println("2. View all messages");
        println("3. Go back");
        print(">> ");
    }

    public static void applyOperationInUserMenu() {
        int operationToPerform = getConsoleAsInt();
        switch (operationToPerform) {
            case 1 -> writeNewMessage();
            case 2 -> viewAllMessages();
        }
    }

    private static void exit() {
        System.exit(0);
    }


    public static void println(String hint) {
        System.out.println(hint);
    }

    public static void print(String hint) {
        System.out.print(hint);
    }

    public static String getConsoleAsString(String hint) {
        System.out.print(hint);
        return scannerAsString.nextLine();
    }

    private static int getConsoleAsInt() {
        return scannerAsInt.nextInt();
    }
}

