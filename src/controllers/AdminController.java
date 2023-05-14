package controllers;

import exceptions.noPermissionException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

import user.User;
import user.UserType;

public class AdminController extends BasicController {

    @FXML
    private Button deleteButton;

    @FXML
    private ImageView imageView;

    @FXML
    private Button logoutButton;

    @FXML
    private Label selectedUserLabel;

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

        userList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Update the label with the selected user's information
                selectedUserLabel.setText(newValue.getUserName());
            } else {
                // Clear the label if no user is selected
                selectedUserLabel.setText("");
            }
        });

    }

    @FXML
    void deleteUser(ActionEvent event) {
        User toDelete = userList.getSelectionModel().getSelectedItem();

        if (toDelete == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user to delete");
            alert.showAndWait();
        }

        if (toDelete != null) {
            System.out.println(sessionManager.getCurrentUsername() + " is deleting " + toDelete.getUserName());
            System.out.println("Current user is " + sessionManager.getCurrentUser().getUserType());

            Task<Boolean> deleteTask = new Task<>() {
                @Override
                protected Boolean call() {
                    try {
                        sessionManager.deleteUser(toDelete, sessionManager.getCurrentUser());
                        return true;
                    } catch (noPermissionException e) {
                        return false;
                    }
                }
            };

            deleteTask.setOnSucceeded(otherEvent -> {
                if (deleteTask.getValue()) {
                    System.out.println("user deleted");
                    fillGUI();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Deletion Error");
                    alert.setContentText("You do not have permission to delete this user");
                    alert.showAndWait();
                }
            });

            Thread thread = new Thread(deleteTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    void logout(ActionEvent event) {
        windowManager.switchToScene("primaryStage", "loginScene");
    }

    @FXML
    void setAdmin(ActionEvent event) {
        User toPromote = userList.getSelectionModel().getSelectedItem();

        if (toPromote == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user to delete");
            alert.showAndWait();
        }

        if (toPromote != null) {
            System.out.println(sessionManager.getCurrentUsername() + " is promoting " + toPromote.getUserName());
            System.out.println("Current user is " + sessionManager.getCurrentUser().getUserType());

            Task<Boolean> promoteTask = new Task<>() {
                @Override
                protected Boolean call() {
                    return sessionManager.changeUserType(toPromote, sessionManager.getCurrentUser(), UserType.ADMIN);
                }
            };

            promoteTask.setOnSucceeded(otherEvent -> {
                if (promoteTask.getValue()) {
                    System.out.println("user promoted to admin");
                    fillGUI();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Promotion Error");
                    alert.setContentText("You do not have permission to promote this user to admin");
                    alert.showAndWait();
                }
            });

            Thread thread = new Thread(promoteTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @FXML
    void setPremium(ActionEvent event) {
        User toPromote = userList.getSelectionModel().getSelectedItem();

        if (toPromote == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No user selected");
            alert.setContentText("Please select a user to delete");
            alert.showAndWait();
        }

        if (toPromote != null) {
            System.out.println(sessionManager.getCurrentUsername() + " is promoting " + toPromote.getUserName());
            System.out.println("Current user is " + sessionManager.getCurrentUser().getUserType());

            Task<Boolean> promoteTask = new Task<>() {
                @Override
                protected Boolean call() {
                    return sessionManager.changeUserType(toPromote, sessionManager.getCurrentUser(),
                            UserType.PREMIUM_USER);
                }
            };

            promoteTask.setOnSucceeded(otherEvent -> {
                if (promoteTask.getValue()) {
                    System.out.println("user promoted to premium user");
                    fillGUI();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Promotion Error");
                    alert.setContentText("You do not have permission to promote this user to premium user");
                    alert.showAndWait();
                }
            });

            Thread thread = new Thread(promoteTask);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public void setTitle() {
        stage.setTitle("Admin");
    }

    @Override
    public void fillGUI() {
        userNameLabel.setText(sessionManager.getCurrentUsername());
        ObservableList<User> users = FXCollections.observableArrayList(sessionManager.getUsers().values());
        userList.setItems(users);
    }
}
