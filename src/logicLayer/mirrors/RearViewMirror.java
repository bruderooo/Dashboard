package logicLayer.mirrors;


/**
 * Klasa odpowiedzialna za lusterko wsteczne.
 * Implementuje interfejs Mirror.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 *
 * @see logicLayer.mirrors.Mirror
 */
public class RearViewMirror implements Mirror {

    public final static int CLOSED = 0;
    public final static int OPENED = 2;

    int state;

    /**
     * Konstruktor, ustawia pole klasy informujace o stanie lusterek jako zamkniete.
     */
    public RearViewMirror() {
        this.state = CLOSED;
    }

    /**
     * Metoda odpowiedzialna za otwieranie lusterka wstecznego.
     */
    @Override
    public void openMirror() {
        this.state = 2;
    }

    /**
     * Metoda odpowiedzialna za zamykanie lusterek.
     */
    @Override
    public void closeMirror() {
        this.state = 0;
    }
}
