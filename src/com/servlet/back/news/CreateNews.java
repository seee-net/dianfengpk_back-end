package com.servlet.back.news;

import com.dao.NewsDaoImpl;
import com.entity.News;
import com.util.InputUtil;
import com.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "CreateNews", urlPatterns = "/CreateNews")
public class CreateNews extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");

        BufferedReader in =request.getReader();
        PrintWriter out=response.getWriter();

        if(request.getParameter("id").equals("admin")) {
            try {
                System.out.println("CreateNews:正在获取表单数据");
                String jsonReceive = InputUtil.getInput(in);

                Map<String, String> dataReceive = JSONUtil.jsonToMaps(jsonReceive);
                News news = new News();

                if (dataReceive != null) {
                    for (String key : dataReceive.keySet()) {
                        switch (key) {
                            case "content": {
                                news.setContent(dataReceive.get("content"));
                                break;
                            }
                            default:
                                break;
                        }
                    }
                }

                boolean success = new NewsDaoImpl().createNews(news);

                System.out.println("CreateNews:正在返回JSON数据");
                HashMap<String, Object> data = new HashMap<>();
                data.put("success", success);

                String jsonSend = JSONUtil.objectToJson(data);
                out.print(jsonSend);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
        //TODO 做一个小验证
}
