import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Song implements Comparable<Song> {
    private String name;
    private String author;

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Song song) {
        return this.name.compareTo(song.getName());
    }
}
