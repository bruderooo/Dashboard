package logicLayer.mirrors;

/**
 * Interfejs sluzacy do obslugi lusterek pojazdu.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public interface Mirror {

    /**
     * Metoda odpowiedzialna za otwieranie/rozlozenie lusterka.
     */
    void openMirror();

    /**
     * Metoda odpowiedzialna za zlozenie/zamkniecie lusterka.
     */
    void closeMirror();

}
