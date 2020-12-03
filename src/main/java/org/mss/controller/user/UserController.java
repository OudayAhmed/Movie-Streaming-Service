package org.mss.controller.user;

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
import javafx.stage.Stage;
import org.mss.controller.data.MovieDetails;
import org.mss.controller.data.WatchlistDetails;
import org.mss.db.DBConnection;


import java.io.IOException;
import java.sql.*;
import java.text.ParseException;

public class UserController {

    @FXML
    private Tab MyAccountTab;

    @FXML
    private TextField NameField;

    @FXML
    private TextField DateofBirthField;

    @FXML
    private TextField PhoneNumberField;

    @FXML
    private TextField AddressField;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField PasswordField;

    @FXML
    private JFXButton LoadButton;

    @FXML
    private JFXButton LogoutButton;

    @FXML
    private JFXButton UpdateButton;

    @FXML
    private Tab MediaTab;

    @FXML
    private TableView<MovieDetails> MediaTable;

    @FXML
    private TableColumn<MovieDetails, Integer> IDColumn;

    @FXML
    private TableColumn<MovieDetails, String> TitleColumn;

    @FXML
    private TableColumn<MovieDetails, Date> YearColumn;

    @FXML
    private TableColumn<MovieDetails, String> CountryColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> LengthColumn;

    @FXML
    private TableColumn<MovieDetails, String> DirectorsColumn;

    @FXML
    private TableColumn<MovieDetails, String> ActorsColumn;

    @FXML
    private TableColumn<MovieDetails, String> GenresColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> RatingColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> ColorColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> BwColumn;

    @FXML
    private TableColumn<MovieDetails, Boolean> OriginalColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> movieSequelIdColumn;

    @FXML
    private TableColumn<MovieDetails, Integer> moviePrequelIdColumn;

    @FXML
    private JFXButton LoadMediaButton;

    @FXML
    private JFXButton AddToMyWatchlistButton;

    @FXML
    private TextField MovieIDMediaField;

    @FXML
    private TextField RatingMediaField;

    @FXML
    private TextField WatchTimeMediaField;

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
    private JFXButton SearchButton;

    @FXML
    private Tab MyWatchlistTab;

    @FXML
    private TableView<WatchlistDetails> MyWatchlistTable;

    @FXML
    private TableColumn<WatchlistDetails, Integer> MovieIDColumn;

    @FXML
    private TableColumn<WatchlistDetails, Integer> RatingWatchlistColumn;

    @FXML
    private TableColumn<WatchlistDetails, Integer> WatchTimeColumn;

    @FXML
    private JFXButton LoadWatchlistButton;

    @FXML
    private TextField MovieIDMyWatchlistField;

    @FXML
    private TextField RatingMyWatchlistField;

    @FXML
    private TextField WatchTimeMyWatchlistField;

    @FXML
    private JFXButton UpdateMyWatchlistButton;


    private ObservableList<MovieDetails> movieDetailsObservableList;
    private ObservableList<WatchlistDetails> watchlistDetailsObservableList;
    private DBConnection dbConnection;
    private int UserId;
    private int UserIdInUserProfile;
    private String Username;
    private String Password;
    private String Role;


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
        getUserIdInUserProfile(dbConnection);
        getMovieDetails(dbConnection);
        getWatchlistDetails(dbConnection);

    }

    private void getUserIdInUserProfile(DBConnection dbConnection) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM userprofile WHERE user_login_id = ?"
        );
        preparedStatement.setInt(1, UserId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.UserIdInUserProfile = resultSet.getInt("user_id");
        }
    }

    private void getWatchlistDetails(DBConnection dbConnection) throws SQLException {
        this.watchlistDetailsObservableList = FXCollections.observableArrayList();

        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "SELECT * FROM watchlist WHERE user_id = ?"
        );
        preparedStatement.setInt(1, UserIdInUserProfile);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            this.watchlistDetailsObservableList.add(new WatchlistDetails(
                    resultSet.getInt("movie_id"),
                    resultSet.getInt("rating"),
                    resultSet.getInt("watch_time")
            ));
        }

        MovieIDColumn.setCellValueFactory(new PropertyValueFactory<WatchlistDetails, Integer>("movieId"));
        RatingWatchlistColumn.setCellValueFactory(new PropertyValueFactory<WatchlistDetails, Integer>("rating"));
        WatchTimeColumn.setCellValueFactory(new PropertyValueFactory<WatchlistDetails, Integer>("watchtime"));
        MyWatchlistTable.setItems(watchlistDetailsObservableList);
    }

    private void getMovieDetails(DBConnection dbConnection) throws SQLException {
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

        IDColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("Id"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("title"));
        YearColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Date>("year"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("country"));
        LengthColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("length"));
        DirectorsColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("directors"));
        ActorsColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("actors"));
        GenresColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("genres"));
        RatingColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("rating"));
        ColorColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("color"));
        BwColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("bw"));
        OriginalColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("original"));
        movieSequelIdColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("movieSequelId"));
        moviePrequelIdColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("moviePrequelId"));
        MediaTable.setItems(movieDetailsObservableList);
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
            DateofBirthField.setText(resultSet.getString("date_of_birth"));
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
        preparedStatement.setDate(2, java.sql.Date.valueOf(DateofBirthField.getText()));
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
    void updateMyWatchlistDetails(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                    "UPDATE watchlist " +
                        "SET rating = ? " +
                        ", watch_time = ? " +
                        "WHERE user_id = ? And movie_id = ?"
        );

        preparedStatement.setInt(1, Integer.parseInt(RatingMyWatchlistField.getText()));
        preparedStatement.setInt(2, Integer.parseInt(WatchTimeMyWatchlistField.getText()));
        preparedStatement.setInt(3, UserIdInUserProfile);
        preparedStatement.setInt(4, Integer.parseInt(MovieIDMyWatchlistField.getText()));

        preparedStatement.executeUpdate();

    }

    @FXML
    void loadAccountDetails(ActionEvent event) throws SQLException {
        getAccountDetails();
    }

    @FXML
    void loadMovieDetails(ActionEvent event) throws SQLException {
        getMovieDetails(dbConnection);
    }

    @FXML
    void loadWatchlistDetails(ActionEvent event) throws SQLException {
        getWatchlistDetails(dbConnection);
    }

    @FXML
    void addToMyWatchlist(ActionEvent event) throws SQLException {
        PreparedStatement preparedStatement = dbConnection.getConnectionToDB().prepareStatement(
                "INSERT INTO watchlist " +
                    "(user_id, movie_id, rating, watch_time)" +
                    "VALUES " +
                    "(?, ?, ?, ?)"
        );
        preparedStatement.setInt(1, UserIdInUserProfile);
        preparedStatement.setInt(2, Integer.parseInt(MovieIDMediaField.getText()));
        preparedStatement.setInt(3, Integer.parseInt(RatingMediaField.getText()));
        preparedStatement.setInt(4, Integer.parseInt(WatchTimeMediaField.getText()));

        preparedStatement.executeUpdate();
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

        IDColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("Id"));
        TitleColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("title"));
        YearColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Date>("year"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("country"));
        LengthColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("length"));
        DirectorsColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("directors"));
        ActorsColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("actors"));
        GenresColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, String>("genres"));
        RatingColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("rating"));
        ColorColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("color"));
        BwColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("bw"));
        OriginalColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Boolean>("original"));
        movieSequelIdColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("movieSequelId"));
        moviePrequelIdColumn.setCellValueFactory(new PropertyValueFactory<MovieDetails, Integer>("moviePrequelId"));
        MediaTable.setItems(movieDetailsObservableList);
    }

}
