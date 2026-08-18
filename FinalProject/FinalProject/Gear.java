
class Gear extends Hardware {
    int teethCount;
    String material;
    double pitchDiameter;

    public Gear(int partId, String name, int stock, double cost, int teethCount, String material, double pitchDiameter) {
        super(partId, name, stock, cost);

        this.teethCount = teethCount;
        this.material = material;
        this.pitchDiameter = pitchDiameter;
    }

    public String toCsvExtra() {
        return teethCount + "," + material + "," + pitchDiameter;
    }

    public String getCategoryDetails() {
        return "A toothed wheel that meshes with another to transmit torque and alter speed or direction in a mechanism.";
    }

    public int getTeethCount() {
        return teethCount;
    }

    public String getMaterial() {
        return material;
    }

    public double getPitchDiameter() {
        return pitchDiameter;
    }
}