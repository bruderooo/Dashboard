package logicLayer.mirrors;

/**
 * Klasa odpowiedzialna za lusterko poczne.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class WingMirror implements Mirror {

    boolean state;

    /**
     * Konstruktor, ustawia pole klasy informujace o stanie lusterek jako zamkniete.
     */
    public WingMirror() {
        this.state = false;
    }

    /**
     * Metoda odpowiedzialna za zamykanie lusterka bocznego.
     */
    @Override
    public void openMirror() {
        this.state = true;
    }

    /**
     * Metoda odpowiedzialna za otwieranie lusterka bocznego.
     */
    @Override
    public void closeMirror() {
        this.state = false;
    }
}
