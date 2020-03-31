import java.util.Collections;
import java.util.Comparator;

public class Song  {
    private String name;
    private String author;

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public String getName() {return name;}

    public String getAuthor() {return author;}


/*
    Comparators for comparing by song name or author
    Usage example: Collections.sort(collectionName, ComparatorObjectName)
*/
    Comparator<Song> nameComp = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };
    Comparator<Song> authorComp = new Comparator<Song>() {
        @Override
        public int compare(Song o1, Song o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    };
}
