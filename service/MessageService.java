package uz.pdp.online.lesson10.task2.service;

import uz.pdp.online.lesson10.task2.db.MessagesDatabase;
import uz.pdp.online.lesson10.task2.entity.Message;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static uz.pdp.online.lesson10.task2.service.UserService.*;
import static uz.pdp.online.lesson10.task2.utils.AppUtils.getConsoleAsString;
import static uz.pdp.online.lesson10.task2.utils.AppUtils.println;
import static uz.pdp.online.lesson10.task2.validators.UserInfoValidator.isValidEmail;

public class MessageService {
    public static void writeNewMessage() {
        String toUserEmail = getConsoleAsString("Enter the receiver's email: ");

        while (!isValidEmail(toUserEmail)) {
            println("Invalid user email. Please try again!");
            toUserEmail = getConsoleAsString("Enter the receiver's email: ");
        }

        String message = getConsoleAsString("Write the message: ");
        LocalDateTime messageTime = LocalDateTime.now();

        long fromUserId = currentUser.getId();
        long toUserId = getUserIdByEmail(toUserEmail);

        Message newMessage = new Message(Message.getMessageOrder(), message, messageTime, fromUserId, toUserId);

        saveMessageToFile(newMessage);
        saveMessageToMessagesDatabase(newMessage);
    }

    private static void saveMessageToFile(Message message) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/uz/pdp/online/lesson10/task2/files/messages.txt", true);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException("Error occurred while saving a new message to the file", e);
        }

        println("\nMessage is sent successfully :))\n");
    }

    private static void saveMessageToMessagesDatabase(Message newMessage) {
        MessagesDatabase.messages.add(newMessage);
    }


    public static void viewAllMessages() {
        File file = new File("src/main/java/uz/pdp/online/lesson10/task2/files/messages.txt");
        List<Message> messageList = getMessagesFromFile();

        long currentUserId = currentUser.getId();
        for (Message message : messageList) {
            if (message.getFromUserId() == currentUserId) {
                println("SENT ==> " + getFormattedForSentMessages(message));
            } else {
                println("RECEIVED ==> " + getFormattedForReceivedMessages(message));
            }

            // SENT ==> Message{id=0, text='Hello, a', messageTime=2023-10-27T17:21:13.428413700, fromUserId=1, toUserId=0}
            // RECEIVED ==> Message{id=1, text='Hello, AAAAA', messageTime=2023-10-27T17:22:09.506644700, fromUserId=2, toUserId=1}
        }
    }

    private static String getFormattedForSentMessages(Message message) {
        String text = message.getText();
        String toUserName = getUsernameById(message.getToUserId());
        LocalDateTime time = message.getMessageTime();
        String messageTimeString = String.format("%d/%d/%d %d:%d", time.getYear(), time.getMonthValue(), time.getDayOfMonth(), time.getHour(), time.getMinute());
        return String.format("TIME: %s - TO: %s ==> MESSAGE: %s", messageTimeString, toUserName, text);
    }

    private static String getFormattedForReceivedMessages(Message message) {
        String text = message.getText();
        String fromUserName = getUsernameById(message.getFromUserId());
        LocalDateTime time = message.getMessageTime();
        String messageTimeString = String.format("%d/%d/%d %d:%d", time.getYear(), time.getMonthValue(), time.getDayOfMonth(), time.getHour(), time.getMinute());
        return String.format("TIME: %s - FROM: %s ==> MESSAGE: %s", messageTimeString, fromUserName, text);
    }

    private static List<Message> getMessagesFromFile() {
        List<Message> messages = new ArrayList<>();
        long currentUserId = currentUser.getId();
        for (Message message : MessagesDatabase.messages) {
            if (message.getToUserId() == currentUserId || message.getFromUserId() == currentUserId) {
                messages.add(message);
            }
        }
        return messages;
    }

}
