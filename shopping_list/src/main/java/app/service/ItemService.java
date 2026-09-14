package app.service;

import app.api.APIReader;
import app.dto.MovieDTO;
import app.config.EnvConfig;
import app.dto.DiscoverResponse;

public class ItemService {
    private final APIReader apiReader;
    private final String apiKey;

    public MovieService() {
        this.apiReader = new APIReader();
        this.apiKey = EnvConfig.get("API_KEY");
    }

    public MovieDTO getMovieById(int id) {
        String url = "https://api.themoviedb.org/3/movie/" + id;
        MovieDTO movieId = apiReader.getWithJacksonGeneric(url, apiKey, MovieDTO.class);
        return movieId;
    }

    public MovieDTO[] getMovieByRating(double ratingLower, double ratingHigher){
        String url = "https://api.themoviedb.org/3/discover/movie?vote_average.gte=" + ratingLower + "&vote_average.lte=" + ratingHigher;

        DiscoverResponse response = apiReader.getWithJacksonGeneric(url, apiKey, DiscoverResponse.class);
            return response.getResults();
    }
}