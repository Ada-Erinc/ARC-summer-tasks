import java.util.List;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    private static void test(Inventory inventory) {
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
    }

    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        // test(inventory);

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
            addingItems(inventory);
        } else if (choice == 2) {
            int Id = getIntInput("\nEnter the Id of the item you want to remove: ");
            inventory.removeItem(Id);
        } else if (choice == 3) {
            int id = getIntInput("\nEnter the Id of the item you want it's stock to change: ");
            int newStock = getIntInput("\nEnter the amount you want it to be adjusted to: ");
            inventory.adjustQuantityItem(id, newStock);
        } else if (choice == 4) {
            sortedInventory(inventory);
        } else if (choice == 5) {
            int stockThreshold = getIntInput("\nEnter a stock threshold: ");
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
        System.out.println("4. View inventory (sorted)");
        System.out.println("5. View low-stock items");
        System.out.println("6. Import from CSV");
        System.out.println("7. Export CSV");
        System.out.println("8. Exit");
    }

    private static void sortedInventory(Inventory inventory) {
        System.out.println("1. Quantity/Stock");
        System.out.println("2. Cost");
        System.out.println("3. Total cost");
        System.out.println("4. Part ID");
        int sortType = getIntInput("\nSelect how to sort: ");
        System.out.println();
        System.out.println("1. Ascending");
        System.out.println("2. Descending");
        int sortOrder = getIntInput("\nSelect sorting order: ");

        Inventory.SortType stock = Inventory.SortType.QUANTITY;
        Inventory.SortType cost = Inventory.SortType.COST;
        Inventory.SortType totalCost = Inventory.SortType.TOTALCOST;
        Inventory.SortType partId = Inventory.SortType.PARTID;

        Inventory.SortOrder ascending = Inventory.SortOrder.ASCENDING;
        Inventory.SortOrder descending = Inventory.SortOrder.DESCENDING;
        if (sortOrder == 1) {
            if (sortType == 1) {
                inventory.listInventory(stock, ascending);
            } else if (sortType == 2) {
                inventory.listInventory(cost, ascending);
            } else if (sortType == 3) {
                inventory.listInventory(totalCost, ascending);
            } else if (sortType == 4) {
                inventory.listInventory(partId, ascending);
            }
        } else if (sortOrder == 2) {
            if (sortType == 1) {
                inventory.listInventory(stock, descending);
            } else if (sortType == 2) {
                inventory.listInventory(cost, descending);
            } else if (sortType == 3) {
                inventory.listInventory(totalCost, descending);
            } else if (sortType == 4) {
                inventory.listInventory(partId, descending);
            }
        }
    }

    private static void addingItems(Inventory inventory) {
        String type = getStringInput("\nType: ");
        int partID = getIntInput("Part ID: ");
        String name = getStringInput("Name: ");
        int stock = getIntInput("Stock: ");
        double cost = getDoubleInput("Cost: ");
        if (type == "Bolt") {
            double length = getDoubleInput("\nLength: ");
            double diameter = getDoubleInput("\nDiameter: ");
            inventory.addItem(new Bolt(partID, name, stock, cost, length, diameter));
        } else if (type == "Nut") {
            double diameter = getDoubleInput("\nDiameter: ");
            int threadPitch = getIntInput("\nThread pitch: ");
            inventory.addItem(new Nut(partID, name, stock, cost, diameter, threadPitch));
        } else if (type == "Gear") {
            int teethCount = getIntInput("\nTeeth count: ");
            String material = getStringInput("\nMaterial: ");
            double pitchDiameter = getDoubleInput("\nPitch diameter: ");
            inventory.addItem(new Gear(partID, name, stock, cost, teethCount, material, pitchDiameter));
        } else if (type == "Washer") {
            double outerDiameter = getDoubleInput("\nOuter diameter: ");
            double innerDiameter = getDoubleInput("\nInner diameter: ");
            double thickness = getDoubleInput("\nThickness: ");
            inventory.addItem(new Washer(partID, name, stock, cost, outerDiameter, innerDiameter, thickness));
        } else if (type == "Wire") {
            double guage = getDoubleInput("\nGuage: ");
            double lenMeter = getDoubleInput("\nLength in meters: ");
            String insulationColor = getStringInput("\nInsulation color: ");
            inventory.addItem(new Wire(partID, name, stock, cost, guage, lenMeter, insulationColor));
        } else if (type == "Bearing") {
            double boreDiameter = getDoubleInput("\nBore diameter: ");
            double loadRating = getDoubleInput("\nLoad rating: ");
            inventory.addItem(new Bearing(partID, name, stock, cost, boreDiameter, loadRating));
        } else if (type == "ThreadedRod") {
            double diameter = getDoubleInput("\nDiameter: ");
            double length = getDoubleInput("\nLength");
            int threadPitch = getIntInput("\nThread pitch: ");
            inventory.addItem(new ThreadedRod(partID, name, stock, cost, diameter, length, threadPitch));
        } else if (type == "DrillBit") {
            double diameter = getDoubleInput("\nDiameter: ");
            String type2 = getStringInput("\nType: ");
            inventory.addItem(new DrillBit(partID, name, stock, cost, diameter, type2));
        } else if (type == "Screwdriver") {
            double diameter = getDoubleInput("\nDiameter: ");
            String type2 = getStringInput("\nType: ");
            inventory.addItem(new Screwdriver(partID, name, stock, cost, diameter, type2));
        } else if (type == "ScrewdriverBit") {
            double diameter = getDoubleInput("\nDiameter: ");
            String type2 = getStringInput("\nType: ");
            inventory.addItem(new ScrewdriverBit(partID, name, stock, cost, diameter, type2));
        } else if (type == "Screw") {
            int threadPitch = getIntInput("\nThread pitch: ");
            String headShape = getStringInput("\nHead shape: ");
            String driveType = getStringInput("\nDrive type: ");
            inventory.addItem(new Screw(partID, name, stock, cost, threadPitch, headShape, driveType));
        }
    }

    public static String getStringInput(String question) {
        while (true) {
            System.out.print(question);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Please enter something.");
            } else {
                return input;
            }
        }
    }

    public static int getIntInput(String question) {
        while (true) {
            System.out.print(question);
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();
                scanner.nextLine();
                return input;
            } else {
                System.out.println("\nPlease enter a number.");
            }
        }
    }

    public static Double getDoubleInput(String question) {
        while (true) {
            System.out.print(question);
            if (scanner.hasNextDouble()) {
                Double input = scanner.nextDouble();
                scanner.nextLine();
                return input;
            } else {
                System.out.println("\nPlease enter a number.");
            }
        }
    }
}