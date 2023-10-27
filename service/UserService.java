package uz.pdp.online.lesson10.task2.service;


import uz.pdp.online.lesson10.task2.App;
import uz.pdp.online.lesson10.task2.db.UsersDatabase;
import uz.pdp.online.lesson10.task2.entity.User;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import static uz.pdp.online.lesson10.task2.utils.AppUtils.getConsoleAsString;
import static uz.pdp.online.lesson10.task2.utils.AppUtils.println;
import static uz.pdp.online.lesson10.task2.validators.UserInfoValidator.isValidEmail;
import static uz.pdp.online.lesson10.task2.validators.UserInfoValidator.isValidName;

public class UserService {
    public static long userRegistrationOrder = 1;
    private static final Logger logger = Logger.getLogger("MyLogger");
    public static User currentUser;

    static {
        try {
            LogManager.getLogManager().readConfiguration(App.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Exception happened while reading .properties file", e);
        }
    }


    public static void register() {
        while (true) {
            String name = getConsoleAsString("Enter your name: ");
            String email = getConsoleAsString("Enter your email: ");
            if (isValidName(name) && isValidEmail(email)) {
                saveUser(name, email);
                break;
            }
            println("Invalid name or email. Please try again!");
        }
    }

    private static void saveUser(String name, String email) {
        long userId = userRegistrationOrder++;
        LocalDateTime registerTime = LocalDateTime.now();

        User newUser = new User(userId, name, email, registerTime);
        currentUser = newUser;
        logUserToConsole(newUser);
        saveUserToFile(newUser);
        saveUserToDatabaseClass(newUser);
    }

    private static void logUserToConsole(User newUser) {
        logger.log(new LogRecord(Level.INFO, String.format("Successful registration: %s", newUser)));
    }

    private static void saveUserToFile(User newUser) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/uz/pdp/online/lesson10/task2/files/users.txt", true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(newUser);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while saving new user to file", e);
        }

        println("\nYou are registered successfully :))\n");
    }

    private static void saveUserToDatabaseClass(User newUser) {
        UsersDatabase.users.add(newUser);
    }

    public static void login() {
        while (true) {
            String email = getConsoleAsString("Enter your email to login: ");

            if (!isValidEmail(email)) {
                println("Not valid email address. Please try again!");
                continue;
            }

            List<User> users = UsersDatabase.users;
            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    println("Login successful. Welcome, " + user.getName() + "!");
                    currentUser = user;
                    return;
                }
            }
            println("User with the provided email not found.");
        }
    }

    public static long getUserIdByEmail(String email) {
        List<User> users = UsersDatabase.users;
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getId();
            }
        }
        return 0;
    }

    public static String getUsernameById(long id) {
        List<User> users = UsersDatabase.users;
        for (User user : users) {
            if (user.getId() ==id ) {
                return user.getName();
            }
        }
        return "";
    }
}
