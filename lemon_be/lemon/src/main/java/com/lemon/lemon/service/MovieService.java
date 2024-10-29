package com.lemon.lemon.service;



import java.util.List;

public interface MovieService {
    List<String> getMoviesForBanner();

    List<String> getMoviesForCardView();

    String getMovieById(Integer id);

    List<String> getMoviesForQuery(String query);
}
