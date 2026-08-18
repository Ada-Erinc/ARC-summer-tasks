
class Wire extends Hardware {
    double guage;
    double lenMeter;
    String insulationColor;

    public Wire(int partId, String name, int stock, double cost, double guage, double lenMeter, String insulationColor) {
        super(partId, name, stock, cost);

        this.guage = guage;
        this.lenMeter = lenMeter;
        this.insulationColor = insulationColor;
    }

    public String toCsvExtra() {
        return guage + "," + lenMeter + "," + insulationColor;
    }

    public String getCategoryDetails() {
        return "A single, flexible strand or rod of metal used to conduct electricity or mechanically support loads.";
    }

    public double getGuage() {
        return guage;
    }

    public double getLenMater() {
        return lenMeter;
    }

    public String getInsulationColor() {
        return insulationColor;
    }
}