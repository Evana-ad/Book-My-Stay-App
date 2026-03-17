import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 4 – Room Search & Availability Check
 * Read-only search using centralized inventory
 * @version 4.1
 */

abstract class Room {

    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Price     : " + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 2000);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 3500);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 6000);
    }
}

class RoomInventory {

    private Map<String, Integer> availability;

    RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 0);   // example unavailable
    }

    int getAvailability(String type) {
        return availability.get(type);
    }
}

class RoomSearchService {

    private RoomInventory inventory;

    RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void searchAvailableRooms(Room[] rooms) {

        System.out.println("===== Available Rooms v4.1 =====");

        for (Room room : rooms) {

            int count = inventory.getAvailability(room.type);

            if (count > 0) {
                room.displayDetails();
                System.out.println("Available : " + count);
                System.out.println();
            }
        }
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Room[] rooms = {
                new SingleRoom(),
                new DoubleRoom(),
                new SuiteRoom()
        };

        RoomSearchService search = new RoomSearchService(inventory);

        search.searchAvailableRooms(rooms);
    }
}