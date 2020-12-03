package org.mss.controller;

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
import javafx.stage.Stage;
import org.mss.controller.customer.CustomerController;
import org.mss.db.DBConnection;
import org.mss.type.cardType;
import org.mss.type.roleType;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

public class SignupController implements Initializable {

    @FXML
    private PasswordField PasswordField;

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
    private TextField PostNumberField;

    @FXML
    private TextField CityField;

    @FXML
    private JFXButton LoginButton;

    @FXML
    private TextField CreditCardNumberField;

    @FXML
    private TextField ExpirationDateField;

    @FXML
    private TextField CreditCardHolderNameField;

    @FXML
    private JFXComboBox<?> SubscriptionTypeCombobox;

    @FXML
    private JFXComboBox<?> CardTypeCombobox;

    @FXML
    private JFXButton SignupButton;

    @FXML
    private TextField CouponField;

    @FXML
    private TextField PaymentAmountField;

    private DBConnection dbConnection;
    private String subscriptionType;
    private int subscriptionTypeId;
    private int creditCardInformationId;
    private String couponCode;
    private int couponId;
    private int discountPercentage;
    private int paymentAmount;
    private int price;
    private String startDate;
    private String expirationDate;
    private int userLoginId;
    private int subscriptionPlanId;


    @FXML
    void signupAnAccount(ActionEvent event) throws SQLException, IOException {
        if (isAllInformationProvided()) {
            setSubscriptionPlanInformation();
            setPersonalInformationProvided();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mss/view/customerLogin.fxml"));
            Parent root = fxmlLoader.load();
            CustomerController customerController = fxmlLoader.getController();
            customerController.setAccountDetails(dbConnection, EmailField.getText(), PasswordField.getText(), "Customer");
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Customer Dashboard");
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    void switchToLogin(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/mss/view/login.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Movie Streaming Service");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dbConnection = new DBConnection();
        dbConnection.getConnectionToDB();
    }

    public boolean isAllInformationProvided() {
        if (isPersonalInformationProvided() && isSubscriptionPlanInformationProvided()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPersonalInformationProvided() {
        if (
                NameField.getText().isEmpty() ||
                PhoneNumberField.getText().isEmpty() ||
                EmailField.getText().isEmpty() ||
                PasswordField.getText().isEmpty()
        ) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isSubscriptionPlanInformationProvided() {
        if (
                CreditCardNumberField.getText().isEmpty() ||
                ExpirationDateField.getText().isEmpty() ||
                CreditCardHolderNameField.getText().isEmpty() ||
                PaymentAmountField.getText().isEmpty()
        ) {
            return false;
        } else {
            return true;
        }
    }

    public void setPersonalInformationProvided() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO userlogin " +
                    "(name, date_of_birth, phone_number, address, email, password, role)" +
                    "VALUES " +
                    "(?, ?, ?, ?, ?, ?, ?::enum_list_role)"
        );

        preparedStatement.setString(1, NameField.getText());
        preparedStatement.setDate(2, java.sql.Date.valueOf(DateOfBirthField.getText()));
        preparedStatement.setString(3, PhoneNumberField.getText());
        preparedStatement.setString(4, AddressField.getText());
        preparedStatement.setString(5, EmailField.getText());
        preparedStatement.setString(6, PasswordField.getText());
        preparedStatement.setString(7, roleType.Customer.toString());

        preparedStatement.executeUpdate();
        setCustomerDetails();

    }

    public void setCustomerDetails() throws SQLException {
        getUserLoginId();
        getSubscriptionPlanId();
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO Customer " +
                    "(user_login_id, post_number, city, subscription_plan_id)" +
                    "VALUES " +
                    "(?, ?, ?, ?)"
        );

        preparedStatement.setInt(1, userLoginId);
        preparedStatement.setInt(2, Integer.parseInt(PostNumberField.getText()));
        preparedStatement.setString(3, CityField.getText());
        preparedStatement.setInt(4, subscriptionPlanId);

        preparedStatement.executeUpdate();

    }

    public void getSubscriptionPlanId() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM SubscriptionPlan WHERE subscription_type_id = ? AND payment_amount = ? AND credit_card_information_id = ?"
        );

        preparedStatement.setInt(1, subscriptionTypeId);

        preparedStatement.setInt(2, paymentAmount);
        preparedStatement.setInt(3, creditCardInformationId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            subscriptionPlanId = resultSet.getInt("subscription_plan_id");
        }
    }

    public void setSubscriptionPlanInformation() throws SQLException {

        getSubscriptionTypeId();
        getCouponId();
        setCreditCardInformation();

        if (!couponCode.isEmpty()) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO SubscriptionPlan " +
                        "(subscription_type_id, coupon_id, start_date, expiration_date, payment_amount, credit_card_information_id)" +
                        "VALUES " +
                        "(?, ?, ?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, subscriptionTypeId);
            preparedStatement.setInt(2, couponId);
            getStartDateAndExpirationDate();
            preparedStatement.setDate(3, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(4, java.sql.Date.valueOf(expirationDate));
            preparedStatement.setInt(5, paymentAmount);
            preparedStatement.setInt(6, creditCardInformationId);

            preparedStatement.executeUpdate();

        } else {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO SubscriptionPlan " +
                        "(subscription_type_id, start_date, expiration_date, payment_amount, credit_card_information_id)" +
                        "VALUES " +
                        "(?, ?, ?, ?, ?)"
            );

            preparedStatement.setInt(1, subscriptionTypeId);
            getStartDateAndExpirationDate();
            preparedStatement.setDate(2, java.sql.Date.valueOf(startDate));
            preparedStatement.setDate(3, java.sql.Date.valueOf(expirationDate));
            preparedStatement.setInt(4, paymentAmount);
            preparedStatement.setInt(5, creditCardInformationId);

            preparedStatement.executeUpdate();
        }


    }

    public void setCreditCardInformation() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO creditcardinformation " +
                    "(card_type, credit_card_number, expiration_date, credit_card_holder_name)" +
                    "VALUES " +
                    "(?::enum_list_credit_card, ?, ?, ?)"
        );

        if (CardTypeCombobox.getValue().equals("Visa")) {
            preparedStatement.setString(1, cardType.Visa.toString());
        } else if (CardTypeCombobox.getValue().equals("Mastercard")) {
            preparedStatement.setString(1, cardType.Mastercard.toString());
        } else if (CardTypeCombobox.getValue().equals("Svenska Express")) {
            preparedStatement.setString(1, cardType.SvenskaExpress.toString());
        }

        preparedStatement.setString(2, CreditCardNumberField.getText());
        preparedStatement.setString(3, ExpirationDateField.getText());
        preparedStatement.setString(4, CreditCardHolderNameField.getText());

        preparedStatement.executeUpdate();

        getcreditCardInformationId();

    }

    public void getUserLoginId() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM UserLogin WHERE name = ? AND email = ? AND password = ?"
        );
        preparedStatement.setString(1, NameField.getText());
        preparedStatement.setString(2, EmailField.getText());
        preparedStatement.setString(3, PasswordField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userLoginId = resultSet.getInt("user_login_id");
        }
    }

    public void getcreditCardInformationId() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM creditcardinformation WHERE credit_card_number = ? AND credit_card_holder_name = ?"
        );
        preparedStatement.setString(1, CreditCardNumberField.getText());
        preparedStatement.setString(2, CreditCardHolderNameField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            creditCardInformationId = resultSet.getInt("credit_card_information_id");
        }
    }

    public void getSubscriptionTypeId() throws SQLException {
        String subscriptionTypeCombobox = (String) SubscriptionTypeCombobox.getValue();
        if (subscriptionTypeCombobox.contains("Individual")) {
            subscriptionType = "Individual";
        } else if (subscriptionTypeCombobox.contains("Couples")) {
            subscriptionType = "Couples";
        } else if (subscriptionTypeCombobox.contains("Family")) {
            subscriptionType = "Family";
        }

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM SubscriptionType WHERE type = ?"
        );
        preparedStatement.setString(1, subscriptionType);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            subscriptionTypeId = resultSet.getInt("subscription_type_id");
            price = resultSet.getInt("price");
        }

    }

    public void getCouponId() throws SQLException {
        couponCode = CouponField.getText();
        if (!couponCode.isEmpty()) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "SELECT * FROM Coupons WHERE coupon_code = ?"
            );
            preparedStatement.setString(1, couponCode);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                couponId = resultSet.getInt("coupon_id");
                discountPercentage = resultSet.getInt("discount_percentage");
                System.out.println(couponId);
                System.out.println(discountPercentage);
            }
        } else {
            discountPercentage = 0;
        }

    }

    public void getStartDateAndExpirationDate() {
        paymentAmount = Integer.parseInt(PaymentAmountField.getText());
        float priceAfterDiscount = price - ((discountPercentage * price)/100);
        int NumberOfDaysOfSubscription = (int) (30 * (paymentAmount/priceAfterDiscount));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, NumberOfDaysOfSubscription);

        Date start_date = new Date();
        startDate = simpleDateFormat.format(start_date);

        Date expiration_date = calendar.getTime();
        expirationDate = simpleDateFormat.format(expiration_date);
    }
}