package org.mss.controller.customer;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.mss.controller.data.UserProfileDetails;
import org.mss.db.DBConnection;
import org.mss.type.cardType;
import org.mss.type.roleType;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;


public class CustomerController {

    @FXML
    private TextField PasswordField;

    @FXML
    private TextField NameField;

    @FXML
    private TextField DateOfBirthField;

    @FXML
    private TextField PhoneNumberField;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField EmailField;

    @FXML
    private JFXButton LogoutButton;

    @FXML
    private JFXButton UpdateButton;

    @FXML
    private JFXButton LoadButton;

    @FXML
    private JFXButton UpdateMySubscriptionPlanButton;

    @FXML
    private JFXButton LoadMySubscriptionPlanButton;

    @FXML
    private JFXComboBox<String> NewSubscriptionTypeCombobox;

    @FXML
    private TextField CouponField;

    @FXML
    private TextField PaymentAmountField;

    @FXML
    private TextField ExpirationDateSubscriptionPlanField;

    @FXML
    private TextField StartDateSubscriptionPlanField;

    @FXML
    private TextField CurrentSubscriptionTypeField;

    @FXML
    private TextField CreditCardNumberField;

    @FXML
    private TextField ExpirationDateCreditCardField;

    @FXML
    private TextField CreditCardHolderNameField;

    @FXML
    private JFXComboBox<String> CardTypeCombobox;

    @FXML
    private JFXButton UpdateMyCreditCardInformationButton;

    @FXML
    private JFXButton LoadMyCreditCardInformationButton;

    @FXML
    private TableView<UserProfileDetails> MyUserProfilesTable;

    @FXML
    private TableColumn<UserProfileDetails, String> NameColumn;

    @FXML
    private TableColumn<UserProfileDetails, Date> DateOfBirthColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PhoneNumberColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> AddressColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> EmailColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PasswordColumn;

    @FXML
    private JFXButton UpdateMyUserProfilesButton;

    @FXML
    private JFXButton LoadMyUserProfilesButton;

    @FXML
    private TextField NameMyUserProfilesField;

    @FXML
    private TextField DateofBirthMyUserProfilesField;

    @FXML
    private TextField PhoneNumberMyUserProfilesField;

    @FXML
    private TextField AddressMyUserProfilesField;

    @FXML
    private TextField EmailMyUserProfilesField;

    @FXML
    private TextField PasswordMyUserProfilesField;

    @FXML
    private JFXButton AddMyUserProfilesButton;

    private DBConnection dbConnection;
    private String Username;
    private String Password;
    private String Role;
    private int UserId;
    private int CustomerId;
    private int subscriptionPlanId;
    private int subscriptionTypeId;
    private int creditCardInformationId;
    private ObservableList<UserProfileDetails> userProfileDetailsObservableList;
    private ArrayList<Integer> userProfileLoginIdArrayList = new ArrayList<>();;
    private int numberOfUserProfile;
    private int UserLoginIdLastUserProfileAdded;
    private int UserLoginIdUserProfileDeleted;

    @FXML
    void Logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/org/mss/view/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Movie Streaming Service");
        stage.setScene(scene);
        stage.show();
    }

    public void setAccountDetails(DBConnection dbConnection, String username, String password, String role) throws SQLException {
        this.dbConnection = dbConnection;
        this.Username = username;
        this.Password = password;
        this.Role = role;

        getAccountDetails();
        getSubscriptionPlanInformation();
        getCreditCardInformation();
        getUserProfileDetails();

    }

    private void getAccountDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM userlogin WHERE email = ? AND password = ? And role = ?::\"enum_list_role\"");
        preparedStatement.setString(1, Username);
        preparedStatement.setString(2, Password);
        preparedStatement.setString(3, Role);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.UserId = resultSet.getInt("user_login_id");
            NameField.setText(resultSet.getString("name"));
            DateOfBirthField.setText(resultSet.getString("date_of_birth"));
            PhoneNumberField.setText(resultSet.getString("phone_number"));
            AddressField.setText(resultSet.getString("address"));
            EmailField.setText(resultSet.getString("email"));
            PasswordField.setText(resultSet.getString("password"));
        }
    }

    @FXML
    void updateAccountDetails(ActionEvent event) throws IOException, SQLException, ParseException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "UPDATE userlogin " +
                    "SET name = ? " +
                    ", date_of_birth = ? " +
                    ", phone_number = ? " +
                    ", address = ? " +
                    ", email = ?" +
                    ", password = ?" +
                    "WHERE email = ? AND password = ? " +
                    "And role = ?::\"enum_list_role\"");
        preparedStatement.setString(1, NameField.getText());
        preparedStatement.setDate(2, java.sql.Date.valueOf(DateOfBirthField.getText()));
        preparedStatement.setString(3, PhoneNumberField.getText());
        preparedStatement.setString(4, AddressField.getText());
        preparedStatement.setString(5, EmailField.getText());
        preparedStatement.setString(6, PasswordField.getText());
        preparedStatement.setString(7, Username);
        preparedStatement.setString(8, Password);
        preparedStatement.setString(9, Role);

        this.Username = EmailField.getText();
        this.Password = PasswordField.getText();

        preparedStatement.executeUpdate();

    }

    @FXML
    void loadAccountDetails(ActionEvent event) throws SQLException {
        getAccountDetails();
    }

    @FXML
    void loadSubscriptionPlanInformation(ActionEvent event) throws SQLException {
        getSubscriptionPlanInformation();
    }

    @FXML
    void loadCreditCardInformation(ActionEvent event) throws SQLException {
        getCreditCardInformation();
    }

    private void getCreditCardInformation() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM CreditCardInformation WHERE credit_card_information_id = ?");
        preparedStatement.setInt(1, creditCardInformationId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            CardTypeCombobox.setValue(resultSet.getString("card_type"));
            CreditCardNumberField.setText(resultSet.getString("credit_card_number"));
            ExpirationDateCreditCardField.setText(resultSet.getString("expiration_date"));
            CreditCardHolderNameField.setText(resultSet.getString("credit_card_holder_name"));
        }

    }

    private void getSubscriptionTypeInformation() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM SubscriptionType WHERE subscription_type_id = ?");
        preparedStatement.setInt(1, subscriptionTypeId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            CurrentSubscriptionTypeField.setText(resultSet.getString("type"));
        }
    }

    private void getSubscriptionPlanInformation() throws SQLException {
        getsubscriptionPlanIdAndCustomerId();

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM SubscriptionPlan WHERE subscription_plan_id = ?");
        preparedStatement.setInt(1, subscriptionPlanId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.subscriptionTypeId = resultSet.getInt("subscription_type_id");
            getSubscriptionTypeInformation();
            StartDateSubscriptionPlanField.setText(String.valueOf(resultSet.getDate("start_date")));
            ExpirationDateSubscriptionPlanField.setText(String.valueOf(resultSet.getDate("expiration_date")));
            this.creditCardInformationId = resultSet.getInt("credit_card_information_id");
        }
    }

    private void getsubscriptionPlanIdAndCustomerId() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Customer WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.subscriptionPlanId = resultSet.getInt("subscription_plan_id");
            this.CustomerId = resultSet.getInt("customer_id");
        }
    }

    @FXML
    void updateCreditCardInformation(ActionEvent event) throws IOException, SQLException, ParseException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "UPDATE CreditCardInformation " +
                        "SET card_type = ?::enum_list_credit_card " +
                        ", credit_card_number = ? " +
                        ", expiration_date = ? " +
                        ", credit_card_holder_name = ? " +
                        "WHERE credit_card_information_id = ? ");

        if (CardTypeCombobox.getValue().equals("Visa")) {
            preparedStatement.setString(1, cardType.Visa.toString());
        } else if (CardTypeCombobox.getValue().equals("Mastercard")) {
            preparedStatement.setString(1, cardType.Mastercard.toString());
        } else if (CardTypeCombobox.getValue().equals("Svenska Express")) {
            preparedStatement.setString(1, cardType.SvenskaExpress.toString());
        }
        preparedStatement.setString(2, CreditCardNumberField.getText());
        preparedStatement.setString(3, ExpirationDateCreditCardField.getText());
        preparedStatement.setString(4, CreditCardHolderNameField.getText());
        preparedStatement.setInt(5, creditCardInformationId);

        preparedStatement.executeUpdate();
    }

    private void getUserProfileDetails() throws SQLException {
        this.userProfileDetailsObservableList = FXCollections.observableArrayList();
        this.userProfileLoginIdArrayList.clear();
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserProfile WHERE customer_id = ?");
        preparedStatement.setInt(1, CustomerId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userProfileLoginIdArrayList.add(resultSet.getInt("user_login_id"));
        }

        numberOfUserProfile = userProfileLoginIdArrayList.size();
        getUserProfileDetailsIntoUserProfilesTable();
    }

    private void getUserProfileDetailsIntoUserProfilesTable() throws SQLException {
        for (int i = 0; i < numberOfUserProfile; i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE user_login_id = ?");
            preparedStatement.setInt(1, userProfileLoginIdArrayList.get(i));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                this.userProfileDetailsObservableList.add(new UserProfileDetails(
                        resultSet.getString("name"),
                        resultSet.getDate("date_of_birth"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role")
                        ));
            }
            NameColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("name"));
            DateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, Date>("dateOfBirth"));
            PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("phoneNumber"));
            AddressColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("address"));
            EmailColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("email"));
            PasswordColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("password"));
            MyUserProfilesTable.setItems(userProfileDetailsObservableList);
        }
    }

    @FXML
    void loadUserProfileDetails(ActionEvent event) throws SQLException {
        getUserProfileDetails();
    }

    @FXML
    void addUserProfileDetails() throws SQLException {
        if (numberOfUserProfile < 3) {
            addUserProfileDetailsIntoUserLogin();
            getUserProfileDetails();
        }
    }

    private void addUserProfileDetailsIntoUserProfile() throws SQLException {
        getUserLoginIdLastUserProfileAdded();
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO UserProfile " +
                    "(user_login_id, customer_id)" +
                    "VALUES " +
                    "(?, ?)"
        );

        preparedStatement.setInt(1, UserLoginIdLastUserProfileAdded);
        preparedStatement.setInt(2, CustomerId);
        preparedStatement.executeUpdate();

    }

    private void getUserLoginIdLastUserProfileAdded() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameMyUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthMyUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberMyUserProfilesField.getText());
        preparedStatement.setString(4, AddressMyUserProfilesField.getText());
        preparedStatement.setString(5, EmailMyUserProfilesField.getText());
        preparedStatement.setString(6, PasswordMyUserProfilesField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.UserLoginIdLastUserProfileAdded = resultSet.getInt("user_login_id");
            userProfileLoginIdArrayList.add(UserLoginIdLastUserProfileAdded);
        }

        numberOfUserProfile = userProfileLoginIdArrayList.size();
    }

    private void addUserProfileDetailsIntoUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO UserLogin " +
                    "(name, date_of_birth, phone_number, address, email, password, role)" +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?::enum_list_role)"
        );

        preparedStatement.setString(1, NameMyUserProfilesField.getText());
        preparedStatement.setDate(2, java.sql.Date.valueOf(DateofBirthMyUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberMyUserProfilesField.getText());
        preparedStatement.setString(4, AddressMyUserProfilesField.getText());
        preparedStatement.setString(5, EmailMyUserProfilesField.getText());
        preparedStatement.setString(6, PasswordMyUserProfilesField.getText());
        preparedStatement.setString(7, roleType.User.toString());

        preparedStatement.executeUpdate();

        addUserProfileDetailsIntoUserProfile();
    }

    @FXML
    void removeUserProfileDetails() throws SQLException {
        if (numberOfUserProfile >= 1 && numberOfUserProfile <= 3) {
            getUserLoginIdUserProfileDeleted();
            removeUserProfileDetailsFromUserProfile();
            removeUserProfileDetailsFromUserLogin();
            getUserProfileDetails();
        }
    }

    @FXML
    void removeCustomerProfile(ActionEvent event) throws SQLException, IOException {
        int numberOfUserProfileBeforeRemoveCustomerProfile = numberOfUserProfile;
        for (int i = numberOfUserProfileBeforeRemoveCustomerProfile - 1; i >= 0; i--) {
            UserLoginIdUserProfileDeleted = userProfileLoginIdArrayList.get(i);
            removeUserProfileDetailsFromUserProfile();
            removeUserProfileFromUserLogin();
        }

        removeCustomerDetailsFromCustomer();
        removeCustomerDetailsFromUserLogin();

        Parent root = FXMLLoader.load(getClass().getResource("/org/mss/view/login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Movie Streaming Service");
        stage.setScene(scene);
        stage.show();
    }

    private void removeUserProfileDetailsFromUserProfile() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserProfile WHERE user_login_id = ? AND customer_id = ? ");
        preparedStatement.setInt(1, UserLoginIdUserProfileDeleted);
        preparedStatement.setInt(2, CustomerId);

        preparedStatement.executeUpdate();
    }

    private void getUserLoginIdUserProfileDeleted() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameMyUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthMyUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberMyUserProfilesField.getText());
        preparedStatement.setString(4, AddressMyUserProfilesField.getText());
        preparedStatement.setString(5, EmailMyUserProfilesField.getText());
        preparedStatement.setString(6, PasswordMyUserProfilesField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.UserLoginIdUserProfileDeleted = resultSet.getInt("user_login_id");
        }

    }

    private void removeUserProfileDetailsFromUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameMyUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthMyUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberMyUserProfilesField.getText());
        preparedStatement.setString(4, AddressMyUserProfilesField.getText());
        preparedStatement.setString(5, EmailMyUserProfilesField.getText());
        preparedStatement.setString(6, PasswordMyUserProfilesField.getText());

        preparedStatement.executeUpdate();

        userProfileLoginIdArrayList.remove(UserLoginIdUserProfileDeleted);

        numberOfUserProfile = userProfileLoginIdArrayList.size();
    }

    private void removeCustomerDetailsFromCustomer() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM Customer WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserId);

        preparedStatement.executeUpdate();
    }

    private void removeCustomerDetailsFromUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserId);

        preparedStatement.executeUpdate();
    }

    private void removeUserProfileFromUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserLoginIdUserProfileDeleted);

        preparedStatement.executeUpdate();

        userProfileLoginIdArrayList.remove(UserLoginIdUserProfileDeleted);

        numberOfUserProfile = userProfileLoginIdArrayList.size();
    }

}
