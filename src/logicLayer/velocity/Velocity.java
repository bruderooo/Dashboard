package logicLayer.velocity;

public class Velocity {
    private double currentVelocity;
    public static final double retardation = 1.1;
    public static final double acceleration = 6.8;
    public static final double deceleration = 10.6;
    public static final double maxVelocity = 182.0;
    public static final double minVelocity = 0.0;

    public Velocity() {
        currentVelocity = 0;
    }

    public void speedUp() {
        currentVelocity += acceleration;
    }

    public void resistancesSlowDown() {
        currentVelocity -= retardation;
    }

    public void slowDown() {
        currentVelocity -= deceleration;
    }

    public void calculate(boolean speedingUp, boolean slowingDown, boolean cruiseControll) {
        if (cruiseControll) return;
        if(speedingUp) speedUp();
        if(slowingDown) slowDown();
        resistancesSlowDown();
        if(currentVelocity > maxVelocity ) currentVelocity = maxVelocity;
        if(currentVelocity < minVelocity ) currentVelocity = minVelocity;
    }

    public void setCurrentVelocity(double currentVelocity) {
        this.currentVelocity = currentVelocity;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }
}
