package org.mss.controller.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class MovieDetails {

    private final int Id;
    private final StringProperty title;
    private final Date year;
    private final StringProperty country;
    private final int length;
    private final StringProperty directors;
    private final StringProperty actors;
    private final StringProperty genres;
    private final int rating;
    private final Boolean color;
    private final Boolean bw;
    private final Boolean original;
    private final int movieSequelId;
    private final int moviePrequelId;

    public MovieDetails(Integer Id, String title, Date year, String country, Integer length, String directors, String actors, String genres, Integer rating, Boolean color, Boolean bw, Boolean original, Integer movieSequelId, Integer moviePrequelId) {
        this.Id = Id;
        this.title = new SimpleStringProperty(title);
        this.year = year;
        this.country = new SimpleStringProperty(country);
        this.length = length;
        this.directors = new SimpleStringProperty(directors);
        this.actors = new SimpleStringProperty(actors);
        this.genres = new SimpleStringProperty(genres);
        this.rating = rating;
        this.color = color;
        this.bw = bw;
        this.original = original;
        this.movieSequelId = movieSequelId;
        this.moviePrequelId = moviePrequelId;
    }

    public int getId() {
        return Id;
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public Date getYear() {
        return year;
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public int getLength() {
        return length;
    }

    public String getDirectors() {
        return directors.get();
    }

    public StringProperty directorsProperty() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors.set(directors);
    }

    public String getActors() {
        return actors.get();
    }

    public StringProperty actorsProperty() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors.set(actors);
    }

    public String getGenres() {
        return genres.get();
    }

    public StringProperty genresProperty() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres.set(genres);
    }

    public int getRating() {
        return rating;
    }

    public Boolean getColor() {
        return color;
    }

    public Boolean getBw() {
        return bw;
    }

    public Boolean getOriginal() {
        return original;
    }

    public int getMovieSequelId() {
        return movieSequelId;
    }

    public int getMoviePrequelId() {
        return moviePrequelId;
    }
}
