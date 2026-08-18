
class ThreadedRod extends Hardware {
    double diameter;
    double length;
    int threadPitch;

    public ThreadedRod(int partId, String name, int stock, double cost, double diameter, double length, int threadPitch) {
        super(partId, name, stock, cost);

        this.diameter = diameter;
        this.length = length;
        this.threadPitch = threadPitch;
    }

    public String toCsvExtra() {
        return diameter + "," + length + "," + threadPitch;
    }

    public String getCategoryDetails() {
        return "A long, headless metal bar threaded along its entire length, designed to be cut to size or used in tension application.";
    }

    public double getDiameter() {
        return diameter;
    }

    public double getLength() {
        return length;
    }

    public int getThreadPitch() {
        return threadPitch;
    }
}