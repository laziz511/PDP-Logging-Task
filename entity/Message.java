package uz.pdp.online.lesson10.task2.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable {
    public static long messageOrder = 0;
    private long id;
    private String text;
    private LocalDateTime messageTime;
    private long fromUserId;
    private long toUserId;

    public Message() {
    }

    public Message(long id, String text, LocalDateTime messageTime, long fromUserId, long toUserId) {
        this.id = id;
        this.text = text;
        this.messageTime = messageTime;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public long getToUserId() {
        return toUserId;
    }

    public void setToUserId(long toUserId) {
        this.toUserId = toUserId;
    }

    public static long getMessageOrder() {
        return messageOrder++;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", messageTime=" + messageTime +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                '}';
    }
}
