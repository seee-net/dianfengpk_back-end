package com.dao;

import com.dao.impl.FormSingleDao;
import com.entity.FormSingle;
import com.util.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormSingleDaoImpl implements FormSingleDao {
    @Override
    public boolean createFormSingle(FormSingle fs) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rowNom = 0;

        try {
            conn = DBconn.getConnection();

            String sql = "insert into apply_form_single (name, school, phone, qq) " +
                    "values(?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, fs.getName());
            stmt.setString(2, fs.getSchool());
            stmt.setString(3, fs.getPhone());
            stmt.setString(4, fs.getQq());

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
    public List<FormSingle> readFormSingle() {
        //数据库设置
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FormSingle> formSingleData = new ArrayList<>();
        try {
            conn = DBconn.getConnection();

            String sql = "select * from apply_form_single " ;

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()){
                FormSingle formSingle = new FormSingle();
                formSingle.setId(rs.getString("id"));
                formSingle.setName(rs.getString("name"));
                formSingle.setSchool(rs.getString("school"));
                formSingle.setPhone(rs.getString("phone"));
                formSingle.setQq(rs.getString("qq"));

                formSingleData.add(formSingle);
            }
        } catch (SQLException e){
            System.err.println("DAO: 数据库错误");
            e.printStackTrace();
        } finally {
            DBconn.closeAll(conn, stmt, rs);
        }
        return formSingleData;
    }
}
