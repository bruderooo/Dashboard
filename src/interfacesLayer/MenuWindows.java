package interfacesLayer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Klasa odpowiedzialna za okna z informacjami o aplikacji oraz instrukcjami do niej.
 *
 * @author Daniel Londka
 * @author Szymon Jacon
 */
public class MenuWindows {

    /**
     * Statyczna metoda ktorej wywolanie otwiera okno zawierajace informacje o programie i instrukcje.
     *
     * @param title tytul okna.
     */
    public static void displayProgramInfo(String title){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(800);
        window.setMinHeight(300);
        GridPane grid = new GridPane();
        Text text = new Text("Program symulujący funkcjonalność deski rozdzielczej samochodu.\n" +
                "- Aby zacząć korzystać z funkcjonalności samochodu należy uruchomić pojazd przyciskiem start,\n" +
                "   służy on również do gaszenia silnika\n" +
                "- Aby przyspieszyć należy wcisnąć strałkę w górę / W\n" +
                "- Aby hamować należy wcisnąć strzałkę w dół / S\n" +
                "- Aby włączyć kierunkowskaz należy wcisnąć strałkę w jego kierunku lub A/ D,\n" +
                "   można również właczyć go poprzez wciśnięcie jego kontrolki lewym przyciskiem myszy,\n"+
                "   aby je wyłączyć należy ponownie wykonać jedną z tych czynności.\n" +
                "- Aby włączyć któreś z pozostałych świateł należy lewym przyciskiem myszy wcisnąć jego kontrolkę,\n" +
                "   aby je wyłączyć należy powtórzyć tę czynność.\n" +
                "- W lewym górnym rogu okna aplikacji mamy przycisk \"Uzupełnij\" otwierający menu przycisków\n" +
                "   pozwalających uzupełnić zbiornik paliwa, naładować akumulator, uzupełnic olej silnikowy.\n" +
                "- Na prawo od poprzedniego przycisku jest przycisk Trasy, w przyszłości jego funkcjonalność\n" +
                "   pozwoli na zapisywanie i resetowanie wybranych tras / przebiegów.\n" +
                "\n" + "");
        text.setFont(new Font("Verdana", 12));
        grid.getChildren().add(text);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }

    /**
     * Metoda statyczna odpowiedzialna za otworzenie okna z informacjami o pojezdzie.
     *
     * @param title tytuł okna.
     */
    public static void displayAutoInfo(String title) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(350);
        window.setMinHeight(300);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Text text = new Text("Producent: Renault\n" +
                "Model: Megane Scenic\n" +
                "Kraj produkcji: Niemcy\n" +
                "Rok produkcji: 2003\n" +
                "Liczba drzwi: 5\n" +
                "Rodzaj paliwa: Benzyna\n" +
                "Pojemność silnika: 1600 cm^3\n" +
                "Moc silnika: 115KM\n" +
                "Skrzynia biegów: 6-biegowa manualna\n" +
                "Napęd: Przedni\n" +
                "Masa własna: 1315kg: \n" +
                "Liczba miejsc: 5\n" + "");
        text.setFont(new Font("Verdana", 12));
        grid.getChildren().add(text);

        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.show();
    }
}
