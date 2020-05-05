package dataLayer;

import RadioFiles.MusicDisc;
import RadioFiles.Song;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RadioSerialization {

    public RadioSerialization() {}

    public void saveSongToHardDisc(MusicDisc disc, int index, String pathName) {
        XStream xStream = new XStream(new DomDriver());
        File file = new File(pathName);

        String xml = xStream.toXML(disc.getSongList().get(index));

        try {
            FileWriter fileWriter = new FileWriter(file);    // Konstrukcja i otwarcie strumienia
            fileWriter.write(xml);                            // Zapis do pliku
            fileWriter.close();                            // Zamkniecie strumienia
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (Exception se) {
            System.err.println("blad sec");
        }
    }

    public void openSongFromHardDisc(MusicDisc disc, String pathName) {
        try {
            XStream xStream = new XStream(new DomDriver());
            File file = new File(pathName);
            Song help = (Song) (xStream.fromXML(file));
            disc.addSong(help);
        } catch (Exception e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }
}
