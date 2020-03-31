public class MainClass {
    public static void main(String[] args) {
        Radio radio = new Radio();
        Song song1 = new Song("To Hell and Back", "Sabaton");
        radio.addSong(song1);
        radio.addSong("Uprising", "Sabaton");
        radio.addSong("Hurt", "Nine Inch Nails");
        radio.addSong("Bismarc", "Sabaton");

        System.out.println("\nLista utworow w kolejnosci powstalej na skutek inicjalizacji:\n\n" + radio.toString());
        radio.sortByName();
        System.out.println("\nLista utworow posortowana wzgledem tytulow:\n\n" + radio.toString());
        radio.sortByAuthor();
        System.out.println("\nLista utworow posortowana wzgledem autorow:\n\n" + radio.toString());
    }
}
