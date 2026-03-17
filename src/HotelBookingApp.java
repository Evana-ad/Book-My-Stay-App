import java.util.*;

class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {

    Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation r) {
        queue.add(r);
    }

    Reservation getNextRequest() {
        return queue.poll();
    }

    boolean isEmpty() {
        return queue.isEmpty();
    }
}

class RoomInventory {

    Map<String, Integer> availability = new HashMap<>();

    RoomInventory() {
        availability.put("Single Room", 2);
        availability.put("Double Room", 2);
        availability.put("Suite Room", 1);
    }

    int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }

    void decrement(String type) {
        availability.put(type, availability.get(type) - 1);
    }
}

class RoomAllocationService {

    Set<String> allocatedRoomIds = new HashSet<>();
    Map<String, Set<String>> allocationMap = new HashMap<>();

    void processBookings(BookingRequestQueue queue, RoomInventory inventory) {

        System.out.println("\n===== Reservation Confirmation v6.1 =====");

        while (!queue.isEmpty()) {

            Reservation r = queue.getNextRequest();
            int available = inventory.getAvailability(r.roomType);

            if (available > 0) {

                String roomId;

                do {
                    roomId = r.roomType.substring(0, 1).toUpperCase()
                            + (int) (Math.random() * 100);
                } while (allocatedRoomIds.contains(roomId));

                allocatedRoomIds.add(roomId);

                allocationMap
                        .computeIfAbsent(r.roomType, k -> new HashSet<>())
                        .add(roomId);

                inventory.decrement(r.roomType);

                System.out.println("Booking Confirmed → "
                        + r.guestName + " | Room ID : " + roomId);

            } else {

                System.out.println("Booking Failed (No Availability) → "
                        + r.guestName);
            }
        }
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingRequestQueue queue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService service = new RoomAllocationService();

        int n;

        System.out.print("Enter number of booking requests : ");

        while (true) {
            try {
                n = Integer.parseInt(sc.nextLine());
                break;
            } catch (Exception e) {
                System.out.print("Enter valid number : ");
            }
        }

        for (int i = 1; i <= n; i++) {

            System.out.print("Guest Name : ");
            String name = sc.nextLine();

            System.out.print("Room Type : ");
            String type = sc.nextLine();

            queue.addRequest(new Reservation(name, type));
        }

        service.processBookings(queue, inventory);
    }
}