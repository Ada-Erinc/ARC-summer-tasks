
class Bearing extends Hardware {
    double boreDiameter;
    double loadRating;

    public Bearing(int partId, String name, int stock, double cost, double boreDiameter, double loadRating) {
        super(partId, name, stock, cost);

        this.boreDiameter = boreDiameter;
        this.loadRating = loadRating;
    }

    public String toCsvExtra() {
        return boreDiameter + "," + loadRating;
    }

    public String getCategoryDetails() {
        return "A mechanical component that reduces friction between moving parts while guiding rotational or linear motion.";
    }

    public double getBoreDiameter() {
        return boreDiameter;
    }

    public double getLoadRating() {
        return loadRating;
    }
}