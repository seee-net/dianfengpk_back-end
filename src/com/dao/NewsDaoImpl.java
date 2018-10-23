package com.dao;

import com.dao.impl.NewsDao;
import com.entity.News;
import com.util.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NewsDaoImpl implements NewsDao {
    @Override
    public boolean createNews(News n) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rowNom = 0;

        try {
            conn = DBconn.getConnection();

            String sql = "insert into news (content) " +
                    "values(?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, n.getContent());

            rowNom = stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("DAO: 数据库错误");
            e.printStackTrace();
        } finally {
            DBconn.closeAll(conn, stmt, rs);
        }
        return rowNom != 0;
    }

    @Override
    public boolean delNews(News n) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rowNom = 0;


        try {
            conn = DBconn.getConnection();

            String sql = "delete from news " +
                    "where id = ?";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, n.getId());

            rowNom = stmt.executeUpdate();
        } catch (SQLException e){
            System.err.println("DAO: 数据库错误");
            e.printStackTrace();
        } finally {
            DBconn.closeAll(conn, stmt, rs);
        }
        return rowNom != 0;
    }

    @Override
    public boolean updateNews(News n) {
        //TODO 修改News
        return false;
    }

    @Override
    public List<News> readNews() {
        //数据库设置
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<News> newsData = new ArrayList<>();
        try {
            conn = DBconn.getConnection();

            String sql = "select * from news " ;

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()){
                News news = new News();
                news.setId(rs.getString("id"));
                news.setContent(rs.getString("content"));

                newsData.add(news);
            }
        } catch (SQLException e){
            System.err.println("DAO: 数据库错误");
            e.printStackTrace();
        } finally {
            DBconn.closeAll(conn, stmt, rs);
        }
        return newsData;
    }
}
