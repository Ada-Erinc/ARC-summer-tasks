
class Washer extends Hardware {
    double outerDiameter;
    double innerDiameter;
    double thickness;

    public Washer(int partId, String name, int stock, double cost, double outerDiameter, double innerDiameter, double thickness) {
        super(partId, name, stock, cost);

        this.outerDiameter = outerDiameter;
        this.innerDiameter = innerDiameter;
        this.thickness = thickness;
    }

    public String toCsvExtra() {
        return outerDiameter + "," + innerDiameter + "," + thickness;
    }

    public String getCategoryDetails() {
        return "A flat, thin ring placed under a nut or bolt head to distribute clamping load and prevent surface damage.";
    }

    public double getOuterDiameter() {
        return outerDiameter;
    }

    public double getInnerDiameter() {
        return innerDiameter;
    }

    public double getThickness() {
        return thickness;
    }
}