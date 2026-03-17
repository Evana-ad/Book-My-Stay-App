import java.util.*;

class Room {

    int roomNumber;
    String type;
    boolean available;

    Room(int roomNumber, String type, boolean available) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.available = available;
    }

    void displayRoom() {
        System.out.println("Room No: " + roomNumber +
                " | Type: " + type +
                " | Available: " + available);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Room r1 = new Room(101, "AC", true);
        Room r2 = new Room(102, "NON-AC", false);
        Room r3 = new Room(103, "DELUXE", true);

        r1.displayRoom();
        r2.displayRoom();
        r3.displayRoom();

    }
}