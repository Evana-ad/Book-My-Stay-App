import java.util.*;



class InvalidBookingException extends Exception {

    InvalidBookingException(String message) {
        super(message);
    }
}

class InventoryService {

    private Map<String, Integer> rooms = new HashMap<>();

    InventoryService() {
        rooms.put("DELUXE", 2);
        rooms.put("SUITE", 1);
        rooms.put("STANDARD", 3);
    }

    void validateAndBook(String roomType) throws InvalidBookingException {

        if (!rooms.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type Entered");
        }

        int available = rooms.get(roomType);

        if (available <= 0) {
            throw new InvalidBookingException("Room Not Available");
        }

        rooms.put(roomType, available - 1);

        System.out.println("Booking Confirmed for " + roomType);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        InventoryService service = new InventoryService();

        System.out.print("Enter Room Type : ");
        String type = sc.nextLine().toUpperCase();

        try {

            service.validateAndBook(type);

        } catch (InvalidBookingException e) {

            System.out.println("Booking Failed : " + e.getMessage());

        }

        System.out.println("System Running Safely...");
    }
}
