
class Screwdriver extends Hardware {
    double diameter;
    String type;

    public Screwdriver(int partId, String name, int stock, double cost, double diameter, String type) {
        super(partId, name, stock, cost);

        this.diameter = diameter;
        this.type = type;
    }

    public String toCsvExtra() {
        return diameter + "," + type;
    }

    public String getCategoryDetails() {
        return "A hand tool featuring a shaped tip designed to apply torque for turning and securing screws.";
    }

    public double getDiameter() {
        return diameter;
    }

    public String getType() {
        return type;
    }
}