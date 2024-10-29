package com.lemon.lemon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lemon.lemon.entity.MovieCardView;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MovieCardViewMapper extends BaseMapper<MovieCardView> {
    @Update("truncate table os_movie.movie_card_view")
    void truncateTable();
}
