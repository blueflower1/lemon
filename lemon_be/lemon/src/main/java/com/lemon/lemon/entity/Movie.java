package com.lemon.lemon.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "os_movie.movie")
public class Movie {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    @TableField(value = "video_type")
    private Integer videoType;
    @TableField(value = "video_urls")
    private String videoUrls;
    @TableField(value = "photo_url")
    private String photoUrl;
    @TableField(value = "create_time")
    private Integer createTime;
    @TableField(value = "update_time")
    private Integer updateTime;
}
