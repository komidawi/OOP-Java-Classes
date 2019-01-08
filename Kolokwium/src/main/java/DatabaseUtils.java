import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseUtils {
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

    public int updateLeaderboard(Record userRecord) {
        int updatedCount = 0;
        try {
            String insertionQuery = "INSERT INTO leaderboard VALUES(?, ?)";
            statement = connection.prepareStatement(insertionQuery);

            // Using PreparedStatement to prevent from SQL injections and increase performance
            PreparedStatement preparedStatement = (PreparedStatement) statement;
            preparedStatement.setString(1, userRecord.getNickname());
            preparedStatement.setInt(2, userRecord.getScore());

            updatedCount = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updatedCount;
    }

    public List<Record> fetchRecords() {
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM leaderboard");

            List<Record> records = new ArrayList<>();

            while (resultSet.next()) {
                String username = resultSet.getString(1);
                int score = resultSet.getInt(2);

                Record record = new Record(username, score);
                records.add(record);
            }

            return records;

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
