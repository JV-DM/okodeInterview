package com.okodeInterview.okode;

import com.okodeInterview.okode.dataModels.Movie;
import com.okodeInterview.okode.dataModels.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class MovieSearchController {

    private static String defaultUrl = "https://api.themoviedb.org/3/";
    private static String searchUrl = defaultUrl + "search/movie?api_key=8f27b7eaf57c01db15a6ca4b6b11f9a1&query=";

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/movies")
    public Movie[] getMovies(@RequestParam(value = "title", required = true) String title) {
        Movie[] movies = null;
        String requestUrl = "";
        RestTemplate restTemplate = new RestTemplate();
        int pages = 1; //minimum amount of pages we will iterate through

        if (title != null && !title.isEmpty())
            requestUrl = searchUrl + title.trim();
        else
            return null;

        for (int i = 1; i <= pages; i++) {
            Result response
                    = restTemplate.getForObject(requestUrl, Result.class);

            if (movies == null)
                movies = response.getResults();
            else {
                ArrayList<Movie> movieArrayList = new ArrayList<>(Arrays.asList(movies));
                movieArrayList.addAll(Arrays.asList(response.getResults()));
                movies = movieArrayList.toArray(new Movie[movieArrayList.size()]);
            }
            pages = response.getTotal_pages();
        }
        return movies;
    }

}
