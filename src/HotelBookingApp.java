import java.io.*;
import java.util.*;



class SystemState implements Serializable {

    Map<String, Integer> inventory;
    List<String> bookingHistory;

    SystemState(Map<String, Integer> inventory, List<String> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

class PersistenceService {

    private static final String FILE_NAME = "system_state.dat";

    void save(SystemState state) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("System state saved.");

        } catch (Exception e) {
            System.out.println("Error saving state.");
        }
    }

    SystemState load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(FILE_NAME))) {

            System.out.println("System state restored.");
            return (SystemState) in.readObject();

        } catch (Exception e) {

            System.out.println("No previous state found. Starting fresh.");

            Map<String, Integer> inv = new HashMap<>();
            inv.put("DELUXE", 2);
            inv.put("SUITE", 1);

            return new SystemState(inv, new ArrayList<>());
        }
    }
}

public class HotelBookingApp {

    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        SystemState state = service.load();

        state.bookingHistory.add("R101-DELUXE");

        state.inventory.put("DELUXE",
                state.inventory.get("DELUXE") - 1);

        System.out.println("Current Inventory : " + state.inventory);
        System.out.println("Booking History : " + state.bookingHistory);

        service.save(state);
    }
}