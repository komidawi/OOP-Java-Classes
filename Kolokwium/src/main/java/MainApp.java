import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private Client client;
    private Controller controller;
    private DatabaseUtils databaseUtils = new DatabaseUtils();

    private enum GameState {
        PLAYING, DRAW, CROSS_WON, CIRCLE_WON
    }

    private GameState currentState;

    private enum Seed {
        EMPTY, CROSS, CIRCLE
    }

    private Seed currentPlayer;

    private final int ROWS = 3;
    private final int COLUMNS = 3;

    private Seed[][] board = new Seed[ROWS][COLUMNS];

    @Override
    public void start(Stage primaryStage) throws Exception {
        initializePrimaryStage(primaryStage);
        initializeLayout();
        initializeClient();
        initializeGame();
        attemptToConnect();
    }

    private void initializeClient() {
        try {
            client = new Client("localhost", 3000);
            client.setupConnection();
            client.setController(controller);
            controller.setClient(client);
            controller.setMainApp(this);
            controller.initializeClickHandlers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Tic Tac Toe");
    }

    private void initializeLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Layout.fxml"));
            rootLayout = loader.load();
            controller = loader.getController();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void attemptToConnect() {
        int maxConnectionAttempts = 3;
        boolean connected;
        for (int i = 0; i < maxConnectionAttempts; i++) {
            connected = databaseUtils.connect();

            if (connected) {
                return;
            }
        }
    }

    @Override
    public void stop() throws Exception {
        client.closeConnection();
    }

    private void initializeGame() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                board[row][col] = Seed.EMPTY;
            }
        }
        currentState = GameState.PLAYING;
        currentPlayer = Seed.CROSS;
    }

    public void handle(int rowSelected, int colSelected) {
        if (currentState == GameState.PLAYING) {
            if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                    && colSelected < COLUMNS && board[rowSelected][colSelected] == Seed.EMPTY) {
                board[rowSelected][colSelected] = currentPlayer;
                updateGame(currentPlayer, rowSelected, colSelected);
                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.CIRCLE : Seed.CROSS;
            }
        } else {
            initializeGame();
        }
    }

    private boolean isDraw() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                if (board[row][col] == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateGame(Seed theSeed, int rowSelected, int colSelected) {
        if (hasWon(theSeed, rowSelected, colSelected)) {
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.CIRCLE_WON;
        } else if (isDraw()) {
            currentState = GameState.DRAW;
        }
    }

    private boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        return (board[rowSelected][0] == theSeed
                && board[rowSelected][1] == theSeed
                && board[rowSelected][2] == theSeed

                || board[0][colSelected] == theSeed
                && board[1][colSelected] == theSeed
                && board[2][colSelected] == theSeed

                || rowSelected == colSelected
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed

                || rowSelected + colSelected == 2
                && board[0][2] == theSeed
                && board[1][1] == theSeed
                && board[2][0] == theSeed);
    }
}
