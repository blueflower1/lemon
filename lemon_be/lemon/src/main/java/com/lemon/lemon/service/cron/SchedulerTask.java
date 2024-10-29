package com.lemon.lemon.service.cron;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lemon.lemon.entity.Movie;
import com.lemon.lemon.entity.MovieBanner;
import com.lemon.lemon.entity.MovieCardView;
import com.lemon.lemon.mapper.MovieBannerMapper;
import com.lemon.lemon.mapper.MovieCardViewMapper;
import com.lemon.lemon.mapper.MovieMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class SchedulerTask {

    @Autowired
    MovieMapper movieMapper;

    @Autowired
    MovieBannerMapper movieBannerMapper;

    @Autowired
    MovieCardViewMapper movieCardViewMapper;

    @Scheduled(cron = "0 0 0-23/2 * * ?")
    void buildMoviesForBanner() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("random()").last("limit 4");
        List<Movie> movies = movieMapper.selectList(wrapper);

        movieBannerMapper.truncateTable();

        for (Movie movie : movies) {
            MovieBanner movieBanner = new MovieBanner();
            BeanUtils.copyProperties(movie,movieBanner);
            movieBannerMapper.insert(movieBanner);
        }
    }

    @Scheduled(cron = "0 0 0-23/2 * * ?")
    void buildMoviesForCardViews() {
        QueryWrapper<Movie> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("random()").last("limit 4");
        List<Movie> movies = movieMapper.selectList(wrapper);

        movieCardViewMapper.truncateTable();

        for (Movie movie : movies) {
            MovieCardView movieCardView = new MovieCardView();
            BeanUtils.copyProperties(movie,movieCardView);
            movieCardViewMapper.insert(movieCardView);
        }
    }
}
