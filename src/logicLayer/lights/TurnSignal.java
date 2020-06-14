package logicLayer.lights;

/**
 * Klasa odpowiedzialna za kierunkowskazy.
 * Przechowuje dwa pola klasy Light odpowiedzialne za kierunkowskazy.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.lights.Light
 */
public class TurnSignal {
    private Light leftLight;
    private Light rightLight;

    /**
     * Konstruktor, tworzy dwie nowe instancje klasy light i przypisuje je do pol klasy TurnSignal
     */
    public TurnSignal() {
        this.leftLight = new Light();
        this.rightLight = new Light();
    }

    /**
     * Metoda odpowiedzialna za wlaczenie lewego kierunkowskazu.
     * Jezeli prawy kierunkowskaz jest juz wlaczany, zostanie on wylaczony.
     */
    public void turnLeft() {
        if( this.rightLight.isOn() ) this.rightLight.switchLight();

        this.leftLight.switchLight();
    }

    /**
     * Metoda odpowiedzialna za wlaczanie prawego kierunkowskazu.
     * Jezeli lewy kierunkowskaz jest juz wlaczany, zostanie on wylaczony
     */
    public void turnRight() {
        if( this.leftLight.isOn() ) this.leftLight.switchLight();

        this.rightLight.switchLight();
    }

    public Light getLeftLight() {
        return leftLight;
    }

    public Light getRightLight() {
        return rightLight;
    }
}
