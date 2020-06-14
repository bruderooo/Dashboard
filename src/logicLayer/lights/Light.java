package logicLayer.lights;

/**
 * Klasa generyczna odpowiedzialna za swiatła.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class Light {
    private boolean isOn;

    /**
     * Konstruktor, inicjalizuje pole klasy informujace o stanie swiatla na false,
     * co oznacza, ze swiatlo jest wylaczane.
     */
    public Light() { this.isOn = false; }

    public boolean isOn() {
        return isOn;
    }

    /**
     * Metoda zmieniajaca stan swiatla na przeciwny, jezeli sa wlaczane wylacza je,
     * jezeli sa wylacane wlacza je.
     */
    public void switchLight() {
        isOn = !isOn;
    }

    /**
     * Metoda wylaczajaca swiatlo
     */
    public void switchOff() {
        isOn = false;
    }
}
