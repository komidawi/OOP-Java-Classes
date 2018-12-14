import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ChatWindowController {

    @FXML
    private TextArea allMessagesArea;

    @FXML
    private TextField clientMessageField;

    @FXML
    private Button sendClientMessageButton;

    private MessengerClient client;

    @FXML
    private void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendClientMessage();
        }
    }

    @FXML
    private void sendClientMessage() {
        client.sendMessage(clientMessageField.getText());
        clientMessageField.setText("");
    }

    public void readMessage(String message) {
        allMessagesArea.appendText(message + "\n");
    }

    public void setClient(MessengerClient client) {
        this.client = client;
    }
}
