import java.util.*;



class CancellationService {

    private Map<String, String> reservationRoomMap = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    CancellationService() {

        inventory.put("DELUXE", 1);
        inventory.put("SUITE", 1);
        inventory.put("STANDARD", 2);

        reservationRoomMap.put("R101", "DELUXE");
        reservationRoomMap.put("R102", "SUITE");
    }

    void cancelBooking(String reservationId) {

        if (!reservationRoomMap.containsKey(reservationId)) {
            System.out.println("Cancellation Failed : Reservation Not Found");
            return;
        }

        String roomType = reservationRoomMap.get(reservationId);

        rollbackStack.push(reservationId);

        inventory.put(roomType, inventory.get(roomType) + 1);

        reservationRoomMap.remove(reservationId);

        System.out.println("Booking Cancelled Successfully");
        System.out.println("Inventory Restored for " + roomType);
    }

    void showRollbackStack() {
        System.out.println("Rollback Stack : " + rollbackStack);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CancellationService service = new CancellationService();

        System.out.print("Enter Reservation ID to Cancel : ");
        String id = sc.nextLine();

        service.cancelBooking(id);
        service.showRollbackStack();
    }
}