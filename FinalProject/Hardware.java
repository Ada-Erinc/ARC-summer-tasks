import java.util.Objects;



abstract class Hardware {
    protected int partId;
    protected String name;
    protected int stock;
    protected double cost;

    public Hardware(int partId, String name, int stock, double cost) {
        this.partId = partId;
        this.name = name;
        this.stock = stock;
        this.cost = cost;
    }

    abstract String getCategoryDetails();

    public String toCsv() {
        String csv = partId + "," + name + "," + stock + "," + cost;
        String extra = toCsvExtra();
        if (!extra.isEmpty()) {
            csv = csv + "," + extra;
        }
        return csv;
    }

    abstract String toCsvExtra();

    @Override
    public String toString() {
        return name + " (" + partId + ")";
    }

    @Override
    public boolean equals(Object p) {
        return ((Hardware)p).getPartId() == getPartId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(partId);
    }

    //setters
    public void setStock(int newStock) {
        this.stock = newStock;
    }

    //getters
    public int getPartId() {
        return partId;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getCost() {
        return cost;
    }

    public double getTotalCost() {
        return stock * cost;
    }
}
