package com.servlet.back.data;

import com.dao.FormTeamDaoImpl;
import com.util.JSONUtil;
import com.util.InputUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "GetDataTeam", urlPatterns = "/GetDataTeam")
public class GetDataTeam extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        PrintWriter out=response.getWriter();

        //TODO 做一个小验证

        List data;
        data = new FormTeamDaoImpl().readFormTeam();

        System.out.println("GetDataTeam:正在返回JSON数据");
        String jsonSend = JSONUtil.objectToJson(data);

        out.print(jsonSend);
    }
}
