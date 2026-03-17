import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



class Reservation {

    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest : " + guestName + " | Requested : " + roomType);
    }
}

class BookingRequestQueue {

    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request added to queue.");
    }

    void displayQueue() {

        System.out.println("\n===== Booking Request Queue (FIFO) =====");

        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        System.out.print("Enter number of booking requests : ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.print("Enter guest name : ");
            String name = sc.nextLine();

            System.out.print("Enter room type : ");
            String type = sc.nextLine();

            Reservation r = new Reservation(name, type);

            bookingQueue.addRequest(r);
        }

        bookingQueue.displayQueue();
    }
}