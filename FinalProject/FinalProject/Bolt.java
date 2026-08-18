
class Bolt extends Hardware {
    double length;
    double diameter;

    public Bolt(int partId, String name, int stock, double cost, double length, double diameter) {
        super(partId, name, stock, cost);
        
        this.length = length;
        this.diameter = diameter;
    }

    public String toCsvExtra() {
        return length + "," + diameter;
    }
    
    public String getCategoryDetails() {
        return "A threaded fastner designed to pass through unthreaded holes and secure components with a matching nut.";
    }

    public enum HeadType {
        HEX, SOCKET, PHILLIPS, FLAT
    }

    public double getLength() {
        return length;
    }

    public double getDiameter() {
        return diameter;
    }
}