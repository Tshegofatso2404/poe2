package poe.pkg2;

import java.util.Scanner;

public class Message {
    private static final int BASE_ID = 1000000000;
    private static final int MAX_MESSAGE_LENGTH = 50;
    
    private int recipientNumber;
    private String text;
    private String status;

    // Validate message length
    public boolean isContentValid() {
        if (text == null) {
            System.out.println("Message cannot be null");
            return false;
        }
        
        boolean isValid = text.length() <= MAX_MESSAGE_LENGTH;
        System.out.println(isValid ? "Message accepted" : "Message too long (max " + MAX_MESSAGE_LENGTH + " chars)");
        return isValid;
    }

    // Generate message hash
    public String generateHash() {
        if (text == null || text.isEmpty()) {
            return "INVALID_MESSAGE";
        }

        String firstWord = text.split(" ")[0];
        String lastWord = text.substring(text.lastIndexOf(" ") + 1);
        
        return String.format("%02d:%d:%s%s",
            Integer.parseInt(Integer.toString(BASE_ID).substring(0, 2)),
            recipientNumber,
            firstWord,
            lastWord).toUpperCase();
    }

    public static void sendMessages(Scanner scan) {
        System.out.print("How many messages to send? ");
        int count = scan.nextInt();
        scan.nextLine(); // Clear the newline
        
        Message[] messages = new Message[count];
        
        for (int i = 0; i < count; i++) {
            System.out.println("\nMessage #" + (i+1));
            Message msg = new Message();
            
            // Get recipient number
            boolean validRecipient = false;
            while (!validRecipient) {
                System.out.print("Enter recipient number (9 digits): +27");
                msg.setRecipientNumber(scan.nextInt());
                scan.nextLine(); // Clear buffer
                validRecipient = true; // Add proper validation if needed
            }
            
            // Get message text
            boolean validMessage = false;
            while (!validMessage) {
                System.out.print("Enter message (max 50 chars): ");
                String text = scan.nextLine();
                msg.setText(text);
                validMessage = msg.isContentValid();
            }
            
            // Get message status
            System.out.println("Choose status:\n1) Send\n2) Save\n3) Discard");
            int choice = scan.nextInt();
            scan.nextLine();
            int option = 0;
            switch (option) {
                case 1:
                   
                    msg.setStatus("SENT");
                case 2:
                     msg.setStatus("SAVED");
                case 3:
                    msg.setStatus("DISCARDED");
                default:
                {
                    System.out.println("Invalid choice, defaulting to DISCARDED");
                    msg.setStatus("DISCARDED");
                }
            }
            
            messages[i] = msg;
            
            // Display message details
            System.out.println("\n--- Message Created ---");
            System.out.println("Recipient: +27" + msg.getRecipientNumber());
            System.out.println("Message: " + msg.getText());
            System.out.println("Status: " + msg.getStatus());
            System.out.println("Hash: " + msg.generateHash());
            System.out.println("----------------------\n");
        }
        
        System.out.println("Successfully processed " + count + " messages");
    }

    // Proper getters and setters
    public void setRecipientNumber(int recipientNumber) {
        this.recipientNumber = recipientNumber;
    }

    public int getRecipientNumber() {
        return recipientNumber;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}