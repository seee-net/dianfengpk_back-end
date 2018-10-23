package com.servlet.back.data;

import com.dao.FormSingleDaoImpl;
import com.util.JSONUtil;
import com.util.StreamUtil;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetDataSingle", urlPatterns = "/GetDataSingle")
public class GetDataSingle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        ServletOutputStream out=response.getOutputStream();

        //TODO 做一个小验证

        List data;
        data = new FormSingleDaoImpl().readFormSingle();

        System.out.println("GetDataSingle:正在返回JSON数据");
        String jsonSend = JSONUtil.objectToJson(data);

        StreamUtil.setOutput(out, jsonSend);
    }
}
