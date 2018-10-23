package com.dao.impl;

import com.entity.News;

import java.util.List;

public interface NewsDao {
    boolean createNews(News n);
    boolean updateNews(News n);
    List<News> readNews();
    boolean delNews(News n);
}
