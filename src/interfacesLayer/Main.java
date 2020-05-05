package interfacesLayer;

import RadioFiles.MusicDisc;
import RadioFiles.Radio;
import dataLayer.RadioSerialization;

public class Main {

    public static void main(String[] args) {
        Radio radio = new Radio();
        MusicDisc disc1 = new MusicDisc();
        disc1.addSong("40-1", "Sabaton");
        disc1.addSong("Uprising", "Sabaton");
        disc1.addSong("You're Beautiful", "James Blunt");
        disc1.addSong("Perfect", "Ed Sheeran");

        RadioSerialization radioSerialization = new RadioSerialization();
        String pathName = "zapisz.xml";
        radioSerialization.saveSongToHardDisc(disc1, 2, pathName);

        pathName = "odczytaj.xml";
        radioSerialization.openSongFromHardDisc(disc1, pathName);

        System.out.println(disc1.toString());

        radio.loadDisc(disc1);

    }

}
