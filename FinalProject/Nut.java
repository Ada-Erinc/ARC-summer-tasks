
class Nut extends Hardware {
    double diameter;
    int threadPitch;

    public Nut(int partId, String name, int stock, double cost, double diameter, int threadPitch) {
        super(partId, name, stock, cost);
        
        this.diameter = diameter;
        this.threadPitch = threadPitch;
    }

    public String toCsvExtra() {
        return diameter + "," + threadPitch;
    }
    
    public String getCategoryDetails() {
        return "A small metal block with an internal screw thread used alongside a bolt to clamp parts together.";
    }

    public double getDiameter() {
        return diameter;
    }

    public int getThreadPitch() {
        return threadPitch;
    }
}