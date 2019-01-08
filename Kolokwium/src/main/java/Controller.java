import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    private Client client;
    private MainApp mainApp;

    @FXML
    private GridPane board;

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    public void read(String message) {
    }

    public void initializeClickHandlers() {
        for (Node element : board.getChildren()) {
            element.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                int row = GridPane.getRowIndex(element);
                int col = GridPane.getColumnIndex(element);
                mainApp.handle(row, col);
            });
        }
    }
}
