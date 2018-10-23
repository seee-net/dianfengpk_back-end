package com.dao;

import com.dao.impl.FormTeamDao;
import com.entity.FormTeam;
import com.util.DBconn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FormTeamDaoImpl implements FormTeamDao {
    @Override
    public boolean createFormTeam(FormTeam ft) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        int rowNom = 0;

        try {
            conn = DBconn.getConnection();

            String sql = "insert into apply_form_team (teamname, school, leader, phone, qq, email, team_member1, team_member2, team_member3, team_member4, intro) " +
                    "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ft.getTeamname());
            stmt.setString(2, ft.getSchool());
            stmt.setString(3, ft.getPhone());
            stmt.setString(4, ft.getQq());
            stmt.setString(5, ft.getEmail());
            stmt.setString(6, ft.getTeam_member1());
            stmt.setString(7, ft.getTeam_member2());
            stmt.setString(8, ft.getTeam_member3());
            stmt.setString(9, ft.getTeam_member4());
            stmt.setString(10, ft.getIntro());

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
    public List<FormTeam> readFormTeam() {
        //数据库设置
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<FormTeam> formTeamData = new ArrayList<>();
        try {
            conn = DBconn.getConnection();

            String sql = "select * from apply_form_team " ;

            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()){
                FormTeam formTeam = new FormTeam();
                formTeam.setId(rs.getString("id"));
                formTeam.setTeamname(rs.getString("teamname"));
                formTeam.setSchool(rs.getString("school"));
                formTeam.setLeader(rs.getString("leader"));
                formTeam.setPhone(rs.getString("phone"));
                formTeam.setQq(rs.getString("qq"));
                formTeam.setEmail(rs.getString("email"));
                formTeam.setTeam_member1(rs.getString("team_member1"));
                formTeam.setTeam_member2(rs.getString("team_member2"));
                formTeam.setTeam_member3(rs.getString("team_member3"));
                formTeam.setTeam_member4(rs.getString("team_member4"));
                formTeam.setIntro(rs.getString("intro"));

                formTeamData.add(formTeam);
            }
        } catch (SQLException e){
            System.err.println("DAO: 数据库错误");
            e.printStackTrace();
        } finally {
            DBconn.closeAll(conn, stmt, rs);
        }
        return formTeamData;
    }
}
