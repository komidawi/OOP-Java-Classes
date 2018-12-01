import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDatabaseUtils {
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public boolean connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection("jdbc:mysql://mysql.agh.edu.pl/dawidk",
                    "dawidk", "ZKsQAvSSDhJ9nUky");
            return true;
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int addBook(Book book) {
        int addedBooks = 0;
        try {
            String insertionQuery = "INSERT INTO books VALUES(?, ?, ?, ?)";
            statement = connection.prepareStatement(insertionQuery);

            // Using PreparedStatement to prevent from SQL injections and increase performance
            PreparedStatement preparedStatement = (PreparedStatement) statement;
            preparedStatement.setString(1, book.getISBN());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setInt(4, book.getYear());

            addedBooks = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addedBooks;
    }

    public List<Book> fetchBookData() {
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM books");

            List<Book> books = new ArrayList<>();

            while (resultSet.next()) {
                String ISBN = resultSet.getString(1);
                String title = resultSet.getString(2);
                String author = resultSet.getString(3);
                int year = resultSet.getInt(4);

                Book book = new Book(ISBN, title, author, year);
                books.add(book);
            }

            return books;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet = null;
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    statement = null;
                }
            }
        }
        return null;
    }

    public boolean isConnected() {
        return connection != null;
    }
}
