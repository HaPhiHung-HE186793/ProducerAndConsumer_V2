package model;

public class Message {
    private final String message;
    private final long timestamp;
    public Message(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
    public String getMessage() {
        return message;
    }
    public long getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString() {
        return "Message{" + "message=" + message + ", timestamp=" + timestamp + '}';
    }
}
