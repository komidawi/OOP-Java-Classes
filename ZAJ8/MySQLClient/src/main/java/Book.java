import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Book {
    private final StringProperty ISBN;
    private final StringProperty title;
    private final StringProperty author;
    private final IntegerProperty year;

    public Book(String ISBN, String title, String author, int year) {
        this.ISBN = new SimpleStringProperty(ISBN);
        this.title = new SimpleStringProperty(title);
        this.author = new SimpleStringProperty(author);
        this.year = new SimpleIntegerProperty(year);
    }

    @Override
    public String toString() {
        return String.format("ISBN: %s, Title: %s, Author: %s, Year: %d",
                ISBN.getValue(), title.getValue(), author.getValue(), year.getValue());
    }

    public String getISBN() {
        return ISBN.get();
    }

    public StringProperty ISBNProperty() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN.set(ISBN);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }
}
