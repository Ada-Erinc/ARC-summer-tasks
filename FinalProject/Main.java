import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        // inventory.addItem(new Bolt(1, "Bolt", 35, 15.5, 5.0, 0.5));
        // inventory.addItem(new Screw(2, "Screw", 15, 5.5, 10, "Flat", "Slotted"));
        // inventory.addItem(new Nut(3, "Nut", 45, 25.5, 5.0, 5));
        // inventory.addItem(new Bearing(4, "Bearing", 20, 45, 5.0, 0.20));
        inventory.importCsv("inventory.csv");

        //Test
        Inventory.SortType sortType = Inventory.SortType.QUANTITY;
        Inventory.SortOrder sortOrder = Inventory.SortOrder.DESCENDING;
        List<Hardware> inventoryList = inventory.listInventory(sortType, sortOrder);
        System.out.println("\nInventory listed by " + sortType + " in " + sortOrder + " order");
        for (int i = 0; i < inventoryList.size(); i++) {
            Hardware item = inventoryList.get(i);
            System.out.println((i+1) + ". " + item);
        }
        System.out.println();

        inventory.isUnderStocked(20);
        // inventory.exportCsv("inventory2.csv");


        //task
        start();
        int choice;
        while (true) {
            System.out.print("\nChoose an Option:");
            choice = scanner.nextInt();
            if (choice < 0 || choice > 9) {
                System.out.println("\nPlease enter a choice.");
            } else {
                break;
            }
        }
        if (choice == 1) {
            System.out.print("\nType: ");
            String type = scanner.nextLine();
            System.out.print("\n");
        } else if (choice == 2) {
            System.out.print("\nEnter the Id of the item you want to remove: ");
            int Id = scanner.nextInt();
            inventory.removeItem(Id);
        } else if (choice == 3) {
            System.out.print("\nEnter the Id of the item you want it's stock to change: ");
            int id = scanner.nextInt();
            System.out.print("\nEnter the amount you want it to be adjusted to: ");
            int newStock = scanner.nextInt();
            inventory.adjustQuantityItem(id, newStock);
        } else if (choice == 4) {
            for (Hardware item : inventory.listInventory()) {
                System.out.println(item);
            }
        } else if (choice == 5) {
            System.out.print("\nEnter a stock threshold: ");
            int stockThreshold = scanner.nextInt();
            inventory.isUnderStocked(stockThreshold);
        } else if (choice == 6) {
            inventory.importCsv("inventory.csv");
        } else if (choice == 7) {
            inventory.exportCsv("inventory.csv");
        } else if (choice == 8) {
            System.exit(0);
        }
    }

    public static void start() {
        System.out.println("====== Supply Tracker ======");
        System.out.println("1. Add new item");
        System.out.println("2. Remove an item");
        System.out.println("3. Adjust stock");
        System.out.println("4. View inventory");
        System.out.println("5. View low-stock items");
        System.out.println("6. Import from CSV");
        System.out.println("7. Export CSV");
        System.out.println("8. Exit");
    }
}