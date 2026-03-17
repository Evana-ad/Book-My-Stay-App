import java.util.*;



class BookingProcessor {

    private Map<String, Integer> inventory = new HashMap<>();

    BookingProcessor() {
        inventory.put("DELUXE", 1);
        inventory.put("SUITE", 1);
    }

    public synchronized void bookRoom(String guest, String roomType) {

        int available = inventory.getOrDefault(roomType, 0);

        if (available > 0) {

            System.out.println(guest + " successfully booked " + roomType);

            inventory.put(roomType, available - 1);

        } else {

            System.out.println(guest + " failed to book " + roomType + " (Not Available)");
        }
    }
}

class GuestThread extends Thread {

    private BookingProcessor processor;
    private String guestName;
    private String roomType;

    GuestThread(BookingProcessor processor, String guestName, String roomType) {
        this.processor = processor;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void run() {
        processor.bookRoom(guestName, roomType);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        BookingProcessor processor = new BookingProcessor();

        Thread g1 = new GuestThread(processor, "Ayan", "DELUXE");
        Thread g2 = new GuestThread(processor, "Meera", "DELUXE");
        Thread g3 = new GuestThread(processor, "Rahul", "SUITE");

        g1.start();
        g2.start();
        g3.start();
    }
}