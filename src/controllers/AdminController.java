package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import user.User;

public class AdminController extends BasicController {

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Button logoutButton;

    @FXML
    private Label selectedUser;

    @FXML
    private Button setAdminButton;

    @FXML
    private Button setPremiumButton;

    @FXML
    private ListView<User> userList;

    @FXML
    private Label userNameLabel;

    @FXML
    void initialize() {
        // Create a Circle with the desired radius
        // choose either imageView.getFitWidth() or imageView.getFitHeight() depending
        // on which is smaller

        int radius = (int) Math.min(imageView.getFitWidth(), imageView.getFitHeight()) / 2;
        Circle clip = new Circle(radius);

        // Set the center of the Circle to the center of the ImageView
        clip.centerXProperty().bind(imageView.fitWidthProperty().divide(2));
        clip.centerYProperty().bind(imageView.fitHeightProperty().divide(2));

        // Set the clip property of the ImageView to the Circle
        imageView.setClip(clip);
    }

    @FXML
    void selectUser(ActionEvent event) {
        if (userList.getSelectionModel().getSelectedItem() != null) {
            userNameLabel.setText(userList.getSelectionModel().getSelectedItem().getName());
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        // TODO: Delete user
    }

    @FXML
    void logout(ActionEvent event) {
        // TODO: Logout
        windowManager.switchToScene("primaryStage", "loginScene");
    }

    @FXML
    void setAdmin(ActionEvent event) {
        // TODO: Set user as admin
    }

    @FXML
    void setPremium(ActionEvent event) {
        // TODO: Set user as premium

    }

    @Override
    public void setTitle() {
        stage.setTitle("Admin");
    }
}
