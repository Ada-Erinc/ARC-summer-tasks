import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Inventory {
    
    Map<Integer, Hardware> inventory = new HashMap<>();

    public enum SortType {
        QUANTITY, COST, TOTALCOST, PARTID
    }

    public enum SortOrder {
        ASCENDING, DESCENDING
    }

    public Inventory() {}

    public int getInventorySize() {
        return inventory.size();
    }

    public void addItem(Hardware part) {
        inventory.put(part.getPartId(), part);
    }

    public void removeItem(int partId) {
        inventory.remove(partId);
    }

    public void adjustQuantityItem(int partId, int adjustedAmount) {
        if (adjustedAmount < 0) {
            throw new IllegalArgumentException("Adjusted amount of an item can not be negative.");
        }

        inventory.get(partId).setStock(adjustedAmount);
    }

    public void isUnderStocked(int stockThreshold) {
        for (Map.Entry<Integer, Hardware> entry : inventory.entrySet()) {
            Hardware item = entry.getValue();
            if (item.getStock() < stockThreshold) {
                System.out.println(item + " is lower than stock threshold.");
            } else if (item.getStock() == stockThreshold) {
                System.out.println(item +" is at stock threshold.");
            }
        }
    }

    public void listInventory() {
        listInventory(SortType.PARTID, SortOrder.ASCENDING);
    }

    public void listInventory(SortType sortType, SortOrder sortOrder) {

        List<Hardware> hardwareList = new ArrayList<Hardware>(inventory.values());

        Comparator<Hardware> comparator;
        
        if (sortType == SortType.QUANTITY) {
            comparator = Comparator.comparing(Hardware::getStock);
        } else if (sortType == SortType.COST) {
            comparator = Comparator.comparing(Hardware::getCost);
        } else if(sortType == SortType.PARTID) {
            comparator = Comparator.comparing(Hardware::getPartId);
        } else {
            comparator = Comparator.comparing(Hardware::getTotalCost);
        }

        if (sortOrder == SortOrder.DESCENDING) {
            comparator = comparator.reversed();
        }

        hardwareList.sort(comparator);

        for (int i = 0; i < hardwareList.size(); i++) {
            if (sortType == SortType.QUANTITY) {
                System.out.println(hardwareList.get(i) + " " + hardwareList.get(i).getStock() + " in stock.");
            } else if (sortType == SortType.COST) {
                System.out.println(hardwareList.get(i) + " costs " + hardwareList.get(i).getCost());
            } else if (sortType == SortType.PARTID) {
                System.out.println(hardwareList.get(i));
            } else {
                System.out.println(hardwareList.get(i) + " total cost is " + hardwareList.get(i).getTotalCost());
            }
        }
    }

    // I chose a wider, denser format where I parse remaining columns differently based on the type value.
    public void importCsv(String filepath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int partId = Integer.parseInt(parts[1]);
                String name = parts[2];
                int stock = Integer.parseInt(parts[3]);
                double cost = Double.parseDouble(parts[4]);
                if (parts[0].equals("Bolt")) {
                    double length = Double.parseDouble(parts[5]);
                    double diameter = Double.parseDouble(parts[6]);
                    addItem(new Bolt(partId, name, stock, cost, length, diameter));
                } else if (parts[0].equals("Nut")) {
                    double diameter = Double.parseDouble(parts[5]);
                    int threadPitch = Integer.parseInt(parts[6]);
                    addItem(new Nut(partId, name, stock, cost, diameter, threadPitch));
                } else if (parts[0].equals("Gear")) {
                    int teethCount = Integer.parseInt(parts[5]);
                    String material = parts[6];
                    double pitchDiameter = Double.parseDouble(parts[7]);
                    addItem(new Gear(partId, name, stock, cost, teethCount, material, pitchDiameter));
                } else if (parts[0].equals("Washer")) {
                    double outerDiameter = Double.parseDouble(parts[5]);
                    double innerDiameter = Double.parseDouble(parts[6]);
                    double thickness = Double.parseDouble(parts[7]);
                    addItem(new Washer(partId, name, stock, cost, outerDiameter, innerDiameter, thickness));
                } else if (parts[0].equals("Wire")) {
                    double guage = Double.parseDouble(parts[5]);
                    double lenMeter = Double.parseDouble(parts[6]);
                    String insulationColor = parts[7];
                    addItem(new Wire(partId, name, stock, cost, guage, lenMeter, insulationColor));
                } else if (parts[0].equals("Bearing")) {
                    double boreDiameter = Double.parseDouble(parts[5]);
                    double loadRating = Double.parseDouble(parts[6]);
                    addItem(new Bearing(partId, name, stock, cost, boreDiameter, loadRating));
                } else if (parts[0].equals("ThreadedRod")) {
                    double diameter = Double.parseDouble(parts[5]);
                    double length = Double.parseDouble(parts[6]);
                    int threadPitch = Integer.parseInt(parts[7]);
                    addItem(new ThreadedRod(partId, name, stock, cost, diameter, length, threadPitch));
                } else if (parts[0].equals("DrillBit")) {
                    double diameter = Double.parseDouble(parts[5]);
                    String type = parts[6];
                    addItem(new DrillBit(partId, name, stock, cost, diameter, type));
                } else if (parts[0].equals("Screwdriver")) {
                    double diameter = Double.parseDouble(parts[5]);
                    String type = parts[6];
                    addItem(new Screwdriver(partId, name, stock, cost, diameter, type));
                } else if (parts[0].equals("ScrewdriverBit")) {
                    double diameter = Double.parseDouble(parts[5]);
                    String type = parts[6];
                    addItem(new ScrewdriverBit(partId, name, stock, cost, diameter, type));
                } else if (parts[0].equals("Screw")) {
                    int diameter = Integer.parseInt(parts[5]);
                    String headShape = parts[6];
                    String driveType = parts[7];
                    addItem(new Screw(partId, name, stock, cost, diameter, headShape, driveType));
                }
            }
        } catch(IOException e) {
            System.out.println("There is a problem with the file.");
        }
    }

    public void exportCsv(String filepath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
            writer.write("type,partId,name,stock,cost,type based proporties");
            writer.newLine();
            for (Map.Entry<Integer, Hardware> entry : inventory.entrySet()) {
                Hardware item = entry.getValue();
                writer.write(item.getClass().getSimpleName() + "," + item.toCsv());
                writer.newLine();
            }
        } catch(IOException e) {
            System.out.println("There is a problem with the file.");
        }
    }
}