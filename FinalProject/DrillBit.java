
class DrillBit extends Hardware {
    double diameter;
    String type;

    public DrillBit(int partId, String name, int stock, double cost, double diameter, String type) {
        super(partId, name, stock, cost);

        this.diameter = diameter;
        this.type = type;
    }

    public String toCsvExtra() {
        return diameter + "," + type;
    }

    public String getCategoryDetails() {
        return "A rotating cutting tool used in a drill to create cylindrical holes in materials.";
    }

    public double getDiameter() {
        return diameter;
    }

    public String getType() {
        return type;
    }
}