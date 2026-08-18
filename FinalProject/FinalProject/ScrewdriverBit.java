
class ScrewdriverBit extends Hardware {
    double diameter;
    String type;

    public ScrewdriverBit(int partId, String name, int stock, double cost, double diameter, String type) {
        super(partId, name, stock, cost);

        this.diameter = diameter;
        this.type = type;
    }

    public String toCsvExtra() {
        return diameter + "," + type;
    }

    public String getCategoryDetails() {
        return "An interchangeable tip inserted into a power driver or multi-bit handle to turn specific screw head patterns.";
    }

    public double getDiameter() {
        return diameter;
    }

    public String getType() {
        return type;
    }
}
