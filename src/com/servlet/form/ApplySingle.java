package com.servlet.form;

import com.dao.FormSingleDaoImpl;
import com.entity.FormSingle;
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

@WebServlet(name = "ApplySingle", urlPatterns = "/ApplySingle")
public class ApplySingle extends HttpServlet {
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

        try {
            System.out.println("ApplySingle:正在获取表单数据");
            String jsonReceive = InputUtil.getInput(in);

            Map<String, String> dataReceive = JSONUtil.jsonToMaps(jsonReceive);
            FormSingle form = new FormSingle();

            if(dataReceive != null) {
                for (String key : dataReceive.keySet()) {
                    switch (key) {
                        case "name": {
                            form.setName(dataReceive.get("name"));
                            break;
                        }
                        case "phone": {
                            form.setPhone(dataReceive.get("phone"));
                            break;
                        }
                        case "qq": {
                            form.setQq(dataReceive.get("qq"));
                            break;
                        }
                        case "school": {
                            form.setSchool(dataReceive.get("school"));
                            break;
                        }
                        default:
                            break;
                    }
                }
            }

            boolean success = new FormSingleDaoImpl().createFormSingle(form);

            System.out.println("ApplySingle:正在返回JSON数据");
            HashMap<String, Object> data = new HashMap<>();
            data.put("success", success);

            String jsonSend = JSONUtil.objectToJson(data);
            out.print(jsonSend);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
