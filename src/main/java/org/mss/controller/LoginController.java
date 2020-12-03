package org.mss.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mss.controller.admin.AdminController;
import org.mss.controller.customer.CustomerController;
import org.mss.controller.user.UserController;
import org.mss.db.DBConnection;
import org.mss.db.LoginToDB;


public class LoginController implements Initializable {


    @FXML
    private JFXButton loginbutton;

    @FXML
    private PasswordField password;

    @FXML
    private JFXComboBox<String> roleCombobox;

    @FXML
    private TextField username;

    private DBConnection dbConnection;
    private LoginToDB loginToDB;
    private boolean isLogin;


    @FXML
    void switchToCustomer(ActionEvent event) {
        loginToDB = new LoginToDB(dbConnection.getConnectionToDB(), username.getText(), password.getText(), roleCombobox.getValue());
        try {
            isLogin = loginToDB.isLoginToDB();
            if (isLogin) {
                if (roleCombobox.getValue().equals("Customer")) {
                    customerLogin(event);
                }else if (roleCombobox.getValue().equals("Administrator")) {
                    adminLogin(event);
                }else if (roleCombobox.getValue().equals("User")) {
                    userLogin(event);
                }
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

    public void adminLogin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mss/view/adminLogin.fxml"));
        Parent root = fxmlLoader.load();
        AdminController adminController = fxmlLoader.getController();
        adminController.setAccountDetails(dbConnection, username.getText(), password.getText(), roleCombobox.getValue());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public void customerLogin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mss/view/customerLogin.fxml"));
        Parent root = fxmlLoader.load();
        CustomerController customerController = fxmlLoader.getController();
        customerController.setAccountDetails(dbConnection, username.getText(), password.getText(), roleCombobox.getValue());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Customer Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    public void userLogin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mss/view/userLogin.fxml"));
        Parent root = fxmlLoader.load();
        UserController userController = fxmlLoader.getController();
        userController.setAccountDetails(dbConnection, username.getText(), password.getText(), roleCombobox.getValue());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("User Dashboard");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        dbConnection.getConnectionToDB();
    }

    @FXML
     void switchToSignup(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/mss/view/signup.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Signup An Account");
        stage.setScene(scene);
        stage.show();
    }
}
