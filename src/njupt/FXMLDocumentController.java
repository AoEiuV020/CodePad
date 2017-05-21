package njupt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * 界面逻辑控制器，
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void onFileQuitClick(ActionEvent event) {
        Platform.exit();
    }
}
