import java.util.*;


class Reservation {

    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println(reservationId + " | " + guestName + " | " + roomType);
    }
}

class BookingHistory {

    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    void generateReport(List<Reservation> list) {

        if (list.isEmpty()) {
            System.out.println("No bookings available.");
            return;
        }

        System.out.println("\n---- BOOKING HISTORY REPORT ----");

        for (Reservation r : list) {
            r.display();
        }

        System.out.println("Total Bookings : " + list.size());
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BookingHistory history = new BookingHistory();
        BookingReportService report = new BookingReportService();

        System.out.print("Enter number of confirmed bookings : ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {

            System.out.println("\nBooking " + i);

            System.out.print("Reservation ID : ");
            String id = sc.nextLine();

            System.out.print("Guest Name : ");
            String name = sc.nextLine();

            System.out.print("Room Type : ");
            String type = sc.nextLine();

            history.addReservation(new Reservation(id, name, type));
        }

        report.generateReport(history.getHistory());
    }
}