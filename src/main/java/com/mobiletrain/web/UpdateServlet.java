package com.mobiletrain.web;

import com.mobiletrain.config.SpringConfig;
import com.mobiletrain.domain.Contact;
import com.mobiletrain.service.ContactService;
import com.mobiletrain.service.impl.ContactServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    @Resource(name = "contactService")
    private ContactService service;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        ContactService service = context.getBean("contactService", ContactService.class);



        // 1. 接收数据
        Contact contact = new Contact();
        Map<String, String[]> parameterMap = request.getParameterMap();
        try {
            BeanUtils.populate(contact, parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 2. 处理数据
        service.update(contact);

        // 3. 响应数据
        // 跳转
        response.sendRedirect("query_contact_page");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
