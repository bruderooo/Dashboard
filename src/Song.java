public class Song  {
    private String name;
    private String author;

    public Song(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public Song(Object song) {
        this.name = ((Song) song).getName();
        this.author = ((Song) song).getAuthor();
    }

    public String getName() {return name;}

    public String getAuthor() {return author;}

    @Override
    public String toString() {return author + " - \"" + name + "\"";}

}
