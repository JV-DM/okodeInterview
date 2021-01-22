package com.okodeInterview.okode;

import com.okodeInterview.okode.dataModels.Movie;
import com.okodeInterview.okode.dataModels.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping("/movies")
public class MovieSearchController {

    private static String defaultUrl = "https://api.themoviedb.org/3/";
    private static String searchUrl = defaultUrl + "search/movie?api_key=8f27b7eaf57c01db15a6ca4b6b11f9a1&query=";

    @RequestMapping(method = RequestMethod.GET)
    public Movie[] getMovies(@RequestParam("title") String title) {
        Movie[] movies = null;
        String requestUrl = defaultUrl;
        RestTemplate restTemplate = new RestTemplate();
        int pages = 1; //minimum amount of pages we will iterate through

        if(title != null && !title.isEmpty())
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
