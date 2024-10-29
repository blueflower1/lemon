package com.lemon.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.lemon.entity.MovieBanner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MovieBannerMapper extends BaseMapper<MovieBanner> {
    @Update("truncate table os_movie.movie_banner")
    void truncateTable();

}
