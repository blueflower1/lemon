package com.lemon.lemon.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.lemon.entity.Movie;
import com.lemon.lemon.entity.MovieBanner;
import com.lemon.lemon.entity.MovieCardView;
import com.lemon.lemon.mapper.MovieBannerMapper;
import com.lemon.lemon.mapper.MovieCardViewMapper;
import com.lemon.lemon.mapper.MovieMapper;
import com.lemon.lemon.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieBannerMapper movieBannerMapper;

    @Autowired
    MovieCardViewMapper movieCardViewMapper;

    @Autowired
    MovieMapper movieMapper;
    @Override
    public List<String> getMoviesForBanner() {
        ArrayList<String> list = new ArrayList<>();
        List<MovieBanner> movieBanners = movieBannerMapper.selectList(new QueryWrapper<MovieBanner>());
        for (MovieBanner movieBanner : movieBanners) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", movieBanner.getId());
            jsonObject.put("name", movieBanner.getName());
            jsonObject.put("photo_url", movieBanner.getPhotoUrl());
            list.add(jsonObject.toString());
        }
        return list;
    }

    @Override
    public List<String> getMoviesForCardView() {
        ArrayList<String> list = new ArrayList<>();
        List<MovieCardView> movieCardViews = movieCardViewMapper.selectList(new QueryWrapper<MovieCardView>());
        for (MovieCardView movieCardView : movieCardViews) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", movieCardView.getId());
            jsonObject.put("name", movieCardView.getName());
            jsonObject.put("photo_url", movieCardView.getPhotoUrl());
            list.add(jsonObject.toString());
        }
        return list;
    }

    @Override
    public String getMovieById(Integer id) {
        Movie movie = movieMapper.selectById(id);
        return JSON.toJSONString(movie);
    }

    @Override
    public List<String> getMoviesForQuery(String query) {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.like("name",query);

        List<Movie> movies = movieMapper.selectList(wrapper);
        ArrayList<String> movieList = new ArrayList<>();

        for (Movie movie : movies) {
            String movieJson = JSON.toJSONString(movie);
            movieList.add(movieJson);
        }
        return movieList;
    }
}
