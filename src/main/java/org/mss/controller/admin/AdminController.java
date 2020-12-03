package org.mss.controller.admin;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.mss.controller.data.MovieDetails;
import org.mss.controller.data.PersonDetails;
import org.mss.controller.data.UserProfileDetails;
import org.mss.db.DBConnection;
import org.mss.type.roleType;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;

public class AdminController {

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
    private TextField PasswordField;

    @FXML
    private TextField PositionField;

    @FXML
    private Tab MediaTab;

    @FXML
    private TableView<MovieDetails> MediaTable;

    @FXML
    private TableColumn<MovieDetails, Integer> IDMediaColumn;

    @FXML
    private TableColumn<MovieDetails, String> TitleMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Date> YearMediaColumn;

    @FXML
    private TableColumn<MovieDetails, String> CountryMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> LengthMediaColumn;

    @FXML
    private TableColumn<MovieDetails, String> DirectorsMediaColumn;

    @FXML
    private TableColumn<MovieDetails, String> ActorsMediaColumn;

    @FXML
    private TableColumn<MovieDetails, String> GenresMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> RatingMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> ColorMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> BwMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> OriginalMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> MovieSequelIDMediaColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> MoviePrequelIDMediaColumn;

    @FXML
    private JFXButton AddMediaButton;

    @FXML
    private JFXButton RemoveMediaButton;

    @FXML
    private TextField TitleMediaField;

    @FXML
    private TextField YearMediaField;

    @FXML
    private TextField CountryMediaField;

    @FXML
    private TextField GenresMediaField;

    @FXML
    private TextField LengthMediaField;

    @FXML
    private TextField DirectorsMediaField;

    @FXML
    private TextField ActorsMediaField;

    @FXML
    private TextField RatingMediaField;

    @FXML
    private TextField ColorMediaField;

    @FXML
    private TextField BwMediaField;

    @FXML
    private TextField OriginalMediaField;

    @FXML
    private TextField MovieSequelIDMediaField;

    @FXML
    private TextField MoviePrequelIDMediaField;

    @FXML
    private JFXButton UpdateMediaButton;

    @FXML
    private TextField TitleRemoveField;

    @FXML
    private TextField YearRemoveField;

    @FXML
    private TextField TitleSearchField;

    @FXML
    private TextField YearSearchField;

    @FXML
    private TextField CountrySearchField;

    @FXML
    private TextField GenresSearchField;

    @FXML
    private TextField LengthSearchField;

    @FXML
    private TextField DirectorsSearchField;

    @FXML
    private TextField ActorsSearchField;

    @FXML
    private TextField RatingSearchField;

    @FXML
    private JFXButton SearchMediaButton;

    @FXML
    private JFXButton LoadMediaButton;

    @FXML
    private Tab DirectorsTab;

    @FXML
    private TableView<PersonDetails> DirectorsTable;

    @FXML
    private TableColumn<PersonDetails, String> NameDirectorsColumn;

    @FXML
    private TableColumn<PersonDetails, String> EmailDirectorsColumn;

    @FXML
    private TableColumn<PersonDetails, String> PhoneNumberDirectorsColumn;

    @FXML
    private TextField NameDirectorsField;

    @FXML
    private TextField EmailDirectorsField;

    @FXML
    private TextField PhoneNumberDirectorsField;

    @FXML
    private JFXButton LoadDirectorsButton;

    @FXML
    private JFXButton AddDirectorsButton;

    @FXML
    private JFXButton RemoveDirectorsButton;

    @FXML
    private JFXButton UpdateDirectorsButton;

    @FXML
    private Tab ActorsTab;

    @FXML
    private TableView<PersonDetails> ActorsTable;

    @FXML
    private TableColumn<PersonDetails, String> NameActorsColumn;

    @FXML
    private TableColumn<PersonDetails, String> EmailActorsColumn;

    @FXML
    private TableColumn<PersonDetails, String> PhoneNumberActorsColumn;

    @FXML
    private TextField NameActorsField;

    @FXML
    private TextField EmailActorsField;

    @FXML
    private TextField PhoneNumberActorsField;

    @FXML
    private JFXButton LoadActorsButton;

    @FXML
    private JFXButton AddActorsButton;

    @FXML
    private JFXButton RemoveActorsButton;

    @FXML
    private JFXButton UpdateActorsButton;

    @FXML
    private Tab CustomersTab;

    @FXML
    private TableView<UserProfileDetails> CustomersTable;

    @FXML
    private TableColumn<UserProfileDetails, String> NameCustomersColumn;

    @FXML
    private TableColumn<UserProfileDetails, Date> DateofBirthCustomersColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PhoneNumberCustomersColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> AddressCustomersColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> EmailCustomersColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PasswordCustomersColumn;

    @FXML
    private JFXButton LoadCustomersButton;

    @FXML
    private TextField NameCustomersField;

    @FXML
    private TextField DateofBirthCustomersField;

    @FXML
    private TextField PhoneNumberCustomersField;

    @FXML
    private TextField AddressCustomersField;

    @FXML
    private TextField EmailCustomersField;

    @FXML
    private TextField PasswordCustomersField;

    @FXML
    private JFXButton AddCustomersButton;

    @FXML
    private JFXButton RemoveCustomersButton;

    @FXML
    private JFXButton UpdateCustomersButton;

    @FXML
    private Tab UserProfilesTab;

    @FXML
    private TableView<UserProfileDetails> UserProfilesTable;

    @FXML
    private TableColumn<UserProfileDetails, String> NameUserProfilesColumn;

    @FXML
    private TableColumn<UserProfileDetails, Date> DateofBirthUserProfilesColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PhoneNumberUserProfilesColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> AddressUserProfilesColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> EmailUserProfilesColumn;

    @FXML
    private TableColumn<UserProfileDetails, String> PasswordUserProfilesColumn;

    @FXML
    private JFXButton LoadUserProfilesButton;

    @FXML
    private TextField NameUserProfilesField;

    @FXML
    private TextField DateofBirthUserProfilesField;

    @FXML
    private TextField PhoneNumberUserProfilesField;

    @FXML
    private TextField AddressUserProfilesField;

    @FXML
    private TextField EmailUserProfilesField;

    @FXML
    private TextField PasswordUserProfilesField;

    @FXML
    private JFXButton AddUserProfilesButton;

    @FXML
    private JFXButton RemoveUserProfilesButton;

    @FXML
    private JFXButton UpdateUserProfilesButton;

    @FXML
    private TextField CustomerNameUserProfilesField;

    @FXML
    private TextField CustomerEmailUserProfilesField;


    private DBConnection dbConnection;
    private ObservableList<MovieDetails> movieDetailsObservableList;
    private ObservableList<PersonDetails> directorsDetailsObservableList;
    private ObservableList<PersonDetails> actorsDetailsObservableList;
    private ObservableList<UserProfileDetails> customersObservableList;
    private ObservableList<UserProfileDetails> userProfileDetailsObservableList;
    private ArrayList<Integer> directorsIdArrayList = new ArrayList<>();
    private ArrayList<Integer> actorsIdArrayList = new ArrayList<>();
    private ArrayList<Integer> userProfileLoginIdArrayList = new ArrayList<>();;
    private int numberOfUserProfile;
    private String[] directorsArray;
    private String[] actorsArray;
    private String[] genresArray;
    private String directors;
    private String actors;
    private String genres;
    private int movieIdSelected;
    private int directorIdSelected;
    private int actorIdSelected;
    private int customerIdSelected;
    private int customerIdDeleted;
    private int userProfileIdSelected;
    private int UserLoginIdUserProfileDeleted;
    private int UserLoginIdLastUserProfileAdded;
    private int customerLoginIdSelected;
    private String Username;
    private String Password;
    private String Role;
    private String position;
    private int UserId;
    private int adminIdLoginId;
    private int adminId;


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
        getMovieDetails();
        getDirectorsDetails();
        getActorsDetails();
        getCustomersDetails();
        getUserProfilesDetails();
    }

    private void getDirectorsDetails() throws SQLException {
        this.directorsDetailsObservableList = FXCollections.observableArrayList();

        Statement statement = dbConnection.getConnectionToDB().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Director");
        getDirectorsDetailsIntoDirectorsTable(resultSet);
    }

    private void getDirectorsDetailsIntoDirectorsTable(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.directorsDetailsObservableList.add(new PersonDetails(
                    resultSet.getInt("director_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number")
            ));
        }

        NameDirectorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("name"));
        EmailDirectorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("email"));
        PhoneNumberDirectorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("phoneNumber"));
        DirectorsTable.setItems(directorsDetailsObservableList);
    }

    private void getActorsDetails() throws SQLException {
        this.actorsDetailsObservableList = FXCollections.observableArrayList();

        Statement statement = dbConnection.getConnectionToDB().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Actor");
        getActorsDetailsIntoActorsTable(resultSet);
    }

    private void getActorsDetailsIntoActorsTable(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.actorsDetailsObservableList.add(new PersonDetails(
                    resultSet.getInt("actor_id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("phone_number")
            ));
        }

        NameActorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("name"));
        EmailActorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("email"));
        PhoneNumberActorsColumn.setCellValueFactory(new PropertyValueFactory<PersonDetails, String>("phoneNumber"));
        ActorsTable.setItems(actorsDetailsObservableList);
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
        getAdminIdFromAdministrator();
    }

    private void getAdminIdFromAdministrator() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Administrator WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.adminId = resultSet.getInt("administrator_id");
            this.position = resultSet.getString("position");
        }
        PositionField.setText(position);
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
    void loadDirectorsDetails(ActionEvent event) throws SQLException {
        getDirectorsDetails();
    }

    @FXML
    void loadActorsDetails(ActionEvent event) throws SQLException {
        getActorsDetails();
    }

    private void getMovieDetails() throws SQLException {
        this.movieDetailsObservableList = FXCollections.observableArrayList();

        Statement statement = dbConnection.getConnectionToDB().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_view");
        getMovieDetailsIntoMediaTable(resultSet);
    }

    private void getMovieDetailsIntoMediaTable(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            this.movieDetailsObservableList.add(new MovieDetails(
                    resultSet.getInt("movie_id"),
                    resultSet.getString("title"),
                    resultSet.getDate("year"),
                    resultSet.getString("country"),
                    resultSet.getInt("length"),
                    resultSet.getString("directors"),
                    resultSet.getString("actors"),
                    resultSet.getString("genres"),
                    resultSet.getInt("rating"),
                    resultSet.getBoolean("color"),
                    resultSet.getBoolean("bw"),
                    resultSet.getBoolean("original"),
                    resultSet.getInt("movie_sequel_id"),
                    resultSet.getInt("movie_prequel_id")
            ));
        }

        IDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("Id"));
        TitleMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("title"));
        YearMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Date>("year"));
        CountryMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("country"));
        LengthMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("length"));
        DirectorsMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("directors"));
        ActorsMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("actors"));
        GenresMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("genres"));
        RatingMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("rating"));
        ColorMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("color"));
        BwMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("bw"));
        OriginalMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("original"));
        MovieSequelIDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("movieSequelId"));
        MoviePrequelIDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("moviePrequelId"));
        MediaTable.setItems(movieDetailsObservableList);
    }

    @FXML
    void loadMovieDetails(ActionEvent event) throws SQLException {
        getMovieDetails();
    }

    @FXML
    void getMovieDetailsAccordingToFilters(ActionEvent event) throws SQLException {
        this.movieDetailsObservableList = FXCollections.observableArrayList();

        Statement statement = dbConnection.getConnectionToDB().createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_view");

        while (resultSet.next()) {

            if (
                    resultSet.getString("title").toLowerCase().contains(TitleSearchField.getText().toLowerCase()) &&
                            String.valueOf(resultSet.getDate("year")).contains(YearSearchField.getText()) &&
                            resultSet.getString("country").toLowerCase().contains(CountrySearchField.getText().toLowerCase()) &&
                            String.valueOf(resultSet.getInt("length")).contains(LengthSearchField.getText()) &&
                            resultSet.getString("directors").toLowerCase().contains(DirectorsSearchField.getText().toLowerCase()) &&
                            resultSet.getString("actors").toLowerCase().contains(ActorsSearchField.getText().toLowerCase()) &&
                            resultSet.getString("genres").toLowerCase().contains(GenresSearchField.getText().toLowerCase()) &&
                            String.valueOf(resultSet.getInt("rating")).contains(RatingSearchField.getText())

            ) {
                this.movieDetailsObservableList.add(new MovieDetails(
                        resultSet.getInt("movie_id"),
                        resultSet.getString("title"),
                        resultSet.getDate("year"),
                        resultSet.getString("country"),
                        resultSet.getInt("length"),
                        resultSet.getString("directors"),
                        resultSet.getString("actors"),
                        resultSet.getString("genres"),
                        resultSet.getInt("rating"),
                        resultSet.getBoolean("color"),
                        resultSet.getBoolean("bw"),
                        resultSet.getBoolean("original"),
                        resultSet.getInt("movie_sequel_id"),
                        resultSet.getInt("movie_prequel_id")
                ));
            }

        }

        IDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("Id"));
        TitleMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("title"));
        YearMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Date>("year"));
        CountryMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("country"));
        LengthMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("length"));
        DirectorsMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("directors"));
        ActorsMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("actors"));
        GenresMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("genres"));
        RatingMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("rating"));
        ColorMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("color"));
        BwMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("bw"));
        OriginalMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("original"));
        MovieSequelIDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("movieSequelId"));
        MoviePrequelIDMediaColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("moviePrequelId"));
        MediaTable.setItems(movieDetailsObservableList);
    }

    @FXML
    void addDirectorsDetails(ActionEvent event) throws SQLException {
        addDirectorsDetailsIntoDirectorsTable();
    }

    private void addDirectorsDetailsIntoDirectorsTable() throws SQLException {
        if (!NameDirectorsField.getText().isEmpty() && !PhoneNumberDirectorsField.getText().isEmpty()) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO Director " +
                            "(name, email, phone_number)" +
                            "VALUES " +
                            "(?, ?, ?)"
            );

            preparedStatement.setString(1, NameDirectorsField.getText());
            preparedStatement.setString(2, EmailDirectorsField.getText());
            preparedStatement.setString(3, PhoneNumberDirectorsField.getText());

            preparedStatement.executeUpdate();
            getDirectorsDetails();
        }
    }

    @FXML
    void addActorsDetails(ActionEvent event) throws SQLException {
        addActorsDetailsIntoActorsTable();
    }

    private void addActorsDetailsIntoActorsTable() throws SQLException {
        if (!NameActorsField.getText().isEmpty() && !PhoneNumberActorsField.getText().isEmpty()) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO Actor " +
                            "(name, email, phone_number)" +
                            "VALUES " +
                            "(?, ?, ?)"
            );

            preparedStatement.setString(1, NameActorsField.getText());
            preparedStatement.setString(2, EmailActorsField.getText());
            preparedStatement.setString(3, PhoneNumberActorsField.getText());

            preparedStatement.executeUpdate();
            getActorsDetails();
        }
    }

    @FXML
    void updateDirectorsDetails(ActionEvent event) throws IOException, SQLException, ParseException {
        if (!NameDirectorsField.getText().isEmpty() && !PhoneNumberDirectorsField.getText().isEmpty()) {
            getDirectorId();
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "UPDATE Director " +
                            "SET name = ? " +
                            ", email = ? " +
                            ", phone_number = ? " +
                            "WHERE director_id = ? ");

            preparedStatement.setString(1, NameDirectorsField.getText());
            preparedStatement.setString(2, EmailDirectorsField.getText());
            preparedStatement.setString(3, PhoneNumberDirectorsField.getText());
            preparedStatement.setInt(4, directorIdSelected);
            preparedStatement.executeUpdate();

            getDirectorsDetails();
        }
    }

    private void getDirectorId() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Director WHERE name = ? AND email = ? AND phone_number = ?");
        preparedStatement.setString(1, NameDirectorsField.getText());
        preparedStatement.setString(2, EmailDirectorsField.getText());
        preparedStatement.setString(3, PhoneNumberDirectorsField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.directorIdSelected = resultSet.getInt("director_id");
        }
    }

    @FXML
    void updateActorsDetails(ActionEvent event) throws IOException, SQLException, ParseException {
        if (!NameActorsField.getText().isEmpty() && !PhoneNumberActorsField.getText().isEmpty()) {
            getActorId();
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "UPDATE Actor " +
                            "SET name = ? " +
                            ", email = ? " +
                            ", phone_number = ? " +
                            "WHERE actor_id = ? ");

            preparedStatement.setString(1, NameActorsField.getText());
            preparedStatement.setString(2, EmailActorsField.getText());
            preparedStatement.setString(3, PhoneNumberActorsField.getText());
            preparedStatement.setInt(4, actorIdSelected);
            preparedStatement.executeUpdate();

            getActorsDetails();
        }
    }

    private void getActorId() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Actor WHERE name = ? AND email = ? AND phone_number = ?");
        preparedStatement.setString(1, NameActorsField.getText());
        preparedStatement.setString(2, EmailActorsField.getText());
        preparedStatement.setString(3, PhoneNumberActorsField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.actorIdSelected = resultSet.getInt("actor_id");
        }
    }

    @FXML
    void removeDirectorsDetails() throws SQLException {
        if (!NameDirectorsField.getText().isEmpty() && !PhoneNumberDirectorsField.getText().isEmpty()) {
            getDirectorId();
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM Director WHERE director_id = ?");
            preparedStatement.setInt(1, directorIdSelected);

            preparedStatement.executeUpdate();
            getDirectorsDetails();
        }
    }

    @FXML
    void removeActorsDetails() throws SQLException {
        if (!NameActorsField.getText().isEmpty() && !PhoneNumberActorsField.getText().isEmpty()) {
            getActorId();
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM Actor WHERE actor_id = ?");
            preparedStatement.setInt(1, actorIdSelected);

            preparedStatement.executeUpdate();
            getActorsDetails();
        }
    }

    @FXML
    void getDirectorDetailsSelectedRow(MouseEvent event) {
        getDirectorSelectedRow();
    }

    @FXML
    void getActorDetailsSelectedRow(MouseEvent event) {
        getActorSelectedRow();
    }

    private void getDirectorSelectedRow() {
        PersonDetails personDetails = DirectorsTable.getSelectionModel().getSelectedItem();
        directorIdSelected = personDetails.getPersonId();
        NameDirectorsField.setText(personDetails.getName());
        EmailDirectorsField.setText(personDetails.getEmail());
        PhoneNumberDirectorsField.setText(personDetails.getPhoneNumber());
    }

    private void getActorSelectedRow() {
        PersonDetails personDetails = ActorsTable.getSelectionModel().getSelectedItem();
        actorIdSelected = personDetails.getPersonId();
        NameActorsField.setText(personDetails.getName());
        EmailActorsField.setText(personDetails.getEmail());
        PhoneNumberActorsField.setText(personDetails.getPhoneNumber());
    }

    @FXML
    void getMovieDetailsSelectedRow(MouseEvent event) {
        getMovieSelectedRow();
    }

    private void getMovieSelectedRow() {
        MovieDetails movieDetails = MediaTable.getSelectionModel().getSelectedItem();
        movieIdSelected = movieDetails.getId();
        TitleMediaField.setText(movieDetails.getTitle());
        YearMediaField.setText(String.valueOf(movieDetails.getYear()));
        CountryMediaField.setText(movieDetails.getCountry());
        LengthMediaField.setText(String.valueOf(movieDetails.getLength()));
        DirectorsMediaField.setText(movieDetails.getDirectors());
        ActorsMediaField.setText(movieDetails.getActors());
        GenresMediaField.setText(movieDetails.getGenres());
        RatingMediaField.setText(String.valueOf(movieDetails.getRating()));
        ColorMediaField.setText(String.valueOf(movieDetails.getColor()));
        BwMediaField.setText(String.valueOf(movieDetails.getBw()));
        OriginalMediaField.setText(String.valueOf(movieDetails.getOriginal()));
        MovieSequelIDMediaField.setText(String.valueOf(movieDetails.getMovieSequelId()));
        MoviePrequelIDMediaField.setText(String.valueOf(movieDetails.getMoviePrequelId()));

        TitleRemoveField.setText(movieDetails.getTitle());
        YearRemoveField.setText(String.valueOf(movieDetails.getYear()));
    }


    @FXML
    void removeMovieDetails() throws SQLException {
        if (!TitleRemoveField.getText().isEmpty() && !YearRemoveField.getText().isEmpty()) {
            getMovieIdDeleted();
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM Media WHERE movie_id = ?");
            preparedStatement.setInt(1, movieIdSelected);

            preparedStatement.executeUpdate();
            getMovieDetails();
        }
    }

    private void getMovieIdDeleted() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM movie_view WHERE title = ? AND year = ?");
        preparedStatement.setString(1, TitleRemoveField.getText());
        preparedStatement.setDate(2, Date.valueOf(YearRemoveField.getText()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.movieIdSelected = resultSet.getInt("movie_id");
        }
    }

    private void getMovieId() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM movie_view WHERE title = ? AND year = ?");
        preparedStatement.setString(1, TitleMediaField.getText());
        preparedStatement.setDate(2, Date.valueOf(YearMediaField.getText()));

        this.directors = DirectorsMediaField.getText();
        this.actors = ActorsMediaField.getText();
        this.genres = GenresMediaField.getText();
        directorsArray = directors.split("\\s*,\\s*");
        actorsArray = actors.split("\\s*,\\s*");
        genresArray = genres.split("\\s*,\\s*");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.movieIdSelected = resultSet.getInt("movie_id");
        }
    }

    private void getMovieIdAdded() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Media WHERE title = ? AND year = ?");
        preparedStatement.setString(1, TitleMediaField.getText());
        preparedStatement.setDate(2, Date.valueOf(YearMediaField.getText()));

        this.directors = DirectorsMediaField.getText();
        this.actors = ActorsMediaField.getText();
        this.genres = GenresMediaField.getText();
        directorsArray = directors.split("\\s*,\\s*");
        actorsArray = actors.split("\\s*,\\s*");
        genresArray = genres.split("\\s*,\\s*");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.movieIdSelected = resultSet.getInt("movie_id");
        }
    }

    @FXML
    void updateMovieDetails(ActionEvent event) throws IOException, SQLException, ParseException {
        if (!TitleMediaField.getText().isEmpty() && !YearMediaField.getText().isEmpty()) {
            getMovieId();
            updateMediaDetails();
            updateMediaGenres();
            updateMediaDirectors();
            updateMediaActors();
            getMovieDetails();
        }
    }

    private void updateMediaDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "UPDATE Media " +
                        "SET title = ? " +
                        ", year = ? " +
                        ", country = ? " +
                        ", length = ? " +
                        ", rating = ? " +
                        ", color = ? " +
                        ", bw = ? " +
                        ", original = ? " +
                        ", movie_sequel_id = ? " +
                        ", movie_prequel_id = ? " +
                        "WHERE movie_id = ? ");

        preparedStatement.setString(1, TitleMediaField.getText());
        preparedStatement.setDate(2, Date.valueOf(YearMediaField.getText()));
        preparedStatement.setString(3, CountryMediaField.getText());
        preparedStatement.setInt(4, Integer.parseInt(LengthMediaField.getText()));
        preparedStatement.setInt(5, Integer.parseInt(RatingMediaField.getText()));
        preparedStatement.setBoolean(6, Boolean.parseBoolean(ColorMediaField.getText()));
        preparedStatement.setBoolean(7, Boolean.parseBoolean(BwMediaField.getText()));
        preparedStatement.setBoolean(8, Boolean.parseBoolean(OriginalMediaField.getText()));
        preparedStatement.setInt(9, Integer.parseInt(MovieSequelIDMediaField.getText()));
        preparedStatement.setInt(10, Integer.parseInt(MoviePrequelIDMediaField.getText()));
        preparedStatement.setInt(11, movieIdSelected);
        preparedStatement.executeUpdate();
    }

    private void updateMediaGenres() throws SQLException {
        removeMediaGenresBeforeUpdateMovieDetails();
        addMediaGenres();
    }

    private void addMediaGenres() throws SQLException {
        for (int i = 0; i < genresArray.length; i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO MediaGenres " +
                            "(movie_id, genre)" +
                            "VALUES " +
                            "(?, ?)"
            );

            preparedStatement.setInt(1, movieIdSelected);
            preparedStatement.setString(2, genresArray[i]);

            preparedStatement.executeUpdate();
        }

    }

    private void removeMediaGenresBeforeUpdateMovieDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM MediaGenres WHERE movie_id = ?");
        preparedStatement.setInt(1, movieIdSelected);

        preparedStatement.executeUpdate();
    }


    private void updateMediaDirectors() throws SQLException {
        removeMediaDirectorsBeforeUpdateMovieDetails();
        addMediaDirectors();
    }

    private void addMediaDirectors() throws SQLException {
        getDirectorsId();
        addDirectorsIdIntoMediaDirectors();
    }

    private void getDirectorsId() throws SQLException {
        for (int i = 0; i < directorsArray.length; i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Director WHERE name = ?");
            preparedStatement.setString(1, directorsArray[i]);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                directorsIdArrayList.add(resultSet.getInt("director_id"));
            }
        }
    }

    private void addDirectorsIdIntoMediaDirectors() throws SQLException {
        for (int i = 0; i < directorsIdArrayList.size(); i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO MediaDirectors " +
                            "(movie_id, director_id)" +
                            "VALUES " +
                            "(?, ?)"
            );

            preparedStatement.setInt(1, movieIdSelected);
            preparedStatement.setInt(2, directorsIdArrayList.get(i));

            preparedStatement.executeUpdate();
        }
        directorsIdArrayList.clear();
    }

    private void removeMediaDirectorsBeforeUpdateMovieDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM MediaDirectors WHERE movie_id = ?");
        preparedStatement.setInt(1, movieIdSelected);

        preparedStatement.executeUpdate();
    }

    private void updateMediaActors() throws SQLException {
        removeMediaActorsBeforeUpdateMovieDetails();
        addMediaActors();
    }

    private void addMediaActors() throws SQLException {
        getActorsId();
        addActorsIdIntoMediaActors();
    }

    private void getActorsId() throws SQLException {
        for (int i = 0; i < actorsArray.length; i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Actor WHERE name = ?");
            preparedStatement.setString(1, actorsArray[i]);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                actorsIdArrayList.add(resultSet.getInt("actor_id"));
            }
        }
    }

    private void addActorsIdIntoMediaActors() throws SQLException {
        for (int i = 0; i < actorsIdArrayList.size(); i++) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO MediaActors " +
                            "(movie_id, actor_id)" +
                            "VALUES " +
                            "(?, ?)"
            );

            preparedStatement.setInt(1, movieIdSelected);
            preparedStatement.setInt(2, actorsIdArrayList.get(i));

            preparedStatement.executeUpdate();
        }
        actorsIdArrayList.clear();
    }

    private void removeMediaActorsBeforeUpdateMovieDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM MediaActors WHERE movie_id = ?");
        preparedStatement.setInt(1, movieIdSelected);

        preparedStatement.executeUpdate();
    }

    @FXML
    void addMovieDetails(ActionEvent event) throws SQLException {
        if (!TitleMediaField.getText().isEmpty() &&
                !YearMediaField.getText().isEmpty() &&
                !CountryMediaField.getText().isEmpty() &&
                !LengthMediaField.getText().isEmpty() &&
                !DirectorsMediaField.getText().isEmpty() &&
                !ActorsMediaField.getText().isEmpty() &&
                !GenresMediaField.getText().isEmpty() &&
                !RatingMediaField.getText().isEmpty() &&
                !ColorMediaField.getText().isEmpty() &&
                !BwMediaField.getText().isEmpty() &&
                !OriginalMediaField.getText().isEmpty()) {

            addMediaDetails();
            getMovieIdAdded();
            addMediaGenres();
            addMediaDirectors();
            addMediaActors();
            getMovieDetails();
            clearAllFields();
        }

    }

    private void clearAllFields() {
        TitleMediaField.clear();
        YearMediaField.clear();
        CountryMediaField.clear();
        LengthMediaField.clear();
        DirectorsMediaField.clear();
        ActorsMediaField.clear();
        GenresMediaField.clear();
        RatingMediaField.clear();
        ColorMediaField.clear();
        BwMediaField.clear();
        OriginalMediaField.clear();
        MovieSequelIDMediaField.clear();
        MoviePrequelIDMediaField.clear();
    }

    private void addMediaDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO Media " +
                        "(title, year, country, length, rating, color, bw, original, movie_sequel_id, movie_prequel_id)" +
                        "VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        );

        preparedStatement.setString(1, TitleMediaField.getText());
        preparedStatement.setDate(2, Date.valueOf(YearMediaField.getText()));
        preparedStatement.setString(3, CountryMediaField.getText());
        preparedStatement.setInt(4, Integer.parseInt(LengthMediaField.getText()));
        preparedStatement.setInt(5, Integer.parseInt(RatingMediaField.getText()));
        preparedStatement.setBoolean(6, Boolean.parseBoolean(ColorMediaField.getText()));
        preparedStatement.setBoolean(7, Boolean.parseBoolean(BwMediaField.getText()));
        preparedStatement.setBoolean(8, Boolean.parseBoolean(OriginalMediaField.getText()));
        preparedStatement.setInt(9, Integer.parseInt(MovieSequelIDMediaField.getText()));
        preparedStatement.setInt(10, Integer.parseInt(MoviePrequelIDMediaField.getText()));

        preparedStatement.executeUpdate();

    }

    private void getCustomersDetails() throws SQLException {
        this.customersObservableList = FXCollections.observableArrayList();

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE role = ?::\"enum_list_role\"");
        preparedStatement.setString(1, "Customer");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            this.customersObservableList.add(new UserProfileDetails(
                    resultSet.getString("name"),
                    resultSet.getDate("date_of_birth"),
                    resultSet.getString("phone_number"),
                    resultSet.getString("address"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("role")
            ));
        }
        NameCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("name"));
        DateofBirthCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, Date>("dateOfBirth"));
        PhoneNumberCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("phoneNumber"));
        AddressCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("address"));
        EmailCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("email"));
        PasswordCustomersColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("password"));
        CustomersTable.setItems(customersObservableList);
    }

    private void getUserProfilesDetails() throws SQLException {
        this.userProfileDetailsObservableList = FXCollections.observableArrayList();

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE role = ?::\"enum_list_role\"");
        preparedStatement.setString(1, "User");

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
        NameUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("name"));
        DateofBirthUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, Date>("dateOfBirth"));
        PhoneNumberUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("phoneNumber"));
        AddressUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("address"));
        EmailUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("email"));
        PasswordUserProfilesColumn.setCellValueFactory(new PropertyValueFactory<UserProfileDetails, String>("password"));
        UserProfilesTable.setItems(userProfileDetailsObservableList);
    }

    @FXML
    void loadCustomersDetails(ActionEvent event) throws SQLException {
        getCustomersDetails();
    }

    @FXML
    void loadUserProfilesDetails(ActionEvent event) throws SQLException {
        getUserProfilesDetails();
    }

    @FXML
    void removeCustomerDetails() throws SQLException {
        getCustomerIdSelected();
        getCustomerIdFromCustomer();
        getAllUserProfilesDetailsForCustomer();
        int numberOfUserProfileBeforeRemoveCustomerProfile = numberOfUserProfile;
        for (int i = numberOfUserProfileBeforeRemoveCustomerProfile - 1; i >= 0; i--) {
            UserLoginIdUserProfileDeleted = userProfileLoginIdArrayList.get(i);
            removeUserProfileFromUserLogin();
        }

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE name = ? AND date_of_birth = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameCustomersField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthCustomersField.getText()));
        preparedStatement.setString(3, EmailCustomersField.getText());
        preparedStatement.setString(4, PasswordCustomersField.getText());
        preparedStatement.executeUpdate();
        getCustomersDetails();
        getUserProfilesDetails();
    }

    private void removeUserProfileFromUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE user_login_id = ?");
        preparedStatement.setInt(1, UserLoginIdUserProfileDeleted);

        preparedStatement.executeUpdate();

        userProfileLoginIdArrayList.remove(UserLoginIdUserProfileDeleted);

        numberOfUserProfile = userProfileLoginIdArrayList.size();
    }

    private void getCustomerIdFromCustomer() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Customer WHERE user_login_id = ?");
        preparedStatement.setInt(1, customerIdSelected);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.customerIdDeleted = resultSet.getInt("customer_id");
        }
    }

    private void getAllUserProfilesDetailsForCustomer() throws SQLException {
        this.userProfileLoginIdArrayList.clear();
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserProfile WHERE customer_id = ?");
        preparedStatement.setInt(1, customerIdDeleted);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userProfileLoginIdArrayList.add(resultSet.getInt("user_login_id"));
        }
        numberOfUserProfile = userProfileLoginIdArrayList.size();

    }

    @FXML
    void removeUserDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("DELETE FROM UserLogin WHERE name = ? AND date_of_birth = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthUserProfilesField.getText()));
        preparedStatement.setString(3, EmailUserProfilesField.getText());
        preparedStatement.setString(4, PasswordUserProfilesField.getText());
        preparedStatement.executeUpdate();
        getUserProfilesDetails();
    }

    @FXML
    void getCustomerDetailsSelectedRow(MouseEvent event) throws SQLException {
        UserProfileDetails userProfileDetails = CustomersTable.getSelectionModel().getSelectedItem();
        NameCustomersField.setText(userProfileDetails.getName());
        DateofBirthCustomersField.setText(String.valueOf(userProfileDetails.getDateOfBirth()));
        PhoneNumberCustomersField.setText(userProfileDetails.getPhoneNumber());
        AddressCustomersField.setText(userProfileDetails.getAddress());
        EmailCustomersField.setText(userProfileDetails.getEmail());
        PasswordCustomersField.setText(userProfileDetails.getPassword());
        getCustomerIdSelected();
    }

    @FXML
    void getUserDetailsSelectedRow(MouseEvent event) throws SQLException {
        UserProfileDetails userProfileDetails = UserProfilesTable.getSelectionModel().getSelectedItem();
        NameUserProfilesField.setText(userProfileDetails.getName());
        DateofBirthUserProfilesField.setText(String.valueOf(userProfileDetails.getDateOfBirth()));
        PhoneNumberUserProfilesField.setText(userProfileDetails.getPhoneNumber());
        AddressUserProfilesField.setText(userProfileDetails.getAddress());
        EmailUserProfilesField.setText(userProfileDetails.getEmail());
        PasswordUserProfilesField.setText(userProfileDetails.getPassword());
        getUserIdSelected();
    }

    @FXML
    void updateCustomerDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "UPDATE UserLogin " +
                        "SET name = ? " +
                        ", date_of_birth = ? " +
                        ", phone_number = ? " +
                        ", address = ? " +
                        ", email = ? " +
                        ", password = ? " +
                        "WHERE user_login_id = ? ");

        preparedStatement.setString(1, NameCustomersField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthCustomersField.getText()));
        preparedStatement.setString(3, PhoneNumberCustomersField.getText());
        preparedStatement.setString(4, AddressCustomersField.getText());
        preparedStatement.setString(5, EmailCustomersField.getText());
        preparedStatement.setString(6, PasswordCustomersField.getText());
        preparedStatement.setInt(7, customerIdSelected);
        preparedStatement.executeUpdate();
        getCustomersDetails();
    }

    private void getCustomerIdSelected() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameCustomersField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthCustomersField.getText()));
        preparedStatement.setString(3, PhoneNumberCustomersField.getText());
        preparedStatement.setString(4, AddressCustomersField.getText());
        preparedStatement.setString(5, EmailCustomersField.getText());
        preparedStatement.setString(6, PasswordCustomersField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.customerIdSelected = resultSet.getInt("user_login_id");
        }
    }

    @FXML
    void updateUserDetails() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "UPDATE UserLogin " +
                        "SET name = ? " +
                        ", date_of_birth = ? " +
                        ", phone_number = ? " +
                        ", address = ? " +
                        ", email = ? " +
                        ", password = ? " +
                        "WHERE user_login_id = ? ");

        preparedStatement.setString(1, NameUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberUserProfilesField.getText());
        preparedStatement.setString(4, AddressUserProfilesField.getText());
        preparedStatement.setString(5, EmailUserProfilesField.getText());
        preparedStatement.setString(6, PasswordUserProfilesField.getText());
        preparedStatement.setInt(7, userProfileIdSelected);
        preparedStatement.executeUpdate();
        getUserProfilesDetails();
    }

    private void getUserIdSelected() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberUserProfilesField.getText());
        preparedStatement.setString(4, AddressUserProfilesField.getText());
        preparedStatement.setString(5, EmailUserProfilesField.getText());
        preparedStatement.setString(6, PasswordUserProfilesField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.userProfileIdSelected = resultSet.getInt("user_login_id");
        }
    }

    @FXML
    void addCustomerDetails() throws SQLException {
        if (!NameCustomersField.getText().isEmpty() &&
            !DateofBirthCustomersField.getText().isEmpty() &&
            !PhoneNumberCustomersField.getText().isEmpty() &&
            !AddressCustomersField.getText().isEmpty() &&
            !EmailCustomersField.getText().isEmpty() &&
            !PasswordCustomersField.getText().isEmpty()
        ) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO UserLogin " +
                            "(name, date_of_birth, phone_number, address, email, password, role)" +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?::enum_list_role)"
            );

            preparedStatement.setString(1, NameCustomersField.getText());
            preparedStatement.setDate(2, java.sql.Date.valueOf(DateofBirthCustomersField.getText()));
            preparedStatement.setString(3, PhoneNumberCustomersField.getText());
            preparedStatement.setString(4, AddressCustomersField.getText());
            preparedStatement.setString(5, EmailCustomersField.getText());
            preparedStatement.setString(6, PasswordCustomersField.getText());
            preparedStatement.setString(7, roleType.Customer.toString());

            preparedStatement.executeUpdate();
            getCustomerIdSelected();
            addCustomerDetailsIntoCustomer();
            getCustomersDetails();
        }

    }

    private void addCustomerDetailsIntoCustomer() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO Customer " +
                        "(user_login_id)" +
                        "VALUES " +
                        "(?)"
        );

        preparedStatement.setInt(1, customerIdSelected);
        preparedStatement.executeUpdate();
    }

    @FXML
    void addUserProfileDetails() throws SQLException {
        getCustomerLoginIdSelectedFromUserLogin();
        getCustomerIdSelectedFromCustomer();
        checkCustomerUserProfileDetails();
        if (numberOfUserProfile < 3) {
            addUserProfileDetailsIntoUserLogin();
            getUserLoginIdLastUserProfileAdded();
            addUserProfileDetailsIntoUserProfile();
            getUserProfilesDetails();
        }
    }

    private void getCustomerIdSelectedFromCustomer() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM Customer WHERE user_login_id = ?");
        preparedStatement.setInt(1, customerLoginIdSelected);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.customerIdSelected = resultSet.getInt("customer_id");
        }

    }

    void addUserProfileDetailsIntoUserLogin() throws SQLException {
        if (!NameUserProfilesField.getText().isEmpty() &&
                !DateofBirthUserProfilesField.getText().isEmpty() &&
                !PhoneNumberUserProfilesField.getText().isEmpty() &&
                !AddressUserProfilesField.getText().isEmpty() &&
                !EmailUserProfilesField.getText().isEmpty() &&
                !PasswordUserProfilesField.getText().isEmpty() &&
                !CustomerNameUserProfilesField.getText().isEmpty() &&
                !CustomerEmailUserProfilesField.getText().isEmpty()
        ) {
            PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "INSERT INTO UserLogin " +
                            "(name, date_of_birth, phone_number, address, email, password, role)" +
                            "VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?::enum_list_role)"
            );

            preparedStatement.setString(1, NameUserProfilesField.getText());
            preparedStatement.setDate(2, java.sql.Date.valueOf(DateofBirthUserProfilesField.getText()));
            preparedStatement.setString(3, PhoneNumberUserProfilesField.getText());
            preparedStatement.setString(4, AddressUserProfilesField.getText());
            preparedStatement.setString(5, EmailUserProfilesField.getText());
            preparedStatement.setString(6, PasswordUserProfilesField.getText());
            preparedStatement.setString(7, roleType.User.toString());

            preparedStatement.executeUpdate();
        }

    }

    private void checkCustomerUserProfileDetails() throws SQLException {
        this.userProfileLoginIdArrayList.clear();
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserProfile WHERE customer_id = ?");
        preparedStatement.setInt(1, customerIdSelected);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            userProfileLoginIdArrayList.add(resultSet.getInt("user_login_id"));
        }

        numberOfUserProfile = userProfileLoginIdArrayList.size();
    }

    private void addUserProfileDetailsIntoUserProfile() throws SQLException {

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO UserProfile " +
                        "(user_login_id, customer_id)" +
                        "VALUES " +
                        "(?, ?)"
        );

        preparedStatement.setInt(1, UserLoginIdLastUserProfileAdded);
        preparedStatement.setInt(2, customerIdSelected);
        preparedStatement.executeUpdate();
    }

    private void getUserLoginIdLastUserProfileAdded() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND date_of_birth = ? AND phone_number = ? AND address = ? AND email = ? AND password = ?");
        preparedStatement.setString(1, NameUserProfilesField.getText());
        preparedStatement.setDate(2, Date.valueOf(DateofBirthUserProfilesField.getText()));
        preparedStatement.setString(3, PhoneNumberUserProfilesField.getText());
        preparedStatement.setString(4, AddressUserProfilesField.getText());
        preparedStatement.setString(5, EmailUserProfilesField.getText());
        preparedStatement.setString(6, PasswordUserProfilesField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.UserLoginIdLastUserProfileAdded = resultSet.getInt("user_login_id");
        }

    }

    private void getCustomerLoginIdSelectedFromUserLogin() throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement("SELECT * FROM UserLogin WHERE name = ? AND email = ?");
        preparedStatement.setString(1, CustomerNameUserProfilesField.getText());
        preparedStatement.setString(2, CustomerEmailUserProfilesField.getText());

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.customerLoginIdSelected = resultSet.getInt("user_login_id");
        }
    }

}
