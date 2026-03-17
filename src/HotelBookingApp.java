import java.util.HashMap;
import java.util.Map;

/**
 * Use Case 3 – Centralized Room Inventory Management
 * Demonstrates HashMap based inventory
 * @version 3.1
 */

class RoomInventory {

    private Map<String, Integer> availability;

    RoomInventory() {

        availability = new HashMap<>();

        availability.put("Single Room", 5);
        availability.put("Double Room", 3);
        availability.put("Suite Room", 2);
    }

    int getAvailability(String type) {
        return availability.get(type);
    }

    void updateAvailability(String type, int count) {
        availability.put(type, count);
    }

    void displayInventory() {

        System.out.println("===== Centralized Room Inventory v3.1 =====");

        for (String type : availability.keySet()) {
            System.out.println(type + " Available : " + availability.get(type));
        }
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();
    }
}