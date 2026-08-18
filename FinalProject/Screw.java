
class Screw extends Hardware {
    int threadPitch;
    String headShape;
    String driveType;

    public Screw(int partId, String name, int stock, double cost, int threadPitch, String headShape, String driveType) {
        super(partId, name, stock, cost);

        this.threadPitch = threadPitch;
        this.headShape = headShape;
        this.driveType = driveType;
    }

    public String toCsvExtra() {
        return threadPitch + "," + headShape + "," + driveType;
    }

    public String getCategoryDetails() {
        return "A threaded fastener that cuts or forms its own mating thread directly into a material as it is turned.";
    }

    public int getThreadPitch() {
        return threadPitch;
    }

    public String getHeadShape() {
        return headShape;
    }

    public String getDriveType() {
        return driveType;
    }
}