package com.servlet.back.news;

import com.dao.NewsDaoImpl;
import com.util.JSONUtil;
import com.util.StreamUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ReadNews", urlPatterns = "/ReadNews")
public class ReadNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        ServletOutputStream out=response.getOutputStream();

        List data;
        data = new NewsDaoImpl().readNews();

        System.out.println("ReadNews:正在返回JSON数据");
        String jsonSend = JSONUtil.objectToJson(data);

        StreamUtil.setOutput(out, jsonSend);
    }
}
