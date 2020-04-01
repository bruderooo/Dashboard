import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Radio radio = new Radio();
        radio.addSong("40-1", "Sabaton");
        radio.addSong("Uprising", "Sabaton");
        radio.addSong("You're Beautiful", "James Blunt");
        radio.addSong("Perfect", "Ed Sheeran");

        String pathName = "zapisz.xml";
        radio.saveSongToHardDisc(2, pathName);

        pathName = "odczytaj.xml";
        radio.openSongFromHardDisc(pathName);

        System.out.println(radio.toString());

    }

}
