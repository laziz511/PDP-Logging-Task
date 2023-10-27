package uz.pdp.online.lesson10.task2;

import static uz.pdp.online.lesson10.task2.utils.AppUtils.*;

public class App {

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        printWelcomeMessage();
        while (true) {
            printMenu();
            applyOperationInAppMenu();
            printUserMenu();
            applyOperationInUserMenu();
        }
    }
}
