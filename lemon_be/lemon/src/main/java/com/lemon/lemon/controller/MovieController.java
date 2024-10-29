package com.lemon.lemon.controller;

import com.lemon.lemon.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    @GetMapping("/getMoviesForBanner")
    public List<String> getMoviesForBanner() {
        return movieService.getMoviesForBanner();
    }

    @GetMapping("/getMoviesForCardView")
    public List<String> getMoviesForCardView() {
        return movieService.getMoviesForCardView();
    }

    @PostMapping("/getMovieById")
    public String getMovieById(@RequestParam Integer id) {
        return movieService.getMovieById(id);
    }

    @PostMapping("/getMoviesForQuery")
    public List<String> getMoviesForQuery(@RequestParam String query) {
        return movieService.getMoviesForQuery(query);
    }
}
