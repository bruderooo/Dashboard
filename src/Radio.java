import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Radio {
    private ArrayList<Song> songList = new ArrayList<>();

    public Radio() {}

    public Radio(Song... songs) { Collections.addAll(songList, songs); }

    public void addSong(Song song) { songList.add(song);}

    public void addSong(String name, String author) {
        songList.add(new Song(name, author));
    }

    public Song getByIndex(int index) {return songList.get(index);}

    public Song getByName(String name) {
        for (Song song: songList) {
            if (song.getName().equals(name)) return song;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Song list:\n");
        for (Song song : songList) {
            s.append(song.toString()).append("\n");
        }
        return s.toString();
    }

    /*
            Comparators for comparing by song name or author
            Usage examples:
            Collections.sort(collectionName, ComparatorObjectName)
            collectionName.sort(ComparatorObjectName)
        */
    Comparator<Song> nameComparator = Comparator.comparing(Song::getName);
    Comparator<Song> authorComparator = Comparator.comparing(Song::getAuthor);

    public void sortByName() {songList.sort(nameComparator);}

    public void sortByAuthor() {songList.sort(authorComparator);}

}
