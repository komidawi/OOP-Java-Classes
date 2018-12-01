import com.sun.javafx.scene.control.skin.NestedTableColumnHeader;
import com.sun.javafx.scene.control.skin.TableColumnHeader;
import com.sun.javafx.scene.control.skin.TableHeaderRow;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.lang.reflect.Method;


public class BookOverviewController {

    @FXML
    private TableView<Book> books;

    @FXML
    private TableColumn<Book, String> ISBN;

    @FXML
    private TableColumn<Book, String> title;

    @FXML
    private TableColumn<Book, String> author;

    @FXML
    private TableColumn<Book, Integer> year;

    @FXML
    private TextField authorSearch;

    @FXML
    private TextField ISBNSearch;

    @FXML
    private TextField ISBNAddField;

    @FXML
    private TextField titleAddField;

    @FXML
    private TextField authorAddField;

    @FXML
    private TextField yearAddField;

    @FXML
    private Button addBookButton;

    @FXML
    private Button refreshDataButton;


    private MainApp mainApp;
    private BookDatabaseUtils databaseUtils;

    public void setDatabaseUtils(BookDatabaseUtils databaseUtils) {
        this.databaseUtils = databaseUtils;
    }

    // Hint: The constructor is called before the initialize() method.
    public BookOverviewController() {
    }

    // Initializes the controller class. This method is automatically called after the fxml file has been loaded.
    // At this time, all the FXML fields should have been already initialized.
    @FXML
    private void initialize() {
        ISBN.setCellValueFactory(cellData -> cellData.getValue().ISBNProperty());
        title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
    }

    @FXML
    private void addBookToRemoteDatabase() {
        String ISBN = ISBNAddField.getText();
        String title = titleAddField.getText();
        String author = authorAddField.getText();
        int year = Integer.parseInt(yearAddField.getText());

        Book book = new Book(ISBN, title, author, year);

        int addedRows = databaseUtils.addBook(book);
        showInformationDialog(addedRows);
        fetchDataFromDatabase();
    }

    private void showInformationDialog(int addedRows) {
        Alert alert;
        if (addedRows > 0) {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Adding Result");
            alert.setHeaderText(null);
            alert.setContentText("Book added successfully!");
        } else {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Error while adding book");
        }

        alert.showAndWait();
    }

    @FXML
    private void fetchDataFromDatabase() {
        mainApp.updateBookData();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void initializeSearchEngine() {
        FilteredList<Book> filteredBooks = new FilteredList<>(mainApp.getBookData(), p -> true);

        authorSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredBooks.setPredicate(book -> book.getAuthor().toLowerCase().contains(authorSearch.getText().toLowerCase())
                    && book.getISBN().contains(ISBNSearch.getText()));
        }));

        ISBNSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
            filteredBooks.setPredicate(book -> book.getAuthor().toLowerCase().contains(authorSearch.getText().toLowerCase())
                    && book.getISBN().contains(ISBNSearch.getText()));
        }));

        SortedList<Book> sortedBooks = new SortedList<>(filteredBooks);

        sortedBooks.comparatorProperty().bind(books.comparatorProperty());

        books.setItems(sortedBooks);
    }
}
