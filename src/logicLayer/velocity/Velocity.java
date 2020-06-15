package logicLayer.velocity;

/**
 * Klasa odpowiedzialna za predkosc.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class Velocity {
    private double currentVelocity;
    public static final double retardation = 1.1;
    public static final double acceleration = 7.8;
    public static final double deceleration = 14.6;
    public static final double maxVelocity = 182.0;
    public static final double minVelocity = 0.0;

    /**
     * Konstruktor ustawiajacy obecna predkosc na zero.
     */
    public Velocity() {
        currentVelocity = 0;
    }

    /**
     * Metoda odpowiedzialna za zwiekszanie predkosci.
     */
    public void speedUp() {
        currentVelocity += acceleration;
    }

    /**
     * Metoda odpowiedzialna za zmniejszanie predkosci spowodowane oporami.
     */
    public void resistancesSlowDown() {
        currentVelocity -= retardation;
    }

    /**
     * Metoda odpowiedizalna za zmiejszanie predkosci przez uzytkownika.
     */
    public void slowDown() {
        currentVelocity -= deceleration;
    }

    /**
     * Metoda odpowiedzialna za obliczanie obecnej predkosci pojazdu.
     *
     * @param speedingUp czy uzytkownik przyspiesza pojazd
     * @param slowingDown czy uzytkownik hamuje pojazd
     * @param cruiseControll czy pojazd obecnie ma wlaczany tempomat
     */
    public void calculate(boolean speedingUp, boolean slowingDown, boolean cruiseControll) {
        if (cruiseControll) return;
        if(speedingUp) speedUp();
        if(slowingDown) slowDown();
        resistancesSlowDown();
        if(currentVelocity > maxVelocity ) currentVelocity = maxVelocity;
        if(currentVelocity < minVelocity ) currentVelocity = minVelocity;
    }

    public double getCurrentVelocity() {
        return currentVelocity;
    }
}
