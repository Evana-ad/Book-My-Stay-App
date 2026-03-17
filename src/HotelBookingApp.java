import java.util.*;



class AddOnService {

    String name;
    double cost;

    AddOnService(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    void addService(String reservationId, AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    void displayServices(String reservationId) {

        List<AddOnService> list = serviceMap.get(reservationId);

        if (list == null) {
            System.out.println("No add-on services selected.");
            return;
        }

        double total = 0;

        System.out.println("\nServices for Reservation : " + reservationId);

        for (AddOnService s : list) {
            System.out.println(s.name + " → " + s.cost);
            total += s.cost;
        }

        System.out.println("Total Add-On Cost : " + total);
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AddOnServiceManager manager = new AddOnServiceManager();

        System.out.print("Enter Reservation ID : ");
        String reservationId = sc.nextLine();

        System.out.print("Enter number of add-on services : ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {

            System.out.print("Service Name : ");
            String name = sc.nextLine();

            System.out.print("Service Cost : ");
            double cost = Double.parseDouble(sc.nextLine());

            manager.addService(reservationId,
                    new AddOnService(name, cost));
        }

        manager.displayServices(reservationId);
    }
}