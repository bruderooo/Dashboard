import java.util.ArrayList;
import java.util.Collections;


public class Radio {
    private ArrayList<Song> songList = new ArrayList<Song>();

    public Radio() {};

    public Radio(Song... songs) { Collections.addAll(songList, songs); }

    public void addSong(Song song) { songList.add(song);}

}
