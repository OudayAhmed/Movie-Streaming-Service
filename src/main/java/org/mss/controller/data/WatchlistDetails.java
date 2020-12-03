package org.mss.controller.data;

public class WatchlistDetails {

    private final int movieId;
    private final int rating;
    private final int watchtime;

    public WatchlistDetails(Integer movieId, Integer rating, Integer watchtime) {
        this.movieId = movieId;
        this.rating = rating;
        this.watchtime = watchtime;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRating() {
        return rating;
    }

    public int getWatchtime() {
        return watchtime;
    }
}
