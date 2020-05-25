package logicLayer.mirrors;



public class RearViewMirror implements Mirror {

    // State w tej klasie jest zmienną całkowitą ponieważ
    // przyjmuje wartosci 0, 1 i 2. Są to odpowiednio
    // schowane, tryb nocny (lusterko otwarte do połowy)
    // otwarte

    public final static int CLOSED = 0;
    public final static int SEMI_OPENED = 1;
    public final static int OPENED = 2;

    int state;

    public RearViewMirror() {
        this.state = CLOSED;
    }

    // Metoda ta przełącza w tryb nocny (lusterko otwarte
    // do połowy)


    public void nightMode() {
        this.state = 1;
    }

    // Metoda służąca do otwierania lusterka w pełni
    @Override
    public void openMirror() {
        this.state = 2;
    }

    // Metoda do zamykania lusterka
    @Override
    public void closeMirror() {
        this.state = 0;
    }
}
