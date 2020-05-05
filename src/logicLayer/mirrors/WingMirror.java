package logicLayer.mirrors;

public class WingMirror implements Mirror {

    // State w tej klasie jest zmienną logiczną ponieważ
    // przyjmuje wartosci true albo false. Są to odpowiednio
    // schowane i otwarte.

    boolean state;

    public WingMirror() {
        this.state = false;
    }

    @Override
    public void openMirror() {
        this.state = true;
    }

    @Override
    public void closeMirror() {
        this.state = false;
    }
}
