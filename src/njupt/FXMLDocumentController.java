package njupt;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

/**
 * 界面逻辑控制器，
 */
@SuppressWarnings("unused")
public class FXMLDocumentController implements Initializable {
    private Stage stage;
    private File fileOpened;
    @FXML
    private TextArea textArea;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    void setStage(Stage stage) {
        this.stage = stage;
        openFile(null);
    }

    private void openFile(File file) {
        fileOpened = file;
        if (fileOpened == null) {
            stage.setTitle("<NULL>");
        } else {
            stage.setTitle(fileOpened.getAbsolutePath());
        }
    }

    private void readFile(File file) {
        if (file == null) {
            textArea.setText("");
            return;
        }
        try {
            textArea.setText(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath()))));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("file open error: " + e.getMessage());
            alert.show();
        }
    }

    private void saveFileAs(File file) {
        try {
            Files.write(Paths.get(file.getAbsolutePath()), textArea.getText().getBytes());
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("file write error: " + e.getMessage());
            alert.show();
        }
        openFile(file);
    }

    @FXML
    private void onFileNewClick(ActionEvent event) {
        openFile(null);
        readFile(null);
    }

    @FXML
    private void onFileOpenClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) {
            // 选择文件被取消才会是null,
            return;
        }
        openFile(file);
        readFile(file);
    }

    @FXML
    private void onFileSaveClick(ActionEvent event) {
        if (fileOpened == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("no file opened");
            alert.show();
            return;
        }
        saveFileAs(fileOpened);
    }

    @FXML
    private void onFileSaveAsClick(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        saveFileAs(fileChooser.showSaveDialog(stage));
    }

    @FXML
    private void onFileQuitClick(ActionEvent event) {
        Platform.exit();
    }
}
