
public class Main {

    public static void main(String[] args) {
        Radio radio = new Radio();
        MusicDisc disc1 = new MusicDisc();
        disc1.addSong("40-1", "Sabaton");
        disc1.addSong("Uprising", "Sabaton");
        disc1.addSong("You're Beautiful", "James Blunt");
        disc1.addSong("Perfect", "Ed Sheeran");

        String pathName = "zapisz.xml";
        disc1.saveSongToHardDisc(2, pathName);

        pathName = "odczytaj.xml";
        disc1.openSongFromHardDisc(pathName);

        System.out.println(disc1.toString());

        radio.loadDisc(disc1);

    }

}
