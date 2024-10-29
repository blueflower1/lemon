package com.lhw.lemon.config;

public class Config {

    public static final String BACKEND_URL = "http://101.42.53.251:8080/";
    public static final String BANNER_URL = BACKEND_URL + "getMoviesForBanner";
    public static final String CARD_VIEW_URL = BACKEND_URL + "getMoviesForCardView";
    public static final String DETAIL_URL = BACKEND_URL + "getMovieById";
    public static final String VIDEO_LIST_URL = BACKEND_URL + "getMoviesForQuery";

    public static final String MOVIE = "电影";
    public static final String TELEPLAY = "电视剧";
    public static final String SEARCH_NULL_TOAST = "输入关键词为空";
    public static String DETAIL_VIEW_TEXT_FORMAT = "%s\n类型: %s\n添加时间: %s\n最后一次修改时间: %s";
    public static String VIDEO_LIST_VIEW_TEXT_FORMAT = "%s\n类型: %s\n添加时间: %s";
    public static final String MOVIE_PLAY_BUTTON_TEXT = "播放";
    public static final String SOURCE_MISS = "该资源尚不存在,联系管理员添加或填写愿望单";
    public static final String BROWSABLE_MISS = "未找到相关浏览器";
}
