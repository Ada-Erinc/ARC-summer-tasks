import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Inventory inventory = new Inventory();

        //task
        while (true) {
            System.out.println();
            start();
            int choice;
            while (true) {
                System.out.print("\nChoose an Option:");
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 9) {
                    System.out.println("\nPlease enter a choice.");
                } else {
                    break;
                }
            }
            if (choice == 1) {
                addingItems(inventory);
            } else if (choice == 2) {
                if (inventory.getInventorySize() == 0) {
                    System.out.println("\nInventory empty. Please first add an item.");
                } else {
                    int Id = getIntInput("\nEnter the Id of the item you want to remove: ");
                    inventory.removeItem(Id);
                }
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
                String exit = getStringInput("\nHave you exported yet?(Y/N): ");
                if (exit.equals("N") || exit.equals("n")) {
                    String export = getStringInput("Are you sure you want to exit without exporting?(Y/N): ");
                    if (export.equals("Y") || export.equals("y")) {
                        break;
                    }
                } else {
                    break;
                }
            }
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
        if (type.equals("Bolt")) {
            double length = getDoubleInput("Length: ");
            double diameter = getDoubleInput("Diameter: ");
            inventory.addItem(new Bolt(partID, name, stock, cost, length, diameter));
        } else if (type.equals("Nut")) {
            double diameter = getDoubleInput("Diameter: ");
            int threadPitch = getIntInput("Thread pitch: ");
            inventory.addItem(new Nut(partID, name, stock, cost, diameter, threadPitch));
        } else if (type.equals("Gear")) {
            int teethCount = getIntInput("Teeth count: ");
            String material = getStringInput("Material: ");
            double pitchDiameter = getDoubleInput("Pitch diameter: ");
            inventory.addItem(new Gear(partID, name, stock, cost, teethCount, material, pitchDiameter));
        } else if (type.equals("Washer")) {
            double outerDiameter = getDoubleInput("Outer diameter: ");
            double innerDiameter = getDoubleInput("Inner diameter: ");
            double thickness = getDoubleInput("Thickness: ");
            inventory.addItem(new Washer(partID, name, stock, cost, outerDiameter, innerDiameter, thickness));
        } else if (type.equals("Wire")) {
            double guage = getDoubleInput("Guage: ");
            double lenMeter = getDoubleInput("Length in meters: ");
            String insulationColor = getStringInput("Insulation color: ");
            inventory.addItem(new Wire(partID, name, stock, cost, guage, lenMeter, insulationColor));
        } else if (type.equals("Bearing")) {
            double boreDiameter = getDoubleInput("Bore diameter: ");
            double loadRating = getDoubleInput("Load rating: ");
            inventory.addItem(new Bearing(partID, name, stock, cost, boreDiameter, loadRating));
        } else if (type.equals("ThreadedRod")) {
            double diameter = getDoubleInput("Diameter: ");
            double length = getDoubleInput("Length");
            int threadPitch = getIntInput("Thread pitch: ");
            inventory.addItem(new ThreadedRod(partID, name, stock, cost, diameter, length, threadPitch));
        } else if (type.equals("DrillBit")) {
            double diameter = getDoubleInput("Diameter: ");
            String type2 = getStringInput("Type: ");
            inventory.addItem(new DrillBit(partID, name, stock, cost, diameter, type2));
        } else if (type.equals("Screwdriver")) {
            double diameter = getDoubleInput("Diameter: ");
            String type2 = getStringInput("Type: ");
            inventory.addItem(new Screwdriver(partID, name, stock, cost, diameter, type2));
        } else if (type.equals("ScrewdriverBit")) {
            double diameter = getDoubleInput("Diameter: ");
            String type2 = getStringInput("Type: ");
            inventory.addItem(new ScrewdriverBit(partID, name, stock, cost, diameter, type2));
        } else if (type.equals("Screw")) {
            int threadPitch = getIntInput("Thread pitch: ");
            String headShape = getStringInput("Head shape: ");
            String driveType = getStringInput("Drive type: ");
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